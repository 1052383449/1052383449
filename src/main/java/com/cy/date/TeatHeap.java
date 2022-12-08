package com.cy.date;

public class TeatHeap {

    public int[] arr = {11, 18, 4, 65, 89, 32, 16};    //堆是以数组的方式存储的

    public void creatHeap() {
        //最后一棵子树的位置
        int root = (arr.length - 1 - 1) / 2;
        while (root >= 0) {
            shiftDown(root);    //一直向下调整直至调整到最后一颗树，也就是0位置上的整棵树
            root--;
        }
    }

    public void shiftDown(int root) {
        int parent = root;
        int child = parent * 2 + 1; //孩子节点记得加一
        //这里使用while语句，是为了确保在当前子树创建完后其子树的子树也是大根堆
        while (child < arr.length) {    //向下调整，要一路顺下去，保证是正确的
            if (child + 1 < arr.length && (arr[child] > arr[child + 1])) {  //这里是看除了左孩子节点外，还有没有右孩子节点，如果有的话要找出来大的那个，以便之后替换
                child = child + 1;
            }
            //还要判断该子树的根节点是不是最大的，是就直接break，因为已经是大根堆了，不是再交换变成大根堆
            if (arr[parent] < arr[child]) {
                break;
            } else {
                int str = this.arr[parent];
                this.arr[parent] = this.arr[child];
                this.arr[child] = str;
                parent = child;
                child = parent * 2 + 1;
            }
        }
    }


    public void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i+",");
        }
        System.out.println("");
        System.out.println("__________");
    }

    public static void main(String[] args) {
        TeatHeap teatHeap = new TeatHeap();
        teatHeap.printArr(teatHeap.arr);
        teatHeap.creatHeap();
        teatHeap.printArr(teatHeap.arr);
    }
}
