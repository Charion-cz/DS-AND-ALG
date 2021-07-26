package com.cz.linkList;

import java.util.Stack;

/**
 * @Description: 单项链表
 * @Date: 2021/5/19 9:35
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 按标号顺序加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        // 遍历
        singleLinkedList.list();

        System.out.println();
        singleLinkedList.reverseNode(singleLinkedList.getHead());
        singleLinkedList.list();

        int length = singleLinkedList.getLength(singleLinkedList.getHead());
//        System.out.println( length);
        HeroNode indexNode = singleLinkedList.findIndexNode(singleLinkedList.getHead(), 1);
//        System.out.println(indexNode);

//        reverseNode(singleLinkedList.getHead());
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
//        singleLinkedList.update(newHeroNode);

//        singleLinkedList.del(4);
//        // 遍历
//        singleLinkedList.list();
    }

    // 逆序输出单链表的所有节点
    public static void reverseNode(HeroNode head){
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur.next != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}

class SingleLinkedList{
    // 初始化头结点
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    // 添加
    public void add(HeroNode node){
        // 辅助节点temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while(true){
            // 找到链表的最后
            if(temp.next == null){
                break;
            }
            // 如果没找到，将temp后移
            temp = temp.next;
        }
        // 退出while循环时，temp指向了链表的最后
        // 将最后这个节点的next指向新的节点
        temp.next = node;
    }

    // 顺序添加
    public void addByOrder(HeroNode heroNode){
        // 创建辅助指针
        HeroNode temp = head;
        boolean flag = false; // 标志添加的编号是否存在，默认为false
        while (true){
            // 表示heroNode最大
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no) { // 位置找到，就在temp后面
                break;
            }else if (temp.next.no == heroNode.no) {// 说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next; // 后移，遍历当前链表
        }
        // 判断flag的值
        if(flag) {
            System.out.printf("准备插入的编号%d 已经存在，不能添加",heroNode.no);
            System.out.println();
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 更新
    public void update(HeroNode newHeroNode){
        // 判空
        if (head.next == null){
            System.out.println("链表为空。。");
            return;
        }
        // 头结点不能动
        HeroNode temp = head.next;
        boolean flag = false;
        while(true){
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            System.out.printf("没有找到编号为 %d 的节点\n",newHeroNode.no);
        }
    }

    // 删除
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空。。");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的节点 %d不存在\n",no);
        }
    }

    // 显示链表【遍历】
    public void list(){
        // 判断是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 头结点不能动，创建一个辅助节点来遍历
        HeroNode temp = head.next;
        // 这里首先进行非空判断，对第一个节点来说其实是多余的，当头结点的下一个节点为空的时候，判空条件会直接执行
        // 如果不为空的话，判空条件不会执行，第一轮循环也不会执行
        // 这个if判断是为后面的条件在执行
        // 但是只能放在这里
        // 放在后面的话，会少输出一个节点的信息
        while(true) {
            // 判断是否到了最后
            if(temp == null){
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            // 将next后移，不然是死循环
            temp = temp.next;
        }
    }

    // 显示链表长度
    public int getLength(HeroNode head) {
        HeroNode temp = head;
        if(temp.next == null) {
            return 0;
        }
        int length = 0;
        while(temp.next != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    // 找到倒数第k个节点
    public HeroNode findIndexNode(HeroNode head,int index){
        HeroNode temp = head.next;
        if (temp.next == null) {
            return null;
        }
        int length = getLength(head);
        if (index <= 0 || index > length) {
            return null;
        }
        for (int i = 0; i < length - index; i++){
            temp = temp.next;
        }
        return temp;
    }

    // 链表反转
    public void reverseNode(HeroNode head){
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");
        while(cur != null){
            // 保存当前节点的下一个节点
            next = cur.next;
            // cur的下一个节点指向新链表的第一个节点，
            // 在第二轮循环的时候，将第二个节点的next指向已经接好的第一个节点
            // 也就是将2号指向1号
            cur.next = reverseHead.next;
            // 将头结点指向2号
            reverseHead.next = cur;
            // 让cur后移
            cur = next;
        }
        // 将头结点替换，实现单链表的反转
        head.next = reverseHead.next;
    }
}

// 节点数据，每一个HeroNode对象都是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;       // 指向下一个节点
    // 构造器
    public HeroNode(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }
}
