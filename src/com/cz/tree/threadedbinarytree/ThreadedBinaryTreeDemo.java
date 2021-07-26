package com.cz.tree.threadedbinarytree;

/**
 * @Description:
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/7/25 15:57
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试: 以 10 号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10 号结点的前驱结点是 =" + leftNode); //3
        System.out.println("10 号结点的后继结点是=" + rightNode); //1
        threadedBinaryTree.threadedList();
        System.out.println();
    }
}

// 实现线索化功能的二叉树
class ThreadedBinaryTree{

    private HeroNode root;

    // 为了实现线索化，需要创建要给指定当前节点的前去节点的指针
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    public void threadedList() {
        HeroNode node = root;

        while (node != null) {
            // 找到第一个节点并输出
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node);

            // 如果当前节点指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 对二叉树进行线索化
     * 线索化每一个节点时，在当前循环处理前驱节点
     * 在当前节点变成pre节点时，在下一次循环处理后继节点
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {

        if (node == null) {
            return;
        }
        // 线索化左子树
        threadedNodes(node.getLeft());

        // 线索化当前节点
        // 处理前驱节点
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前节点的左指针类型，指向前驱节点
            node.setLeftType(1);
        }
        // 处理后继节点,这里是通过pre在设置上一个node节点的后继节点
        if (pre != null && pre.getRight() == null) {
            // 让前驱节点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        // 每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        // 线索化右子树
        threadedNodes(node.getRight());
    }

    // 前序遍历整个树
    public void preOrder(){
        if (this.root != null) {
            this.root.preOrder();
        }else {
            System.out.println("树为空~~~");
        }
    }

    // 中序遍历整个树
    public void infixOrder(){
        if (this.root != null) {
            this.root.infixOrder();
        }else {
            System.out.println("树为空~~~");
        }
    }

    // 后序遍历整个树
    public void postOrder(){
        if (this.root != null) {
            this.root.postOrder();
        }else {
            System.out.println("树为空~~~");
        }
    }

    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("空树。无法删除~~~");
        }
    }
}
// 节点属性和方法
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;  // 默认为null
    private HeroNode right; // 默认为null

    // 如果为0，就表示是左子树，如果是1，就表示指向前驱节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历
    public void preOrder(){
        // 输出根节点
        System.out.println(this);
        // 向左子树遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 向有子树遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    // 中序遍历
    public void infixOrder(){
        // 如果左子树不为空，向左遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出中节点
        System.out.println(this);
        // 向右遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    // 后序遍历
    public void postOrder(){
        // 如果左子树不为空，向左遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 向右遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
        // 输出中节点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * 先判断当前节点是否符合条件，然后遍历左子树，然后遍历由子树
     * @param no 待查找编号
     * @return 返回节点
     */
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode temp = null;
        if (this.left != null) {
            temp = this.left.preOrderSearch(no);
        }
        if (temp != null){
            return temp;
        }
        if (this.right != null) {
            temp = this.right.preOrderSearch(no);
        }
        return temp;
    }

    /**
     * 中序遍历查找，先遍历左子树，再遍历根节点，再遍历右子树
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode temp = null;
        if (this.left != null) {
            temp = this.left.infixOrderSearch(no);
        }
        if (temp != null) {
            return temp;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            temp = this.right.infixOrderSearch(no);
        }
        return temp;
    }

    /**
     * 后序遍历查找
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode temp = null;
        if (this.left != null) {
            temp = this.left.preOrderSearch(no);
        }
        if (this.right != null) {
            temp = this.right.postOrderSearch(no);
        }
        if (this.no == no) {
            return this;
        }
        return temp;
    }

    /**
     * 删除节点
     * @param no
     */
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}