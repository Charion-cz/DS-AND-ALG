package com.cz;

import java.util.Arrays;

/**
 * @Description:
 * @Date: 2021/6/30 9:59
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {-1,10,-12,7,3};
//        selectSort(arr);
//        insertSort(arr);
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i=0; i<arr.length-1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j<arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i=1; i<arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i-1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex+1 != i) {
                arr[insertIndex+1] = insertVal;
            }
        }
    }

    /**
     * 快速排序
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int key = arr[start];
            int i = start;
            for (int j=start+1;j<= end; j++) {
                if (key > arr[j]) {
                    swap(arr,j,++i);
                }
            }
            arr[start] = arr[i];
            arr[i] = key;
            quickSort(arr,start,i-1);
            quickSort(arr,i+1,end);
        }
    }

    /**
     * ^位异或运算
     * @param arr
     * @param j
     * @param i
     */
    private static void swap(int[] arr, int j, int i) {
        if (i != j) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }

}