package com.cz.recursion;

/**
 * @Description: 八皇后问题
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/5/26 11:13
 */
public class Queue {

    int max = 8;
    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        // 测试
        Queue queue = new Queue();
        queue.check(0);
        System.out.printf("一共有 %d 种解法",count);
    }

    // 放置第n个皇后
    private void  check(int n) {
        if (n == max) { // 是否放置到了最后一个皇后
            print();
            return;
        }
        // 从每一行的第一个位置放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后 n 放到该行的第一列
            array[n] = i;
            // 判断当前放置第 n 个皇后到i列时，是否冲突
            if (judge(n)) { // 不冲突
                check(n+1);
            }
            // 如果冲突，就继续执行array[n] = i,即将第n个皇后，放置在本行的后移的一个位置
        }
    }

    // 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的冲突
    private boolean judge(int n) {
        for (int i = 0; i< n;i++) {
            // array[i] == array[n]判断第n个皇后是否和第n-1个皇后是否在同一列
            // Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个在同一斜线
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0;i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
