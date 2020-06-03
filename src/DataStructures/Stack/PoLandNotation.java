package DataStructures.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 列表、栈实现逆波兰计算器
 * 中缀表达式转后缀表达式
 */
public class PoLandNotation {
    //定义优先级
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static void main(String[] args) {
        /**
         * 实现中缀表达式转后缀表达式
         * 先将"1+((2+3)×4)-5"转换成List
         * 再将中缀List转换成后缀List
         */
        String expression = "1+((2+3)×4)-5";
        System.out.println(parseSuffixExpression(toInfixExpressionList(expression)));


        /**
         * 实现逆波兰计算器
         * 先将"3 4 + 5 x 6 -"放到ArrayList中
         * 将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
         */
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        /*String suffixExpression = "30 40 + 50 x 60 -";
        System.out.println(suffixExpression + " = " + calculate(getListString(suffixExpression)));*/
    }

    /**
     * 中缀表达式转成对应的 List
     * @param str 中缀表达式
     * @return
     */
    public static List<String> toInfixExpressionList(String str){
        ArrayList<String> list = new ArrayList<>();
        int i = 0;//定义索引
        String s;//对多位数进行拼接
        char ch;//遍历每个字符
        do {
            //如果ch是一个字符
            if ((ch=str.charAt(i)) < 48 || (ch=str.charAt(i))>57){
                list.add(String.valueOf(ch));//加入List
                i++;
            }else {//如果是数字，考虑多位数
                s = "";//每次使用前置空
                while (i<str.length() && (ch=str.charAt(i))>=48 && (ch=str.charAt(i)) <=57){
                    s += ch;
                    i++;
                }
                list.add(s);
            }
        }while (i<str.length());
        return list;
    }

    public static List<String> parseSuffixExpression(List<String> list){
        //因为s2这个栈在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出，直接使用 List<String>
        Stack<String> s1 = new Stack<>();
        ArrayList<String> s2 = new ArrayList<>();

        //遍历
        for (String item: list){
            //如果是一个数，加入s2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将这一对括号丢弃
            }else {//遇到运算符
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中再次与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && getValue(s1.peek()) >= getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);//需要将item压入栈
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() !=0 ){
            s2.add(s1.pop());
        }

        return s2;
    }

    /**
     * 返回符号优先级
     * @param operation 传入符号
     * @return 返回优先级
     */
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "×":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("运算符出错");
                break;
        }
        return result;
    }

    /**
     * 将逆波兰表达式分割放入到ArrayList中
     * @param suffixExpression 逆波兰表达式
     * @return 数组
     */
    /*public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");//以空格作为分割界限
        ArrayList<String> list = new ArrayList<>();
        for (String str: split) {
            list.add(str);
        }
        return list;
    }

    /**
     * 完成对逆波兰表达式的运算
     * 从左至右扫描，将30和40压入堆栈
     * 遇到+运算符，因此弹出40和30（40为栈顶元素，30为次顶元素），计算出30+40的值，得70，再将70入栈
     * 将50入栈
     * 接下来是×运算符，因此弹出50和70，计算出70×50=3500，将3500入栈
     * 将60入栈
     * 最后是-运算符，计算出3500-60的值，即3440，由此得出最终结果
     * @param list 传入逆波兰表达式的数组
     * @return 结果
     */
    /*public static int calculate(List<String> list){
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : list){
            //使用正则表达式来取出数
            if (item.matches("\\d+")){//匹配多位数
                //入栈
                stack.push(item);
            }else {
                //取出两个数，并运算，再入栈
                int number2 = Integer.parseInt(stack.pop());
                int number1 = Integer.parseInt(stack.pop());
                if (item.equals("+")){
                    stack.push(String.valueOf(number1 + number2));
                }else if (item.equals("-")){
                    stack.push(String.valueOf(number1 - number2));
                }else if (item.equals("x")){
                    stack.push(String.valueOf(number1 * number2));
                }else if(item.equals("/")){
                    stack.push(String.valueOf(number1 / number2));
                }else {
                    throw new RuntimeException("运算符错误");
                }
            }
        }
        //返回栈顶
        return Integer.parseInt(stack.pop());
    }*/
}
