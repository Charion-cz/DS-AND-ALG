package com.cz.linkList;

/**
 * @Description: 约瑟夫环
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/5/25 10:35
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1,2,5);
    }
}

// 创建一个环形单项列表
class CircleSingleLinkedList{
    // 创建一个first节点，当前没有编号
    private Boy first = null;

    // 添加小孩节点，构建成一个环形链表
    public void addBoy(int nums) {
        if(nums < 0) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; // 创建辅助指针
        // 通过for循环创建环形链表
        for (int i = 1; i <= nums ; i++) {
            // 根据编号创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个节点
            if(i == 1){
                first = boy;
                first.setNext(first);// 构成环
                curBoy = first; // 让curBoy指向第一个小孩
            }else{
                curBoy.setNext(boy); // 将最后一个节点指向新创建的节点
                boy.setNext(first); // 将新的节点指向第一个节点
                curBoy = boy;   // 将辅助指针指向新的节点
            }
        }
    }

    public void showBoy(){
        // 判断是否为空
        if(first == null) {
            System.out.println("空~~");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n",curBoy.getNo());
            if (curBoy.getNext() == first){ // 说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();  // curBoy后移
        }
    }

    public void countBoy(int startNo,int countNum,int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("产生·参数输入有误，请重新输入");
            return;
        }
        // 创建辅助指针，帮助完成小孩出圈
        Boy helper = first;
        // 将辅助指针指向环形变量的最后一个节点
        while(true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前，先将first和helper移动k-1次
        // 因为first已经指向了第一个节点，所以移动k-1次的时候，
        // 会将first直接指向准备出圈的那个元素，这是为第一步做准备
        for (int j = 0;j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 当小孩开始报数时，让first和helper指针同时移动 m-1 次，然后出圈
        // 也就是数m下
        // 这里是一个循环操作，直到圈里只有一个节点
        while (true) {
            if (helper == first) { // 说明圈里只有一个节点
                break;
            }
            // 让first和helper指针同时移动countNum-1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩 %d 出圈 \n",first.getNo());
            // 然后将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号是%d \n",first.getNo());
    }
}

class Boy{
    private int no;
    private Boy next;
    public Boy (int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }
    public Boy getNext() {
        return next;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public void setNext(Boy next) {
        this.next = next;
    }
}
