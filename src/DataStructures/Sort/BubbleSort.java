package DataStructures.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int arr[] = {3,9,-1,10,20}
        int arrSize = 80000;//定义数组大小
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
        int[] arr = createNumbers(arrSize);

        Date start = new Date();//开始时间
        System.out.println("开始: " + simpleDateFormat.format(start));

        bubbleSort(arr);

        Date end = new Date();//结束时间
        System.out.println("结束: " + simpleDateFormat.format(end));
    }

    //生成随机数数组
    public static int[] createNumbers(int arrSize) {
        int arr[] = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = (int) (Math.random()*8000000);//生成一个0到8000000的数
        }
        return arr;
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;//临时变量
        boolean flag = false;//是否交换的标志位
        for (int i = 0; i < arr.length - 1; i++) {//大的循环次数
            for (int j = 0; j < arr.length - 1 - i; j++) {//扫描数组
                //前面的数比后面的数大，就交换
                if (arr[j] > arr[j+1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            //System.out.println("第" + (i+1) + "次排序后的数组");
            //System.out.println(Arrays.toString(arr));

            if (!flag){//未发生交换，退出循环
                break;
            }else {
                flag = false;//发生交换
            }
        }
    }
}
