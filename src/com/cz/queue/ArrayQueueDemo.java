package com.cz.queue;

/**
 * @Description: 队列
 * @Date: 2021/5/18 17:15
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {

    }
}

class ArrayQueue{
    private int maxSize;    // 表示数组的最大容量
    private int front;      // 指向队列的头
    private int rear;       // 指向队列尾
    private int[] arr;      // 用于存放数据，模拟队列

    // 创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    // 判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n){
        // 判断队列是否满
        if(isFull()){
            System.out.println("队列满了~~");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    // 获取队列是数据
    public int getQueue(){
        // 判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法取数据");
        }
        front++;
        return arr[front];
    }

    // 显示所有队列消息
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        for (int i=0; i<arr.length; i++){
            System.out.printf("arr[%d]=%d",i,arr[i]);
        }
    }

    // 显示队列的头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front+1];
    }
}