package com.cy.date;

import java.util.ArrayList;
import java.util.List;

public class BTree {

    /**
     * b+树非叶子节点
     */
    public static class TreeNode{
        public List<Integer> entrys = new ArrayList<Integer>();//节点值
        public List<? extends TreeNode> childs = new ArrayList<TreeNode>();//子节点

        public List<Integer> getEntrys() {
            return entrys;
        }

        public void setEntrys(List<Integer> entrys) {
            this.entrys = entrys;
        }

        public List<? extends TreeNode> getChilds() {
            return childs;
        }

        public void setChilds(List<? extends TreeNode> childs) {
            this.childs = childs;
        }
    }

    /**
     * b+树叶子节点
     */
    public static class LeafNode extends TreeNode{
        public List<String> datas = new ArrayList<String>();
        public TreeNode next;


        public List<String> getDatas() {
            return datas;
        }

        public void setDatas(List<String> datas) {
            this.datas = datas;
        }

        public TreeNode getNext() {
            return next;
        }

        public void setNext(TreeNode next) {
            this.next = next;
        }
    }

    //b+树阶数
    private final int m = 4;

    //Entry 上限
    private final int OVERFLOW_BOUND = m-1;

    //Entry 下限
    private final int UNDERFLOW_BOUND = this.OVERFLOW_BOUND/2;


    public static void main(String[] args) {
        BTree bTree = new BTree();
        TreeNode root = new TreeNode();
        
        //b+树插入数据
        root = bTree.insert(root,10);
        root = bTree.insert(root,20);
        root = bTree.insert(root,30);
        root = bTree.insert(root,50);

        System.out.println(root);
    }


    /**
     * 插入数据
     * @param root
     * @param val
     * @return
     */
    public TreeNode insert(TreeNode root, int val){
        if(root==null){
            root = new TreeNode();
            root.getEntrys().add(val);
        }else{
            List<Integer> entrys = root.getEntrys();
            //判断当前节点是否大于上限
            if(entrys.size()<OVERFLOW_BOUND){
                root.getEntrys().add(val);
            }else{
                if(val>entrys.get(OVERFLOW_BOUND-1)){

                }
            }

        }


        return root;
    }



}
