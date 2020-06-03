package DataStructures.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        //int [] arr = {8,9,1,7,2,3,5,4,6,0};
        int arrSize = 80000;//定义数组大小
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
        int[] arr = createNumbers(arrSize);

        Date start = new Date();//开始时间
        System.out.println("开始: " + simpleDateFormat.format(start));

        //shellSort1(arr);
        shellSort2(arr);

        Date end = new Date();//结束时间
        System.out.println("结束: " + simpleDateFormat.format(end));
    }

    /**
     * 交换式的希尔排序，从前往后
     * @param arr
     */
    private static void shellSort1(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length/2; gap>0; gap /= 2){// /2来减小增量

            for (int i = gap; i < arr.length; i++) {//把数据分成了gap组

                for (int j = i-gap; j >=0 ; j -= gap) {//遍历数组中的元素
                    if (arr[j] > arr[j+gap]){//如果当前元素大于加上gap步长后的那个元素，交换
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }

            //System.out.println("第" + (++count) + "次希尔排序之后:" + Arrays.toString(arr));
        }
    }

    /**
     * 移位式希尔排序，从后往前
     * @param arr
     */
    private static void shellSort2(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length/2; gap>0; gap /= 2){// /2来减小增量

            for (int i = gap; i < arr.length; i++) {//从第gap个元素，逐个对其所在的组进行直接插入排序
                int j = i;//待插入位置记录下标
                temp = arr[j];//记录要插入的数

                if (arr[j] < arr[j-gap]) {//如果当前元素小于减gap步长后的那个元素，直接插入
                    while (j-gap >=0 && temp < arr[j-gap]){
                        arr[j] = arr[j-gap];//把大的数移动到后面
                        j -= gap;//下标往前移动
                    }

                    //退出循环后就能找到插入位置
                    arr[j] = temp;
                }
            }

            //System.out.println("第" + (++count) + "次希尔排序之后:" + Arrays.toString(arr));
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
