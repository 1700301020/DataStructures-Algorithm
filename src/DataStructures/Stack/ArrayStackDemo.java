package DataStructures.Stack;

import java.util.Scanner;

/**
 * 使用数组模拟栈操作
 */
//定义一个ArrayStack类表示栈
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//用数组模拟栈
    private int top = -1;//表示栈顶

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int number){
        //判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = number;
    }

    //出栈
    public int pop(){
        //判断栈是否空
        isStackNull();
        int value = stack[top];
        top--;
        return value;
    }

    //显示数据
    public void show(){
        //判断是否为空
        isStackNull();
        //从栈顶开始
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }

    /**
     * 判断栈是否为空
     */
    public void isStackNull() {
        if (isEmpty()) {
            System.out.println("栈为空");
        }
    }
}

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop){
            System.out.println("show:显示栈");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("exit:退出");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入元素:");
                    stack.push(scanner.nextInt());
                    break;
                case "pop":
                    try {
                        System.out.println("出栈元素为:" + stack.pop());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("已退出");
    }
}
