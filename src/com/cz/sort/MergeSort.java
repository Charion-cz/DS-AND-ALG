package com.cz.sort;

import java.util.Arrays;

/**
 * @Description: 归并排序
 * @Date: 2021/7/6 10:48
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {4,2,-1,0,-6,7,13,9};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分割整个数组
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right) {
            int mid = (left + right)/2;
            // 分割左边
            mergeSort(arr,left,mid,temp);
            // 分割右边
            mergeSort(arr,mid+1,right,temp);
            // 合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并数组
     * 这里分为三步：
     *  1：先把左右两边(有序)的数据按照规则填充到 temp数组,直到左右两边的有序序列，有一边处理完毕为止
     * @param arr 原数组
     * @param left 左边索引
     * @param mid 中间值
     * @param right 右边索引
     * @param temp 工具数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;       // 左边数组的索引
        int j = mid+1;      // 右边数组的索引
        int t = 0;          // temp数组的索引
        while (i <= mid && j <= right) {
            // 将左右两边数组进行比较
            // 将小的值赋给temp
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            }else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        // 赋完后会有一个数组有剩余值，将剩余值赋给temp
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        // 这里拷贝值是拷贝传进来的left到right区间的值，不是整个数组
        // 所以需要定义tempLeft来接收这个区间
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
