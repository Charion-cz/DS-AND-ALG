package com.cz.tree;

/**
 * @Description:
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/7/24 14:58
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        System.out.println(arr);
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(){
        this.preOrder(0);
    }
    /**
     *
     * @param index 当前的根节点
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0){
            System.out.println("树为空，无法遍历");
        }
        System.out.println(arr[index]);
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }
}