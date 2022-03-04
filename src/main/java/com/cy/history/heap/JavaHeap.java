package com.cy.history.heap;

public class JavaHeap {
    static class OOMObject{}

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        array = test(array,1,5);
        for (int i : array) {
            System.out.println(i);

        }
    }

    public static int[] test(int[] array,int index,int end){
        if(index>=end||end>array.length-1){
            return array;
        }
        while (index < end) {
            int k = array[index];
            array[index]=array[end];
            array[end]=k;
            index++;
            end--;
        }

        return array;
    };
}
