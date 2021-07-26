package com.cz.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 逆波兰表达式
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/5/25 19:40
 */
public class PolandNotation {
    public static void main(String[] args) {
        String suffixException = "3 4 + 5 * 6 -";

        List<String> list = getListString(suffixException);
        int res = calculate(list);
        System.out.println(res);
    }

    // 将逆波兰表达式依次将数据放入ArrayList中
    public static List<String> getListString(String suffixException) {
        String[] split = suffixException.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele:split) {
            list.add(ele);
        }
        return list;
    }

    // 完成对逆波兰表达式的计算
    public static int calculate(List<String> ls){
        // 创建一个栈
        Stack<String> stack = new Stack<String>();
        for (String item : ls) {
            // 用正则表达式取出数
            if (item.matches("\\d+")) { // 匹配的是多位数
                // 入栈
                stack.push(item);
            }else {
                // pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")) {
                    res = num1 - num2;
                }else if (item.equals("*")) {
                    res = num1 * num2;
                }else if (item.equals("/")) {
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误~~");
                }
                // 把res 入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
