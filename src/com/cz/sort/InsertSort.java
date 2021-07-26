package com.cz.sort;

import java.util.Arrays;

/**
 * @Description: 插入排序
 * 把 n 个待排序的元素看成为一个有序表和一个无序表
 * 开始时有序表中只包含一个元素，无序表中包含有 n-1 个元素
 * 排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较
 * 将它插入到有序表中的适当位置，使之成为新的有序表
 * @Date: 2021/5/27 10:49
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {5,-2,6,3};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i< arr.length; i++) {
            // 定义待插入的数
            int insertVal = arr[i];
            // 定义待插入的位置，在待插入的数的前一个
            int insertIndex = i -1;
            /*
            给insertVal找插入的位置
            insertIndex >= 0 保证给insertVal找到插入位置，并且数组不会越界。如果等于0，就说明插入数最小，在开头
            arr[insertIndex] > insertVal 表示待插入的数还没有找到位置，这里是将待插入的是和有序数组的某个值进行大小对比
            待插数大于有序数组才满足条件
            否则将arr[insertIndex] 后移一位，同时将定位往前找更小的
             */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]  ) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 当退出while循环时，说明插入的位置已经找到,并且index是待插入的前有一个位置，所以加一
            if (insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }

            System.out.println("第"+i+"轮排序");
            System.out.println(Arrays.toString(arr));
        }
    }
}
