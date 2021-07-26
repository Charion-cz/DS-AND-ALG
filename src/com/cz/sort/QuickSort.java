package com.cz.sort;

import java.util.Arrays;

/**
 * @Description: 快速排序
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/7/5 14:57
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {4,12,-2,0,3,7,0};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = arr[(left+right)/2];
        // 确保比pivot大的值放在了右边，比pivot小的放在了左边
        while (l < r) {
            // 从左边开始找比pivot大的值，没找到就找下一个
            while (arr[l] < pivot) {
                l++;
            }
            // 从右边开始找比pivot小的值，没找到就找下一个
            while (arr[r] > pivot) {
                r--;
            }
            // 相等就是两个指针在移动过程中相遇了，找完了这一整趟，可以退出了
            if (l >= r) {
                break;
            }
            // 前面的while条件找到了交换值，在这里进行交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后，发现这个 arr[l] == pivot值相等 r--,前移
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换完后，发现这个 arr[l] == pivot值相等 l++,后移
            if (arr[r] == pivot) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr,left,r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr,l,right);
        }
    }
}
