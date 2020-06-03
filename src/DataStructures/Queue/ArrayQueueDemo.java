package DataStructures.Queue;

import java.util.Scanner;

/**
 * 使用数组模拟队列操作
 */
class ArrayQueue{
    private int maxSize;//最大容量
    private int front;//头指针
    private int rear;//尾指针
    private int[] array;//存放数据，模拟队列

    //构造函数
    public ArrayQueue(int arrmaxSize) {
        this.maxSize = arrmaxSize;
        this.array = new int[maxSize];
        this.front = -1;//指向队列头的前一个位置
        this.rear = -1;//指向队列最后一个数据
    }

    //判断是否满
    public boolean isFull(){
        return rear == maxSize-1;
    }

    //判断是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据
    public void addQueue(int n){
        //判断是否满
        if (isFull()) {
            System.out.println("队列满了");
            return;
        }
        array[++rear] = n;//尾指针后移
    }

    //获取数据
    public int getQueue(){
        //判断队列是否为空
        isEmptyError();
        return array[++front];//头指针后移
    }

    /**
     * 判断队列是否为空
     */
    public void isEmptyError() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空");
        }
    }

    //显示队列数据
    public void showQueue(){
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        //遍历
        for (int i = 0; i < array.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,array[i]);
        }
    }

    //获取头指针的值
    public int getHeader(){
        //判断队列是否为空
        isEmptyError();
        return array[front+1];
    }
}

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建对象
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):取出数据");
            System.out.println("h(head):查看头指针");

            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输一个数：");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try{
                        System.out.println("取出的数据是:" + arrayQueue.getQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        System.out.println("头指针的数据是:" + arrayQueue.getHeader());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("退出成功");
    }
}
