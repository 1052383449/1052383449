package com.cy.date;

/**
 * 1.创建RBTree，定义颜色
 * 2.创建RBNode
 * 3.辅助方法定义：parentOf(node),isRed(node),isBlack(node),setRed(node),setBlack(node),inOrderPrint();
 * 4.左旋方式定义：leftRotate(node);
 * 5.右旋方式定义：rightRotate(node);
 * 6.公开插入接方法定义：insert(K key, V value);
 * 7.内部插入接口方法定义：insert(RBNode node);
 * 8.修正插入导致红黑树失衡的方法定义：insertFIxUp(RBNode node);
 * 9.测试红黑树正确性
 *
 *
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends  Comparable<K>, V> {
    private static final boolean RED = true;    // 红色节点
    private static final boolean BLACK = false; // 黑色节点

    /**
     * 根节点的引用
     */
    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    /**
     * 获取当前节点的父节点
     * @param node
     */
    private RBNode parentOf(RBNode node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    /**
     * 节点是否为红色
     * @param node
     */
    private boolean isRed(RBNode node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    /**
     * 节点是否为黑色
     * @param node
     */
    private boolean isBlack(RBNode node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    /**
     * 设置节点为红色
     * @param node
     */
    private void setRed(RBNode node) {
        if (node != null) {
            node.color = RED;
        }
    }

    /**
     * 设置节点为黑色
     * @param node
     */
    private void setBlack(RBNode node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 中序打印二叉树
     */
    public void inOrderPrint() {
        inOrderPrint(this.root);
    }

    private void inOrderPrint(RBNode node) {
        if (node != null) {
            inOrderPrint(node.left);
            System.out.println("key:" +  node.key + ",value:" + node.value);
            inOrderPrint(node.right);
        }
    }

    /**
     * 公开的插入方法
     * @param key
     * @param value
     */
    public void insert(K key, V value) {
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);
        // 新节点 一定是红色！
        node.setColor(RED);
        insert(node);
    }

    private void insert(RBNode node) {
        // 1.查找当前node的父节点
        RBNode parent = null;
        // 从 根节点 开始查找
        RBNode x = this.root;

        while (x != null) {
            parent = x;
            // cmp > 0 说明 node.key 大于 x.key
            // cmp = 0 说明 node.key 等于 x.key 说明需要进行替换操作
            // cmp < 0 说明 node.key 小于 x.key 需要到x的左子树查找
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp == 0) {
                x.setValue(node.getValue());
                return;
            } else {
                x = x.left;
            }
        }
        node.parent = parent;

        if (parent != null) {
            // 判断 node与parent 的key 谁大
            // cmp > 0 说明 当前node的key比parent的key大，需要把node放入parent的右子节点
            // cmp < 0 说明 当前node的key比parent的key小，需要把node放入parent的左子节点
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0) {
                parent.right = node;
            } else {
                parent.left = node;
            }
        } else {
            this.root = node;
        }

        // 需要调用修复红黑树平衡的方法，insertFixup();
        insertFixup(node);
    }

    /**
     * 插入后修复红黑树平衡的方法
     *      情景1：红黑树为空树，将根节点染色为黑色
     *      情景2：插入节点的key已经存在
     *      情景3：插入节点的父节点为黑色，因为所插入的路径，黑色节点没有变化，所以红黑树依然平衡，不需要处理
     *
     *      情景4：插入节点的父节点为红色（需要处理）
     *          情景4.1：叔叔节点存在，并且为红色（父-叔 双红），将爸爸和叔叔染色为红色，将爷爷染色为红色，并且再以爷爷节点为当前节点，进行下一轮处理
     *          情景4.2：叔叔节点不存在，或者为黑色，父节点为爷爷节点的左子树
     *              情景4.2.1：插入节点为其父节点的左子节点（LL情况），将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点右旋，就完成了
     *              情景4.2.2：插入节点为其父节点的右子节点（LR情况），
     *                        以爸爸节点进行一次左旋，得到LL双红的情景（4.2.1），然后指定爸爸节点为当前节点进行下一轮处理
     *          情景4.3：叔叔节点不存在，或者为黑色，父节点为爷爷节点的右子树
     *              情景4.3.1：插入节点为其父节点的右子节点（RR情况），将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点左旋，就完成了
     *              情景4.3.2：插入节点为其父节点的左子节点（RL情况），
     *                        以爸爸节点进行一次右旋，得到RR双红的情景（4.3.1），然后爸爸节点为当前节点进行下一轮处理
     */
    private void insertFixup(RBNode node) {
        this.root.setColor(BLACK);  // 处理情景1，情景2、3不需要处理

        RBNode parent = parentOf(node);
        RBNode gparent = parentOf(parent);

        // 情景4：插入节点的父节点为红色
        if (parent != null && isRed(parent)) {
            // 如果父节点为红色，那么一定存在爷爷节点，因为根节点不可能是红色

            RBNode uncle = null;

            if (parent == gparent.left) {   // 父节点为爷爷节点的左子树
                uncle = gparent.right;

                // 情景4.1：叔叔节点存在，并且为红色
                if (uncle != null && isRed(uncle)) {
                    // 将爸爸和叔叔染色为红色，将爷爷染色为红色，并且再以爷爷节点为当前节点，进行下一轮处理
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixup(gparent);
                    return;
                }
                // 情景4.2：叔叔节点不存在，或者为黑色
                if (uncle == null || isBlack(uncle)) {
                    // 插入节点为其父节点的左子节点（LL情况），将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点右旋，就完成了
                    if (node == parent.left) {
                        setBlack(parent);
                        setRed(gparent);
                        rightRotate(gparent);
                        return;
                    }
                    // 情景4.2.2：插入节点为其父节点的右子节点（LR情况）
                    // 以爸爸节点进行一次左旋，得到LL双红的情景（4.2.1），然后指定爸爸节点为当前节点进行下一轮处理
                    if (node == parent.right) {
                        leftRotate(parent);
                        insertFixup(parent);
                        return;
                    }
                }
            } else {    // 父节点为爷爷节点的右子树

                uncle = gparent.left;

                // 情景4.1：叔叔节点存在，并且为红色
                if (uncle != null && isRed(uncle)) {
                    // 将爸爸和叔叔染色为红色，将爷爷染色为红色，并且再以爷爷节点为当前节点，进行下一轮处理
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixup(gparent);
                    return;
                }

                // 情景4.3：叔叔节点不存在，或者为黑色，父节点为爷爷节点的右子树
                if (uncle == null || isBlack(uncle)) {
                    // 情景4.3.1：插入节点为其父节点的右子节点（RR情况），将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点左旋，就完成了
                    if (node == parent.right) {
                        setBlack(parent);
                        setRed(gparent);
                        leftRotate(gparent);
                        return;
                    }

                    // 情景4.3.2：插入节点为其父节点的左子节点（RL情况），
                    // 以爸爸节点进行一次右旋，得到RR双红的情景（4.3.1），然后爸爸节点为当前节点进行下一轮处理
                    if (node == parent.left) {
                        rightRotate(parent);
                        insertFixup(parent);
                        return;
                    }
                }
            }
        }
    }


    /**
     * 左旋方法
     * 左旋示意图：左旋x节点
     *    p                         p
     *    |                         |
     *    x                         y
     *   / \            ---->      / \
     *  lx  y                     x   ry
     *     / \                   / \
     *    ly  ry                lx  ly
     *
     * 1.x的右子节点指向y的左子节点（ly），将y的左子节点的父节点更新为x
     * 2.当x的父节点（不为空时），更新y的父节点为x的父节点，并将x的父节点 指定子节点（当前x的位置）为y
     * 3.将x的父节点更新为y，将y的左子节点更新为x
     */
    private void leftRotate(RBNode x) {
        // 1.x的右子节点指向y的左子节点（ly），将y的左子节点的父节点更新为x
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        // 2.当x的父节点（不为空时），更新y的父节点为x的父节点，并将x的父节点 指定子节点（当前x的位置）为y
        if (x.parent != null) {
            y.parent = x.parent;
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else {
            // 说明 x是根节点，吃时需要更新 y为根节点的引用引用
            this.root = y;
            this.root.parent = null;
        }

        // 3.将x的父节点更新为y，将y的左子节点更新为x
        x.parent = y;
        y.left = x;
    }

    /**
     * 右旋方法
     * 右旋示意图：右旋y节点
     *     p                         p
     *     |                         |
     *     y                         x
     *    / \            ---->      / \
     *   x   ry                    lx  y
     *  / \                           / \
     * lx  ly                        ly  ry
     *
     * 1.将y的左子节点指向x的右子节点（ly），将x的右子节点的父节点更新为y
     * 2.当y的父节点（不为空时），更新x的父节点为y的父节点，并将y的父节点 指定子节点（当前y的位置）为x
     * 3.将y的父节点更新为x，将x的右子节点更新为y
     */
    private void rightRotate(RBNode y) {
        // 1.将y的左子节点指向x的右子节点（ly），将x的右子节点的父节点更新为y
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }

        // 2.当y的父节点（不为空时），更新x的父节点为y的父节点，并将y的父节点 指定子节点（当前y的位置）为x
        if (y.parent != null) {
            x.parent = y.parent;
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        } else {
            this.root = x;
            this.root.parent = null;
        }

        // 3.将y的父节点更新为x，将x的右子节点更新为y
        y.parent = x;
        x.right = y;
    }

    /**
     * 红黑树节点，（父亲节点，左节点，右节点，节点颜色，节点值(K,V) ）
     * @param <K>
     * @param <V>
     */
    static class RBNode <K extends  Comparable<K>, V> {
        private RBNode parent;  // 父亲节点
        private RBNode left;    // 左节点
        private RBNode right;   // 右节点
        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}

