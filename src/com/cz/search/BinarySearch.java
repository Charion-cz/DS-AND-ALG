package com.cz.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 二分查找
 * @Date: 2021/7/13 11:50
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {-1,3,5,8,12,15,53,53,53,53,55,73,123};
        int findVal = 53;
//        int binarySearch = binarySearch(arr, 0, arr.length - 1, findVal);
//        System.out.println("findVAl的下标是：" + binarySearch);
        List<Integer> list = binarySearch2(arr,0,arr.length-1,53);
        System.out.println(list);
    }

    /**
     * 首先设置退出循环的条件：就是左边的索引大于右边的索引，没有找到数据
     * 当中间值大于待查找值时，将查找范围缩小到中间值到最右边
     * 当中间值小于待查找值时，将查找范围缩小到最左边到中间值
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int binarySearch(int[] arr,int left,int right,int findVal) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right)/2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch(arr, mid+1, right, findVal);
        }else if (findVal < midVal) {
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

    public static List<Integer> binarySearch2(int[] arr,int left,int right,int findVal) {
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (midVal > findVal) {
            return binarySearch2(arr,mid+1,right,findVal);
        }else if (midVal < findVal) {
            return binarySearch2(arr,left,mid - 1,findVal);
        }else {
            List list = new ArrayList<Integer>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp--;
            }

            temp = mid + 1;
            while (true) {
                if (temp > arr.length-1 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp++;
            }

            return list;
        }
    }
}
