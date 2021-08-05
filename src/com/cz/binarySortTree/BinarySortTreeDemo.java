package com.cz.binarySortTree;

/**
 * @Description: 二叉排序树
 * @Version:
 * @Date: 2021/8/1 14:45
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinaryTree binaryTree = new BinaryTree();
        for (int i:arr) {
            binaryTree.add(new Node(i));
        }
        binaryTree.infixOrder();
        System.out.println();
        binaryTree.delNode(10);
        binaryTree.infixOrder();
    }
}

class BinaryTree{
    private Node root;

    // 查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        }else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    public int delRightTreeMin(Node node) {
        Node target = node;
        while(target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }
    /**
     *删除节点
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        }else {
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
                }else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            }else if (targetNode.left != null && targetNode.right != null) { // 删除有两颗子树的节点
                int min = delRightTreeMin(targetNode.right);
                targetNode.value = min;
            }else { // 删除有一颗子节点的数
                // 如果要删除的节点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        // 如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }
                }else { // 要删除的节点有右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }
                }

            }
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        }else {
            root.add(node);
        }
    }

    public void infixOrder () {
        if (root != null) {
            root.infixOrder();
        }else {
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
