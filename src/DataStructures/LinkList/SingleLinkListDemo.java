package DataStructures.LinkList;

import java.util.Stack;

/**
 * 实现单链表
 */
//定义结点类
class Node{
    public int number;
    public String name;
    public String nickName;
    public Node next;//指向下一个结点

    //构造函数
    public Node(int number, String name, String nickName) {
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

//定义链表类
class SingleLinkList{
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    Node head = new Node(0,"","");

    //按添加顺序添加节点到单向链表
    public void add(Node newNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        Node temp = head;
        //找到当前链表的最后节点
        while (true){
            //找到链表的最后
            if (isLinkListNull(temp)) break;
            //如果没有到最后, 将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后，将最后这个节点的next指向新的节点
        temp.next = newNode;
    }

    //显示链表
    public void showList(){
        //判断链表是否为空
        if (isLinkListNull(head)){
            System.out.println("链表为空");
            return;
        }
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        Node temp = head.next;
        while (true){
            //判断是否到链表最后
            if (temp == null) break;
            //如果没有到最后, 将输出temp信息
            System.out.println(temp);
            //temp后移
            temp = temp.next;
        }
    }

    //按序号添加结点
    public void addByOrder(Node newNode){
        /**因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
         * 因为单链表，因为我们找的temp是位于添加位置的前一个节点，否则插入不了*/
        Node temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认为false
        while (true){
            if (isLinkListNull(temp)) break;//说明temp已经在链表的最后
            if (temp.next.number > newNode.number){//位置找到，就在temp的后面插入
                break;
            }else if (temp.next.number == newNode.number){//说明希望添加的Node的序号已然存在
                flag = true;
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if (flag){
            //不能添加，说明编号存在
            System.out.println(newNode.number+ "号结点已经存在");
        }else {
            //插入到链表中, temp的后面
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    //修改结点信息
    public void update(Node newNode){
        //根据newNode的number来修改
        //判断是否为空
        if (isLinkListNull(head)) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的结点编号
        Node temp = head.next;//定义辅助结点
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
        Node temp = head.next;//定义辅助结点
        boolean flag = false;//标志是否找到
        while (true){
            if (isLinkListNull(temp)) break;//已经到链表的最后
            if (temp.next.number == number){//找到结点
                flag = true;
                break;
            }
            temp = temp.next;//后移
        }
        //判断是否找到结点
        if (flag){//可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("不存在%d号结点",number);
        }
    }

    //判断链表是否到尾部
    public boolean isLinkListNull(Node node) {
        if (node.next == null) return true;
        return false;
    }

    /**
     * 求单链表中有效节点的个数
     * @param head 链表的头节点
     * @return 有效结点个数
     */
    public int getLength(Node head){
        if (isLinkListNull(head)) return 0;
        int length = 0;
        //定义辅助变量，没有计算头节点
        Node cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;//遍历
        }
        return length;
    }

    //获取头节点
    public Node getHead() {
        return head;
    }

    /**
     * 查找单链表中的倒数第 k个结点
     * @param head 链表头节点
     * @param index 倒数第k个结点的序号
     * @return 倒数第k个结点
     */
    public Node findIndexNode(Node head, int index){
        //如果链表为空，返回null
        if (isLinkListNull(head)) return null;
        Node cur = head.next;//定义辅助遍历
        int length = getLength(head);//获得链表长度
        //index校验
        if(index <= 0 || index > length) return null;
        for (int i = 0; i < length - index; i++) {//得到长度后，我们从链表的第一个开始遍历 (length-index)个
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 将单链表反转
     * @param head 传入头节点
     */
    public void reverseList(Node head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (isLinkListNull(head) || head.next.next == null) return;
        Node cur = head.next;//定义辅助变量
        Node next = null;//指向当前节点[cur]的下一个节点
        Node newHead = new Node(0,"","");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表newHead的最前端
        while (cur != null){
            next = cur.next;////暂时保存当前节点的下一个节点
            cur.next = newHead.next;//新链表的第一个结点连到当前结点
            newHead.next = cur;//将当前结点连接到新链表的头节点
            cur = next;//当前结点在原链表的位置后移
        }
        //将head.next指向newHead.next,实现单链表的反转
        head.next = newHead.next;
    }

    /**
     * 通过栈实现逆序输出
     * @param head 创建头节点
     */
    public void reversePrint(Node head){
        if (isLinkListNull(head)) return;//空链表
        Stack<Node> stack = new Stack<>();//创建一个栈
        Node cur = head.next;//定义辅助结点
        while (cur != null){
            stack.push(cur);//入栈
            cur = cur.next;//后移
        }
        while (stack.size()>0){
            System.out.println(stack.pop());//出栈
        }
    }
}

public class SingleLinkListDemo {

    public static void main(String[] args) {
        //创建结点
        Node node1 = new Node(1,"1.1","1.1");
        Node node2 = new Node(2,"2.1","2.1");
        Node node3 = new Node(3,"3.1","3.1");
        Node node4 = new Node(4,"4.1","4.1");
        //创建链表
        SingleLinkList singleLinkList = new SingleLinkList();
        //添加结点
        singleLinkList.addByOrder(node1);
        singleLinkList.addByOrder(node4);
        singleLinkList.addByOrder(node2);
        singleLinkList.addByOrder(node3);

        singleLinkList.reversePrint(singleLinkList.getHead());

        /*singleLinkList.reverseList(singleLinkList.getHead());
        singleLinkList.showList();

        System.out.println(singleLinkList.findIndexNode(singleLinkList.getHead(),3));

        System.out.println("链表结点为" + singleLinkList.getLength(singleLinkList.getHead()));
        //显示链表
        singleLinkList.showList();
        Node node5 = new Node(4,"4.2","4.2");
        //修改结点信息
        singleLinkList.update(node5);
        singleLinkList.showList();
        //删除
        singleLinkList.delete(4);
        System.out.println("链表结点为" + singleLinkList.getLength(singleLinkList.getHead()));
        singleLinkList.showList();*/
    }
}
