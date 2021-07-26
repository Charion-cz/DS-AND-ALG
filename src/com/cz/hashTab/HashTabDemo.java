package com.cz.hashTab;

import java.util.Scanner;

/**
 * @Description: hashTab实现
 * @Date: 2021/7/18 16:30
 */
public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;// 表示有多少条链表

    /**
     * 构造函数
     * @param size
     */
    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i<size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加emp
     * @param emp
     */
    public void add(Emp emp) {
        // 确定emp在哪一条链表
        int empLinkedListNO = hashFun(emp.id);
        // 将emp添加到这条链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    /**
     * 遍历所有链表
     */
    public void list(){
        for (int i=0; i< size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 根据id查找emp节点
     * @param id
     * @return
     */
    public void findById (int id) {
        // 确定在哪一条链表中
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findById(id);
        if(emp != null) {
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        }else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    /**
     * 编写散列函数，采用最简单的取模方法
     */
    public int hashFun(int id) {
        return id % size;
    }
}

/**
 * 创建单个节点数据
 */
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

/**
 * 定义数组每个位置的链表
 */
class EmpLinkedList{
    // 头指针，指向第一个节点，是有效节点
    private Emp head;   //  默认为空

    /**
     * 添加节点
     * @param emp
     */
    public void add(Emp emp) {
        // 如果第一个头结点为空，那就添加到头结点
        if (head == null) {
            head = emp;
            return;
        }
        // 头结点不为空，将emp添加到最后面，定义一个辅助指针遍历链表
        Emp temp = head;
        while(true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // 退出后将emp加入到链表最后面
        temp.next = emp;
    }

    /**
     * 遍历第no个节点代表的链表
     * @param no
     */
    public void list (int no) {
        if (head == null) {
            System.out.println("第"+(no + 1)+"条链表为空");
            return;
        }
        System.out.print("第"+(no + 1)+"条链表的信息为:");
        Emp temp = head;

        while (true) {
            System.out.printf("==> 第%d个节点的name是%s\t",temp.id,temp.name);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 根据id查找节点
     * @param id
     * @return
     */
    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表为空~~");
            return null;
        }

        Emp temp = head;
        while (true) {
            if (temp.id == id){
                break;
            }
            if (temp.next == null) {
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }
}
