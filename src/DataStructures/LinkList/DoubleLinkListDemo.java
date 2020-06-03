package DataStructures.LinkList;

/**
 * 实现双链表
 */
//定义结点类
class Node2{
    public int number;
    public String name;
    public String nickName;
    public Node2 next;//指向下一个结点
    public Node2 pre;//指向前一个结点

    //构造函数
    public Node2(int number, String name, String nickName) {
        this.number = number;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方法，重写toString
    @Override
    public String toString() {
        return "Node{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

//定义双向链表类
class DoubleLinkList{
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    Node2 head = new Node2(0,"","");

    public Node2 getHead() {
        return head;
    }

    //显示链表
    public void showList(){
        //判断链表是否为空
        if (isLinkListNull(head)){
            System.out.println("链表为空");
            return;
        }
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        Node2 temp = head.next;
        while (true){
            //判断是否到链表最后
            if (temp == null) break;
            //如果没有到最后, 将输出temp信息
            System.out.println(temp);
            //temp后移
            temp = temp.next;
        }
    }

    //判断链表是否到尾部
    public boolean isLinkListNull(Node2 node) {
        if (node.next == null) return true;
        return false;
    }

    //按添加顺序添加节点到双向链表
    public void add(Node2 newNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        Node2 temp = head;
        //找到当前链表的最后节点
        while (true){
            //找到链表的最后
            if (isLinkListNull(temp)) break;
            //如果没有到最后, 将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next = newNode;
        newNode.pre = temp;
    }

    //修改结点信息
    public void update(Node2 newNode){
        //根据newNode的number来修改
        //判断是否为空
        if (isLinkListNull(head)) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的结点编号
        Node2 temp = head.next;//定义辅助结点
        boolean flag = false;//表示是否找到该节点
        while (true){
            if (temp == null) break;//遍历完该链表，找不到
            if (temp.number == newNode.number){
                flag = true;
                break;
            }
            temp = temp.next;//后移
        }
        //根据flag判断是否找到
        if (flag){
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        }else {
            System.out.println("没有找到" + newNode.name + "号结点");
        }
    }

    //删除节点
    public void delete(int number){
        //判断链表是否为空
        if (isLinkListNull(head)){
            System.out.println("链表为空");
            return;
        }
        Node2 temp = head.next;//定义辅助结点
        boolean flag = false;//标志是否找到
        while (true){
            if (temp == null) break;//已经到链表的最后
            if (temp.number == number){//找到结点
                flag = true;
                break;
            }
            temp = temp.next;//后移
        }
        //判断是否找到结点
        if (flag){//可以删除
                temp.pre.next = temp.next;
            if (temp.next != null) {//防止temp是最后一个结点出现空指针异常
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("不存在%d号结点",number);
        }
    }
}

public class DoubleLinkListDemo {
    public static void main(String[] args) {
        //创建结点
        Node2 node1 = new Node2(1,"1.1","1.1");
        Node2 node2 = new Node2(2,"2.1","2.1");
        Node2 node3 = new Node2(3,"3.1","3.1");
        Node2 node4 = new Node2(4,"4.1","4.1");
        //创建双向链表
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        //添加结点
        doubleLinkList.add(node1);
        doubleLinkList.add(node2);
        doubleLinkList.add(node3);
        doubleLinkList.add(node4);
        doubleLinkList.showList();
        Node2 node5 = new Node2(4,"4.2","4.2");
        doubleLinkList.update(node5);
        doubleLinkList.showList();
        doubleLinkList.delete(4);
        doubleLinkList.showList();
    }
}
