package com.cy.history.tree;

import java.util.Stack;

public class TreeTest {
    String data;
    TreeTest LChild;
    TreeTest RChild;
    TreeTest(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TreeTest getLChild() {
        return LChild;
    }

    public void setLChild(TreeTest LChild) {
        this.LChild = LChild;
    }

    public TreeTest getRChild() {
        return RChild;
    }

    public void setRChild(TreeTest RChild) {
        this.RChild = RChild;
    }

    public String toString() {
        return data;
    }
    public static String[] tree = {"0","1","2","3","4","5","6","7","8"};
    public static int i = 0;
    //先序创建二叉树是比较合适的
    public static TreeTest inOrderCreateBTree() {
        if(i>=tree.length){
            return null;
        }
        TreeTest bt = null;
        String s = tree[i];
        i++;
        bt = new TreeTest(s);
        if(i%2>0) {
            bt.LChild = inOrderCreateBTree();
            bt.RChild = inOrderCreateBTree();
            return bt;
        }else {


            bt.RChild = inOrderCreateBTree();
            return bt;
        }
    }
    //递归法前序遍历二叉树
    public static void preOrderPrintBTree(TreeTest bt) {
        if(bt == null) {
            System.out.print("." + " ");
        }else {
            System.out.print(bt + " ");
            preOrderPrintBTree(bt.LChild);
            preOrderPrintBTree(bt.RChild);
        }
    }
    //基于栈的非递归法先序遍历二叉树
    public static void preOrderPrintBTree1(TreeTest bt) {
        if(bt == null) {
            System.out.println("null tree");
        }
        Stack<TreeTest> stack = new Stack<TreeTest>();
        TreeTest p = bt;
        while(!stack.isEmpty() || p != null) {
            while(p != null) {
                System.out.print(p + " ");
                stack.push(p);
                p = p.LChild;
                if(p == null) {
                    System.out.print("." + " ");
                }
            }
            if(!stack.isEmpty()) {
                p = stack.pop();
                p = p.RChild;
                if(p == null) {
                    System.out.print("." + " ");
                }
            }
        }
    }
    public static void main(String[] args) {
        TreeTest TreeTest0 = inOrderCreateBTree();


        //preOrderPrintBTree(TreeTest0);
        preOrderPrintBTree1(TreeTest0);

    }
}
