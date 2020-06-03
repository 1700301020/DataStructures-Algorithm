package DataStructures.Recursion;

/**
 * 递归实现八皇后问题
 */
public class Queue8 {

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array, 保存皇后放置位置的结果, 比如arr = {0, 4, 7, 5, 2, 6, 1, 3}
    //对应array下标表示第几行，即第几个皇后，array[i]=value , value表示第i+1个皇后，放在第i+1行的第value+1列
    int[] array = new int[max];
    private static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(count);
    }

    /**
     * 放置第n个皇后
     * check是每一次递归时，进入到check中都有for(int i = 0; i < max; i++)，因此会有回溯
     * @param n
     */
    private void check(int n){
        if (n == max){//当n=8, 其实8个皇后就已经放好了
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前皇后放到当前行的第i列
            array[n] = i;
            //判断当前皇后放到第i列时是否冲突
            if (judge(n)){
                //接着放下一个皇后
                check(n+1);
            }
            //如果冲突，就继续执行array[n] = i，即将当前皇后，在本行后移一个位置
        }
    }

    /**
     * 判断当前皇后是否和之前的n-1个皇后冲突
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //Math.abs(n-i) == Math.abs(array[n] - array[i] 表示判断第n个皇后是否和第i皇后是否在同一斜线
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 输出皇后的摆放位置
     */
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
