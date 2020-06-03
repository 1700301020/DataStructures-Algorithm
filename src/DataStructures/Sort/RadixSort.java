package DataStructures.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        //int[] arr = {53, 3, 542, 748, 14, 214};
        int arrSize = 80000;//定义数组大小
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
        int[] arr = createNumbers(arrSize);

        Date start = new Date();//开始时间
        System.out.println("开始: " + simpleDateFormat.format(start));

        radixSort(arr);

        Date end = new Date();//结束时间
        System.out.println("结束: " + simpleDateFormat.format(end));
    }

    private static void radixSort(int[] arr) {
        //定义一个二维数组表示10个桶
        int[][] bucket = new int[10][arr.length];
        //定义一个统计每个桶每次放入多少个数据的数组
        int[] bucketElementCounts = new int[10];

        //得到数组中最大的数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }

        //得到最大的数的位数
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //对每位数进行排序
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位的值
                int digitOfElement = (arr[j] /n) % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                //对应的计数数组值增加
                bucketElementCounts[digitOfElement]++;
            }

            //按照桶的顺序放回原来的数组
            int index = 0;
            //遍历每个桶，并将每个桶的数据放回到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，则将数据放回
                if (bucketElementCounts[k] != 0){
                    //循环取出数据放回
                    for (int j = 0; j < bucketElementCounts[k]; j++) {
                        arr[index++] = bucket[k][j];
                    }
                }
                //一轮过后需要将统计数组置0
                bucketElementCounts[k] = 0;
            }
            //System.out.println(Arrays.toString(arr));
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
