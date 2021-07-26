package com.cz.linkList;

/**
 * @Description: 双向链表
 * @Version:
 * @author: zhuang.chen@hand-china.com
 * @Date: 2021/5/25 9:33
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();
        System.out.println();
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");

        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();
        System.out.println();
        doubleLinkedList.del(4);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList{
    // 初始化头结点
    private HeroNode2 head = new HeroNode2(0,"","");
    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    public void add(HeroNode2 node){
        // 辅助节点temp
        HeroNode2 temp = head;
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
        node.pre = temp;
    }

    // 修改双向列表节点的值
    public void update(HeroNode2 newHeroNode){
        // 判空
        if (head.next == null){
            System.out.println("链表为空。。");
            return;
        }
        // 头结点不能动
        HeroNode2 temp = head.next;
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
        HeroNode2 temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) { // 如果是最后一个节点，就不需要执行下面的语句，不然会空指针异常
                temp.next.pre = temp.pre;
            }
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
        HeroNode2 temp = head.next;
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
}

class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;
    // 构造器
    public HeroNode2(int no,String name,String nickName){
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
