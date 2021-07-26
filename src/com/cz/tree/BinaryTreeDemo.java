package com.cz.tree;

/**
 * @Description: 二叉树
 * @Date: 2021/7/20 11:42
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        binaryTree.preOrder();
    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    private HeroNode left;
    private HeroNode right;

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