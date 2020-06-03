package DataStructures.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = {119,34,101,1};
        int arrSize = 80000;//定义数组大小
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
        int[] arr = createNumbers(arrSize);

        Date start = new Date();//开始时间
        System.out.println("开始: " + simpleDateFormat.format(start));

        insertSort(arr);

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

    private static void insertSort(int[] arr) {
        int insertValue = 0;
        int insertIndex = 0;

        for (int i = 1; i <arr.length; i++) {//一共进行arr.length次插入

            //定义插入的数
            insertValue = arr[i];
            insertIndex = i-1;//被选中的插入数据前面的那个数的下标

            //找到插入的位置
            // 说明
            // 1. insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
            // 2. insertValue < arr[insertIndex] 待插入的数，还没有找到插入位置
            while (insertIndex >= 0 && insertValue < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];//需要将 arr[insertIndex] 后移
                insertIndex--;//下标往前走
            }

            //退出循环，找到插入位置insertIndex+1
            //如果插入的下标不等于当前的下标则插入
            if (insertIndex + 1 != i) {
                arr[insertIndex+1] = insertValue;
            }


            //System.out.println("第" +i+"轮排序之后");
            //System.out.println(Arrays.toString(arr));
        }
    }
}
