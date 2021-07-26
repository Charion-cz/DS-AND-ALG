package com.cz.sort;

import java.util.Arrays;

/**
 * @Description: 选择排序
 * 第一次从 arr[0]~arr[n-1]中选取最小值，与 arr[0]交换
 * 第二次从 arr[1]~arr[n-1]中选取最小值，与 arr[1]交换
 * 第三次从 arr[2]~arr[n-1]中选取最小值，与 arr[2]交换…
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/5/26 17:44
 */
public class SelectSort {
    public static void main(String[] args) {

        int[] arr = {5,7,4,1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {

        for (int i=0; i<arr.length - 1; i++){
            // 首先将第一个值设为最小
            int min = arr[i];        // 接收每次比较中最小的值
            int minIndex = i;   // 接收每次比较中最小的值的序号
            for (int j=i+1; j<arr.length; j++) { // 和后面的值做比较
                if (min > arr[j]) {          // 当最小值大于后面的比较值
                    min = arr[j];               // 将小的值赋值给临时变量
                    minIndex = j;
                }
            }
            // 内层循环结束后，选出第一次大循环的最小值
            // 如果最小值所在的位置不是待比较的位置，就进行置换
            if (minIndex != i) {
                arr[minIndex] = arr[i]; // 将大的值换到后面去
                arr[i] = min;           // 将最小值赋给i
            }
        }
    }
}
