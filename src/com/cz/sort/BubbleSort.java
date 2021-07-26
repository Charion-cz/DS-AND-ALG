package com.cz.sort;

import java.util.Arrays;

/**
 * @Description: 冒泡排序
 * 冒泡排序（Bubble Sorting）的基本思想是：通过对待排序序列从前向后（从下标较小的元素开始）,
 * 依次比较 相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。
 * 因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，
 * 因此要在 排序过程中设置一个标志 flag 判断元素是否进行过交换。从而减少不必要的比较。
 * @Date: 2021/5/26 16:09
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {-1,4,2,-4,18};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        boolean flag = false;
        int temp = 0;
        for (int i = 0; i<arr.length-1; i++) {
            for (int j=0; j<arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    flag = true;
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
            if(!flag) {
                break;
            }else {
                flag = false;
            }
        }
    }
}
