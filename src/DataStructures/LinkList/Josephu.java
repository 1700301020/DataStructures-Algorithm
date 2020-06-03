package DataStructures.LinkList;

/**
 * 使用循环单链表解决约瑟夫环问题
 */
//定义小孩子类
class Boy{
    private int number;//编号
    private Boy next;//指向下一个小孩

    public Boy(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

//定义一个环形链表类
class CircleSingleLinkedList{
    //创建一个first节点,当前没有编号
    private Boy first = null;

    /**
     * 添加小孩节点，构建成一个环形的链表
     * @param nums 输入小孩数量
     */
    public void addBoy(int nums){
        //数据校验
        if (nums < 1){
            System.out.println("输入的值不正确");
            return;
        }
        Boy curBoy = null;//定义一个辅助变量
        for (int i = 1; i <= nums ; i++) {
            Boy boy = new Boy(i);//根据编号创建小孩结点
            //如果是第一个小孩
            if (i == 1){
                first = boy;
                first.setNext(first);//构成环形
                curBoy = first;//让curBoy指向第一个小孩
            }else {
                curBoy.setNext(boy);//连接新的小孩
                boy.setNext(first);//末尾的小孩连接到第一个小孩
                curBoy = boy;//当前小孩后移一位
            }
        }
    }

    /**
     * 遍历当前的环形链表
     */
    public void showBoy(){
        //判断链表是否为空
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号是%d\n",curBoy.getNumber());
            if (curBoy.getNext() == first){
                break;//说明遍历完毕
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startBoy 表示从第几个小孩开始数数
     * @param countNum 表示每个小孩数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startBoy, int countNum, int nums){
        //数据校验
        if (first == null || startBoy<1 ||startBoy>nums) {
            System.out.println("参数输入有误");
            return;
        }
        Boy helper = first;//创建辅助指针
        //helper指向环形链表的最后节点
        while (true){
            if (helper.getNext() == first) break;//移动到了最后
            helper = helper.getNext();//helper后移
        }
        //小孩报数前，先让first和helper移动到开始报数的小孩位置
        for (int i = 1; i <= startBoy-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //这里是一个循环操作，直到圈中只有一个小孩
        while (true){
            if (helper == first) break;//只有一个小孩
            //当小孩报数时，让first和helper指针同时移动countNum-1次,也就是每次有多少个小孩要报数
            for (int i = 1; i <=countNum-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的小孩，就是要出圈的小孩
            System.out.printf("第%d个小孩出圈\n",first.getNumber());
            //出圈操作
            first = first.getNext();//当前小孩后移
            helper.setNext(first);//出圈前在first之后的小孩连到当前小孩，完成出圈
        }
        System.out.printf("最后留下来的小孩子编号为:%d", first.getNumber());
    }
}

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1,2,5);
    }
}
