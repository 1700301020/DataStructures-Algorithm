package DataStructures.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {-9,78,0,23,-567,70};
        int arrSize = 800000;//定义数组大小
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
        int[] arr = createNumbers(arrSize);

        Date start = new Date();//开始时间
        System.out.println("开始: " + simpleDateFormat.format(start));

        quickSort(arr,0,arr.length-1);

        //System.out.println(Arrays.toString(arr));
        Date end = new Date();//结束时间
        System.out.println("结束: " + simpleDateFormat.format(end));
    }

    private static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标
        int r = right;//右下标
        int midValue = arr[(left+right)/2];//中间值
        int temp = 0;

        //比中间值大的放右边，小的放左边
        while (l < r){
            while (arr[l] < midValue) l += 1;//在左边找，直到找到大于中间值的数
            while (arr[r] > midValue) r -= 1;//在右边找，直到找到小于中间值的数

            //如果 l>=r 说明左边的数都小于中间值，右边的数都大于中间值
            if (l >= r) break;

            //否则就交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //交换完成发现arr[l] == midValue ,则r--,往数组前移;
            if (arr[l] == midValue) {
                r -= 1;
            }
            //交换完成发现arr[r] == midValue ,则l++，往数组后移;
            if (arr[r] == midValue) {
                l += 1;
            }
        }

        //如果l==r,必须l++,r--,否则会出现栈溢出
        if (l==r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if (r > left) quickSort(arr,left,r);
        //向右递归
        if (l < right) quickSort(arr,l,right);
    }

    //生成随机数数组
    public static int[] createNumbers(int arrSize) {
        int arr[] = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = (int) (Math.random()*8000000);//生成一个0到8000000的数
        }
        return arr;
    }
}
