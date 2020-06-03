package DataStructures.Queue;

import java.util.Scanner;

/**
 * 使用数组模拟循环队列
 */
class CircleArray{
    private int maxSize;//最大容量
    private int front;//front 就指向队列的第一个元素,  也就是说 arr[front] 就是队列的第一个元素
    private int rear;//rear 指向队列的最后一个元素的后一个位置
    private int[] array;//存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        array = new int[arrMaxSize];
    }

    //判断是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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
        //直接将数据加入
        array[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取数据
    public int getQueue(){
        //判断队列是否为空
        isEmptyError();
        //这里需要分析出 front 是指向队列的第一个元素
        int value = array[front];//先把 front对应的值保留到一个临时变量
        front = (front + 1) % maxSize;//将 front后移,考虑取模
        return value;//将临时保存的变量返回
    }

    /**
     * 判断队列是否为空，为空则返回异常
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
        //从 front 开始遍历
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,array[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    private int size() {
        return (rear + maxSize -front) % maxSize;
    }

    //获取头指针的值
    public int getHeader(){
        //判断队列是否为空
        isEmptyError();
        return array[front];
    }

}

public class CircleArrayQueue {
    public static void main(String[] args) {
        //创建环形队列
        CircleArray arrayQueue = new CircleArray(5);
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
