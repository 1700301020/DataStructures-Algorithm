package DataStructures.Stack;

/**
 * 使用栈实现计算器
 */
//定义一个ArrayStack类表示栈
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//用数组模拟栈
    private int top = -1;//表示栈顶

    public ArrayStack2(int maxSize) {
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

    //返回栈顶的值
    public int peek(){
        return stack[top];
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

    /**
     * 返回运算符的优先级，用数字表示，数字越大优先级越高
     * @param oper 符号
     * @return 优先级
     */
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;//目前只假定有加减乘除
        }
    }

    /**
     * 判断是不是一个运算符
     * @param val 输入一个字符
     * @return
     */
    public boolean isOper(char val){
        return val == '+' || val == '-' || val =='*' || val == '/';
    }

    public int cal(int num1, int num2, int oper){
        int res = 0;//用于存放结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;//注意顺序
                break;
            default:
                break;
        }
        return res;
    }
}

public class Calculator {
    public static void main(String[] args) {
        String expression = "700*2*2-5+1-5+3-4";
        //创建数栈和符号栈
        ArrayStack2 numberStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        char ch = ' ';//将每次扫描得到 char 保存到 ch
        String keyNum = "";//处理多位数字

        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)){
                //如果是运算符,判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    //符号栈不为空
                    //如果当前运算符的优先级小于或者等于符号栈栈顶的操作符
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        //在从符号栈中pop出一个符号，从数栈中pop出两个数进行运算，将得到结果入数栈
                        numberStack.push(numberStack.cal(numberStack.pop(),numberStack.pop(),operStack.pop()));
                        //将当前的操作符入符号栈
                        operStack.push(ch);
                    }else {
                        //如果当前运算符的优先级大于符号栈栈顶的操作符,直接入栈
                        operStack.push(ch);
                    }
                }else {
                    //符号栈为空，直接入栈
                    operStack.push(ch);
                }
            }else {
                //如果ch是数字
                /**
                 * 当处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数
                 * 在处理数，需要向 expression的表达式的index后再看一位,如果是数就进行扫描，如果是符号才入栈
                 * 我们需要定义一个变量字符串，用于拼接
                 */

                keyNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length()-1){
                    numberStack.push(Integer.parseInt(keyNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){//index的下一位是index+1,不必index++
                        numberStack.push(Integer.parseInt(keyNum));
                        keyNum="";//清空，防止迭代
                    }
                }
            }
            //index后移，判断是否到末尾
            index++;
            if (index >= expression.length()){
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            numberStack.push(numberStack.cal(numberStack.pop(),numberStack.pop(),operStack.pop()));
        }

        //将数栈的最后数pop出，就是结果
        System.out.printf("%s的最后结果为:%d",expression,numberStack.pop());
    }
}
