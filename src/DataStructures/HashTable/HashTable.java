package DataStructures.HashTable;

import java.util.Scanner;

/**
 * 哈希表
 */
//定义一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//定义一条链表
class EmpLinkedList{
    private Emp head;//定义头指针

    //添加雇员，这里默认id自增长，直接添加到末尾即可
    public void add(Emp emp){
        //如果是第一个雇员
        if (head == null){
            head = emp;
            return;
        }
        Emp curEmp = head;//使用辅助指针
        while (true){//找到链表末尾
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;//后移
        }
        curEmp.next = emp;
    }

    //遍历链表
    public void list(int no){
        if (head == null){//如果为空
            System.out.println("第" + (no+1)+" 条链表为空");
            return;
        }
        Emp curEmp = head;//使用辅助指针
        System.out.print("第" + (no+1)+" 条链表信息为");
        while (true){//输出每一条链表的信息
            System.out.print(" -> id="+curEmp.id + " name=" + curEmp.name);
            if (curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;//后移
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findById(int id){
        //判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;//辅助指针
        while (true){
            if (curEmp.id == id){//找到直接跳出循环
                break;
            }
            if (curEmp.next == null){//找到末尾还是找不到
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //按id删除某雇员
    public boolean delEmp(int no){
        //判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return false;
        }
        //定义辅助结点
        Emp preEmp = null;
        Emp curEmp = head;

        while (curEmp != null && curEmp.id != no){//只要没找到，就一直往后找
            preEmp = curEmp;
            curEmp = curEmp.next;
        }

        if (preEmp == null)//说明是第一个结点
            head = head.next;
        else
            preEmp.next = curEmp.next;//先前结点连到当前结点的下一个

        return true;
    }
}

//定义一个哈希表
class HashTab{
    private EmpLinkedList[] empLinkedLists;
    private int size;

    //构造函数
    public HashTab(int size){
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //添加员工
    public void add(Emp emp){
        //根据员工id得到员工应当添加到哪条链表
        int emLinkedListId = hashFun(emp.id);
        //添加到对应的链表中
        empLinkedLists[emLinkedListId].add(emp);
    }

    //遍历所有链表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    //查找
    public void findById(int id){
        //使用散列表确定到哪条链表查找
        int number = hashFun(id);
        Emp emp = empLinkedLists[number].findById(id);
        if (emp != null){
            System.out.printf("在第%d条链表中找到员工，id=%d\n",(number+1),id);
        }else {
            System.out.println("找不到");
        }
    }

    //删除
    public void delById(int id){
        //使用散列表确定到哪条链表查找
        int number =hashFun(id);
        boolean flag = empLinkedLists[number].delEmp(id);
        if (flag){
            System.out.println("成功删除 " + id + " 号员工");
        }else {
            System.out.println("未成功删除 " + id + " 号员工");
        }
    }

    //散列函数
    public int hashFun(int id){
        return id % size;
    }
}

public class HashTable {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        //一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("del:删除雇员");
            System.out.println("exit:退出系统");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入查找id");
                    int findid = scanner.nextInt();
                    hashTab.findById(findid);
                    break;
                case "del":
                    System.out.println("输入删除id");
                    int delid = scanner.nextInt();
                    hashTab.delById(delid);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
