package com.cz.sort;

import java.util.Arrays;

/**
 * @Description: 桶排序
 * @Date: 2021/7/12 16:20
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {12, 412, 5, 3881, 64, 88, 965};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int maxLength = (max + "").length();
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                // 求出最后一位数，放在相应的桶
                int digitOfElement = (arr[j] / n) % 10;
                // 这里表示放在第digitOfElement个桶的第0个位置
                // bucketElementCounts是数组，没有初始化之前，每个值都是0，所以这里表示将arr[j]放在第零个位置
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
    }
}
