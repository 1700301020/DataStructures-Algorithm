package DataStructures.Search;

import java.util.Arrays;

/**
 * 斐波那契查找
 */
public class FibonacciSearch {

    private static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8, 10, 89, 1000, 1234};
        System.out.println(fibonacciSearch(arr, 89));
    }

    /**
     * 非递归实现
     * @param arr 目标数组
     * @param key 关键字
     * @return
     */
    private static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length-1;
        int k = 0;//斐波那契分割数值的下标
        int mid = 0;
        int[] f = fib();

        //获取到斐波那契分割值的下标
        while (high > f[k] -1){
            k++;
        }
        //因为f[k]的值可能大于arr的长度，因此需要构建一个新的数组，并指向temp
        int[] temp = Arrays.copyOf(arr, f[k]);
        //使用原始数组的最后一个填充temp
        for (int i = high+1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //找到key
        while (low <= high){
            mid = low + f[k-1]-1;
            if (key < temp[mid]){//往前找
                high = mid -1;
                //全部元素 = 前面的元素 + 后边元素,f[k] = f[k-1] + f[k-2]
                //因为前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1] 的前面继续查找 k--,即下次循环 mid = f[k-1-1]-1
                k--;
            }else if (key > temp[mid]){//往后找
                low = mid +1;
                //全部元素 = 前面的元素 + 后边元素,f[k] = f[k-1] + f[k-2]
                //因为后面有 f[k-2]个元素,所以可以继续拆分 f[k-2] = f[k-3] + f[k-4]
                //即在f[k-2] 的后面面继续查找 k-= 2,即下次循环 mid = f[k-1-2]-1
                k -=2;
            }else{//找到，返回小的那个下标
                if (mid <= high) {
                    return mid;
                }
                else {
                    return high;
                }
            }
        }
        return -1;
    }


    //创建一个斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }
}
