package com.cz.queue;

/**
 * @Description:
 * 环形队列的理解需要借助圆环画图，rear指向的是队列最后一个元素的下一个位置，
 * 这个位置永远是空留出来的，，所以这个位置是动态变化的
 * front指向队列的第一个元素，初始值为0，rear指向队列的最后一个元素的下一个位置，
 * 并且这个位置预留为空
 *
 * 由于环的存在，队列中元素的总个数为：(rear - front + maxSize) % maxSize
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/5/19 9:01
 */
public class CircleArrayDemo {
    public static void main(String[] args) {

    }

}

class CircleArray{
    private int maxSize;    // 表示数组的最大容量
    private int front;      // 指向队列的头
    private int rear;       // 指向队列尾
    private int[] arr;      // 用于存放数据，模拟队列

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[arrMaxSize];
    }

    // 判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    // 获取队列的数据
    public int getQueue(){
        // 判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法取数据");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示所有队列消息
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        // 队列中的有效元素的个数是从front开始计数的，共有size()个元素
        // 并且元素i在自加后可能越界，所以需要对最大值取模
        for (int i = front; i < front + size(); i++){
            System.out.printf("arr[%d]=%d",i % maxSize,arr[i % maxSize]);
        }
    }

    // 求出队列中有效元素的个数
    public int size(){

        return (rear - front + maxSize) % maxSize;
    }

    // 显示队列的头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front];
    }
}
