package com.cz.avl;


/**
 * @Description:
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/8/3 9:19
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = { 10, 12, 8, 9, 7, 6 };
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        BinaryTree binaryTree = new BinaryTree();
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(new Node(arr[i]));
        }

        binaryTree.infixOrder();
        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + binaryTree.getRoot().height());
        System.out.println("树的左子树高度=" + binaryTree.getRoot().leftHeight());
        System.out.println("树的右子树高度=" + binaryTree.getRoot().rightHeight());
        System.out.println("当前的根结点=" + binaryTree.getRoot());
    }
}

class BinaryTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    // 查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    /**
     * 删除节点
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node parent = searchParent(value);
            // 如果删除的是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断targetNode是左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 删除有两颗子树的节点
                int min = delRightTreeMin(targetNode.right);
                targetNode.value = min;
            } else { // 删除有一颗子节点的数
                // 如果要删除的节点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        // 如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { // 要删除的节点有右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }

            }
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("节点为空，无法遍历");
        }
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * @return 返回当前节点的高度
     */
    public int height(){
        return Math.max(left == null? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * @return 返回左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }else {
            return left.height();
        }
    }

    /**
     * @return 返回右子树的高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }else {
            return right.height();
        }
    }

    /**
     * 左旋方法
     */
    private void leftRotate(){

        // 以当前根节点的值创建新的节点
        Node newNode = new Node(this.value);
        // 把新的节点的左子树设置成当前节点的左子树
        newNode.left = this.left;
        // 把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = this.right.left;
        // 把当前节点的值替换成右子节点的值
        this.value = right.value;
        // 把当前节点的右子树设置成当前节点右子树的右子树
        this.right = this.right.right;
        // 把当前节点的左子树设置成新的节点
        this.left = newNode;
    }

    /**
     * 右旋方法
     */
    private void rightRotate(){

        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;

    }


    // 查找要删除的节点
    public Node search(int value) {
        if (this.value == value) {
            return this;
        }else if (this.value > value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        }else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    // 查找要删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)){
            return this;
        }else {
            if (this.left != null && value < this.value) {
                // 向左子树递归
                return this.left.searchParent(value);
            }else if (this.right != null && value >= this.value) {
                // 向右子树递归
                return this.right.searchParent(value);
            }else {
                // 没有找到父节点
                return null;
            }
        }
    }

    /**
     * 添加节点
     * @param node
     */
    public void add(Node node) {
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            }else {
                this.left.add(node);
            }
        }
        if (node.value > this.value) {
            if (this.right == null) {
                this.right = node;
            }else {
                this.right.add(node);
            }
        }

        // 左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()){
                // 先对有子节点进行有旋转
                right.rightRotate();
                // 然后对当前节点进行左旋转
                leftRotate();
            }
            // 直接左旋转
            leftRotate();
            return;
        }

        // 当添加完一个节点后，如果左子树的高度 - 右子树的高度 > 1,右旋转
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
                rightRotate();
            }
            rightRotate();
            return;
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this.value);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}