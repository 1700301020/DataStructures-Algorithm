package DataStructures.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 选择排序
 */
public class SelectedSort {
    public static void main(String[] args) {
        //int[] arr = {101,34,119,1};
        int arrSize = 80000;//定义数组大小
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
        int[] arr = createNumbers(arrSize);

        Date start = new Date();//开始时间
        System.out.println("开始: " + simpleDateFormat.format(start));

        selectSort(arr);

        Date end = new Date();//结束时间
        System.out.println("结束: " + simpleDateFormat.format(end));
    }

    private static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {//进行arr.length - 1次选择
            int minIndex = i;//暂定首个最小
            int min = arr[minIndex];

            for (int j = i+1; j < arr.length; j++) {//循环找出最小数
                if (arr[minIndex] > arr[j]){//假定的最小值不是最小
                    min = arr[j];//更新最小值
                    minIndex = j;//记录最小值下标
                }
            }

            //交换
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
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
