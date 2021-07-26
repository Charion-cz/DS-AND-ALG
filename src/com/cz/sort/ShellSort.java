package com.cz.sort;

import javax.swing.*;
import java.util.Arrays;

/**
 * @Description: shell排序
 * shell排序分为3层循环
 * 第一层循环是对待排数组进行分组，首先将数组分为arr.length/2组，然后后面每次分组的组数是上一次组数除以2
 * 第二层循环是在第一层循环的基础上遍历每一个组
 * 第三层循环是第二层循环的基础上循环每组的所有数据
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/7/4 16:34
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {4,2,6,9,5,0,1,8,3,7};
//        shellSort1(arr);
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 交换法
     * @param arr
     */
    public static void shellSort1(int[] arr) {
        for(int gap = arr.length/2;gap >0; gap /= 2) {
            for (int i = gap; i<arr.length; i++) {
                for (int j = i-gap;j >= 0; j -= gap) {
                    if (arr[j] > arr[j+gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 移位法
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            for (int i=gap; i<arr.length; i++) {
                int j = i;
                int temp = arr[i];
                if (arr[j] < arr[j-gap]) {
                    while (j-gap >= 0 && temp < arr[j-gap]) {
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

}
