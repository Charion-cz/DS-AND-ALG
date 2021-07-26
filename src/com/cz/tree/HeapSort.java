package com.cz.tree;

import java.util.Arrays;

/**
 * @Description: 堆排序
 * @Date: 2021/7/26 18:27
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 6, 9, 8};
        heapSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        // 将整个数组调整成大顶堆
        for (int i=arr.length/2 - 1; i>=0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 将堆顶元素与最后一个元素交换，让最大的数沉到数组末尾
        for (int j=arr.length-1; j>0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // 这里是为了将第一个叶子节点和它的两个子树进行调整，确保根节点是最大的数
            adjustHeap(arr,0,j);
        }

    }

    /**
     * 最后一个非叶子节点所在的子树调整成大顶堆
     * @param arr 待调整的数组
     * @param i 非叶子节点在数组中的位置
     * @param length 待排数组的长度，这个值是逐渐递减的
     */
    public static void adjustHeap(int[] arr,int i, int length) {
        // 取出当前待排非叶子节点的值
        int temp = arr[i];
        // 开始调整，k是i的左子节点
        for (int k = i*2 + 1; k < length; k = k*2 + 1) {
            // 将左子节点和右子节点进行比较
            if (arr[k] < arr[k+1] && k+1 < length) {
                k++;
            }
            // 将两个子节点的较大值与非叶子结点i进行比较
            if (arr[k] > temp) {
                // 把较大的赋值给i
                arr[i] = arr[k];
                // 同时将子节点的位置保留
                i = k;
            }else {
                // 如果非叶子节点最大，直接退出for循环
                break;
            }
        }
        // 如果非叶子节点本来就是最大的，这里进行交换就不改变i的值
        // 如果进行了交换，这里就是将较小的非叶子节点换到叶子节点k上，上面做交换的时候，将k的值赋值给了i
        arr[i] = temp;
    }
}
