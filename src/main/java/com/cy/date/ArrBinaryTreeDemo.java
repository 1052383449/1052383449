package com.cy.date;

import java.util.*;

/**
 * 二叉树
 */
public class ArrBinaryTreeDemo {
    /**
     * 创建树的节点
     */
    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }

    public TreeNode root;

    /**
     * 先自己创建一个树
     */
    public void creatTree(){
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(0);
        TreeNode node9 = new TreeNode(2);
        TreeNode node10 = new TreeNode(9);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        node7.right = node10;
        root = node1;
    }



    public static void main(String[] args) {
        ArrBinaryTreeDemo binaryTree = new ArrBinaryTreeDemo();
        binaryTree.creatTree();

        //前序遍历：访问的顺序为：根→左→右
        binaryTree.preOrdder(binaryTree.root);
        System.out.println("____________");
        //中序遍历：访问的顺序为：左→根→右
        binaryTree.inOrder(binaryTree.root);
        System.out.println("____________");
        //后序遍历：访问的顺序为：左→右→根
        binaryTree.postOrder(binaryTree.root);
        System.out.println("____________");

        //获取二叉树节点个数
        System.out.println("获取二叉树节点个数");
        System.out.println(binaryTree.size(binaryTree.root));
        System.out.println("____________");

        //求叶子节点个数
        System.out.println("求叶子节点个数");
        System.out.println(binaryTree.getLeafNodeCount(binaryTree.root));
        System.out.println("____________");

        //获取第K层节点的个数
        System.out.println("获取第K层节点的个数");
        System.out.println(binaryTree.getKLevelNodeCount(binaryTree.root,3));
        System.out.println("____________");


        //获取二叉树的高度
        System.out.println("获取二叉树的高度");
        System.out.println(binaryTree.getHeight(binaryTree.root));
        System.out.println("____________");

        //检测值为value的元素是否存在
        System.out.println("检测值为value的元素是否存在");
        System.out.println(binaryTree.find(binaryTree.root,1).val);
        System.out.println("____________");

        //层序遍历
        System.out.println("层序遍历");
        binaryTree.levelOrder(binaryTree.root);
        System.out.println("____________");

        //判断是否完全二叉树
        System.out.println("判断是否完全二叉树");
        System.out.println(binaryTree.isCompleteTree(binaryTree.root));
        System.out.println("____________");


        //查找二叉搜索树的数据
        System.out.println("查找二叉搜索树的数据");
        System.out.println(binaryTree.search(binaryTree.root,5).val);
        System.out.println("____________");


        //插入数据
        /*System.out.println("插入数据");
        System.out.println(binaryTree.insert(binaryTree.root,22));
        System.out.println("____________");*/


        //删除数据
        System.out.println("删除数据");
        binaryTree.remove(binaryTree.root,5);
        System.out.println("____________");



        //AVL树（平衡二叉树）插入数据
        System.out.println("AVL树（平衡二叉树）插入数据");
        TreeNode root = null;
        root = binaryTree.insertAVL(root,7);
        root = binaryTree.insertAVL(root,8);
        root = binaryTree.insertAVL(root,3);
        root = binaryTree.insertAVL(root,1);
        root = binaryTree.insertAVL(root,4);
        root = binaryTree.insertAVL(root,5);
        root = binaryTree.insertAVL(root,2);
        root = binaryTree.insertAVL(root,1);
       /*root = binaryTree.insertAVL(root,9);*/

        System.out.println("____________");

        //层序遍历
        System.out.println("层序遍历");
        binaryTree.levelOrder(root);
        System.out.println("____________");


        //AVL树（平衡二叉树）删除数据
        System.out.println("AVL树（平衡二叉树）删除数据");
        root = binaryTree.removeAVL(root,7);
        System.out.println("____________");

        //层序遍历
        System.out.println("层序遍历");
        binaryTree.levelOrder(root);
        System.out.println("____________");
    }


    /**
     * AVL树（平衡二叉树）插入数据
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertAVL(TreeNode root,int val){
        if(root==null){
            root = new TreeNode(val);
        }else{
            TreeNode cur = root;
            TreeNode parent=null;
            while(cur!=null){
                if(val>cur.val){
                    parent = cur;
                    cur = cur.right;
                }else if(val<cur.val){
                    parent = cur;
                    cur = cur.left;
                }else{
                    return root;//不能有相同的数据
                }
            }

            TreeNode node = new TreeNode(val);
            if(val>parent.val){
                parent.right = node;
            }else{
                parent.left = node;
            }

        }


        //调整不平衡节点
        root = adjustment(root);

        return root;
    }

    //查找不平衡子树
    public void searchImbalance(TreeNode root,TreeNode parent, Stack<Map<String,TreeNode>> treeStack){
        if(root==null) return;
        //查询左子树的高度
        int lh = 0;
        if(root.left!=null){
            lh = getHeight(root.left);
        }
        //查询右子树的高度
        int rh = 0;
        if(root.right!=null){
            rh = getHeight(root.right);
        }
        if(lh-rh>1||lh-rh<-1){
            Map<String,TreeNode> map = new HashMap<String, TreeNode>();
            map.put("cur",root);
            map.put("parent",parent);
            treeStack.push(map);
        }
        searchImbalance(root.left,root,treeStack);
        searchImbalance(root.right,root,treeStack);
    }

    public TreeNode adjustment(TreeNode root){
        //查找最小不平衡子树
        Stack<Map<String,TreeNode>> treeStack = new Stack<Map<String, TreeNode>>();
        while (treeStack!=null){
            searchImbalance(root,null,treeStack);
            //判断是属于哪种旋转情况
            if(treeStack.size()>0){
                Map<String,TreeNode> map = treeStack.pop();
                TreeNode cur = map.get("cur");
                //LL单旋
                if(cur.left!=null&&cur.left.left!=null&&(cur.right==null||cur.left.left.left!=null||cur.left.left.right!=null)){
                    TreeNode newNode = cur.left;
                    cur.left = null;
                    newNode.right = cur;
                    if(map.get("parent")!=null){
                        TreeNode parent = map.get("parent");
                        parent.left = newNode;
                    }else{
                        root = newNode;
                    }
                }
                //RR单旋
                if(cur.right!=null&&cur.right.right!=null&&(cur.left==null||cur.right.right.right!=null||cur.right.right.left!=null)){
                    TreeNode newNode = cur.right;
                    cur.right = null;
                    newNode.left = cur;
                    if(map.get("parent")!=null){
                        TreeNode parent = map.get("parent");
                        parent.right = newNode;
                    }else{
                        root = newNode;
                    }
                }
                //LR双旋
                if(cur.left!=null&&cur.left.right!=null&&(cur.right==null||cur.left.right.right!=null||cur.left.right.left!=null)){
                    TreeNode newNode = new TreeNode(cur.left.right.val);
                    newNode.left =  new TreeNode(cur.left.val);
                    if(cur.left.left!=null){
                        newNode.left.left = new TreeNode(cur.left.left.val);
                    }
                    if(cur.left.right.left!=null){
                        newNode.left.right = new TreeNode(cur.left.right.left.val);
                    }
                    newNode.right = new TreeNode(cur.val);
                    if(cur.right!=null){
                        newNode.right.right = new TreeNode(cur.right.val);
                    }
                    if(cur.left.right.right!=null){
                        newNode.right.left = new TreeNode(cur.left.right.right.val);
                    }
                    if(map.get("parent")!=null){
                        TreeNode parent = map.get("parent");
                        parent.left = newNode;
                    }else{
                        root = newNode;
                    }
                }

                //RL双旋
                if(cur.right!=null&&cur.right.left!=null&&(cur.right.left.left!=null||cur.right.left.right!=null)){
                    TreeNode newNode = new TreeNode(cur.right.left.val);
                    newNode.right =  new TreeNode(cur.right.val);
                    if(cur.right.right!=null){
                        newNode.right.right = new TreeNode(cur.right.right.val);
                    }
                    if(cur.right.left.left!=null){
                        newNode.right.left = new TreeNode(cur.right.left.left.val);
                    }
                    newNode.left = new TreeNode(cur.val);
                    if(cur.left!=null){
                        newNode.left.left = new TreeNode(cur.left.val);
                    }
                    if(cur.right.left.right!=null){
                        newNode.left.right = new TreeNode(cur.right.left.right.val);
                    }
                    if(map.get("parent")!=null){
                        TreeNode parent = map.get("parent");
                        parent.right = newNode;
                    }else{
                        root = newNode;
                    }
                }
            }else{
                treeStack = null;
            }

        }
        return root;
    };

    /**
     * 删除数据
     * @param root
     * @param val
     */
    public TreeNode removeAVL(TreeNode root,int val){
        if(root==null) return root;

        //找到当前节点
        TreeNode cur = root;
        TreeNode parent=null;
        while(cur!=null){
            if(val>cur.val){
                parent = cur;
                cur = cur.right;
            }else if(val<cur.val){
                parent = cur;
                cur = cur.left;
            }else{
                break;
            }
        }

        //删除节点
        //1、当前节点是叶子节点，直接删除该节点
        if(cur.right == null && cur.left == null){
            if(parent.left.val == val){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }else
        //2、当前节点只有右节点或者左节点，子节点替换父节点
        if(cur.right == null || cur.left == null){
            if(parent.left.val == val){
                parent.left = cur;
            }else{
                parent.right = cur;
            }
        }else{
            //3、当前节点有左右节点,找左节点最大值替换或者找右节点替换
            //左节点处理，找最大值
            TreeNode max=cur.left;
            TreeNode maxParent=cur;
            while(max.right!=null){
                maxParent = max;
                max = max.right;

            }
            cur.val = max.val;
            if (max==maxParent.left){
                maxParent.left=max.right;
            }else {
                maxParent.right = max.right;
            }
        }


        //4、调整不平衡节点
        return adjustment(root);

    }


    /**
     * 查找数据
     * @param root
     * @param key
     * @return
     */
    public TreeNode search(TreeNode root,int key){
        if(root==null) return null;
        if(key==root.val){
            return root;
        }else if(key<root.val){
            return search(root.left,key);
        }else if(key>root.val){
            return search(root.right,key);
        }

        return null;
    }

    /**
     * 插入数据
     * @param root
     * @param val
     * @return
     */
    public boolean insert(TreeNode root,int val){
        if(root==null){
            root = new TreeNode(val);
        }else{
            TreeNode cur = root;
            TreeNode parent=null;
            while(cur!=null){
                if(val>cur.val){
                    parent = cur;
                    cur = cur.right;
                }else if(val<cur.val){
                    parent = cur;
                    cur = cur.left;
                }else{
                    return false;//不能有相同的数据
                }
            }

            TreeNode node = new TreeNode(val);
            if(val>parent.val){
                parent.right = node;
            }else{
                parent.left = node;
            }

        }
        return true;
    }


    /**
     * 删除数据
     * @param root
     * @param val
     */
    public void remove(TreeNode root,int val){
        if(root==null) return;

        //找到当前节点
        TreeNode cur = root;
        TreeNode parent=null;
        while(cur!=null){
            if(val>cur.val){
                parent = cur;
                cur = cur.right;
            }else if(val<cur.val){
                parent = cur;
                cur = cur.left;
            }else{
                break;
            }
        }

        //删除节点
        //1、当前节点是叶子节点，直接删除该节点
        if(cur.right == null && cur.left == null){
            if(parent.left.val == val){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }
        //2、当前节点只有右节点或者左节点，子节点替换父节点
        if(cur.right == null || cur.left == null){
            if(parent.left.val == val){
                parent.left = cur;
            }else{
                parent.right = cur;
            }
        }
        //3、当前节点有左右节点,找左节点最大值替换或者找右节点替换
        //左节点处理，找最大值
        TreeNode max=cur.left;
        TreeNode maxParent=cur;
        while(max.right!=null){
            maxParent = max;
            max = max.right;

        }
        cur.val = max.val;
        if (max==maxParent.left){
            maxParent.left=max.right;
        }else {
            maxParent.right = max.right;
        }

        //右节点处理

    }

    /**
     * 前序遍历
     * @param root
     */
    public void preOrdder(TreeNode root){
        if(root==null) return;
        System.out.println(root.val);
        preOrdder(root.left);
        preOrdder(root.right);
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inOrder(TreeNode root){
        if(root==null) return;
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    /**
     * 后序遍历
     * @param root
     */
    public void postOrder(TreeNode root){
        if(root==null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

    // 获取树中节点的个数
    public  int size(TreeNode root){
        if(root==null) return 0;
        return size(root.left) + size(root.right) + 1;
    }
    // 获取叶子节点的个数
    public int getLeafNodeCount(TreeNode root){
        if(root==null) return 0;
        if(root.left==null&&root.right==null) return 1;
        return getLeafNodeCount(root.left) + getLeafNodeCount(root.right) ;
    }

    // 获取第K层节点的个数
    public int getKLevelNodeCount(TreeNode root,int k){
        if(root==null||k==0) return 0;
        if(k==1) return 1;
        return getKLevelNodeCount(root.left,k-1) + getKLevelNodeCount(root.right,k-1) ;
    }
    // 获取二叉树的高度
    public int getHeight(TreeNode root){
        if(root==null) return 0;
        int leftNum = getHeight(root.right);
        int rightNum = getHeight(root.left);
        return (leftNum>rightNum?leftNum:rightNum)+1;
    }


    // 检测值为value的元素是否存在
    public TreeNode find(TreeNode root, int val){
        if(root==null) return null;
        if(root.val==val){
            return root;
        }
        TreeNode left = find(root.left,val);
        if(left!=null && left.val==val){
            return left;
        }
        TreeNode right = find(root.right,val);
        if(right!=null && right.val==val){
            return right;
        }
        return null;
    }
    //层序遍历
    public void levelOrder(TreeNode root){
        if(root==null) return;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size>0){
                TreeNode treeNode = queue.poll();
                System.out.println(treeNode.val);
                if(treeNode.left!=null)queue.offer(treeNode.left);
                if(treeNode.right!=null)queue.offer(treeNode.right);
                size--;
            }
            System.out.println("..................");
        }

    }
    // 判断一棵树是不是完全二叉树
    public boolean isCompleteTree(TreeNode root){
        if(root==null) return true;

        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        queue2.offer(root);    //先放入根节点，为了能够进入循环
        TreeNode cur = root;    //先创建一下cur
        while(cur!=null){
            cur = queue2.poll();    //将队列的元素取出来，用cur记录
            if(cur!=null){    //若刚刚提取出来的元素不为null，将左、右节点放入队列
                queue2.offer(cur.left);
                queue2.offer(cur.right);
            }
        }
        //此时弹出来则证明遇到了null，那么此时就要判断队列中有没有节点，一直弹出元素
        //直至栈为空或者遇到了节点
        while(!queue2.isEmpty()){
            TreeNode str = queue2.poll();
            if(str!=null){    //如果遇到了节点，不为空，则证明该树不为完全二叉树，返回false
                return false;
            }
        }

        return true;
    }
}
