package DataStructures.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        //int[] arr ={8, 4, 5, 7, 1, 3, 6, 2};

        int arrSize = 80000;//定义数组大小
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
        int[] arr = createNumbers(arrSize);
        int[] temp = new int[arr.length];

        Date start = new Date();//开始时间
        System.out.println("开始: " + simpleDateFormat.format(start));

        mergeSort(arr,0,arr.length-1,temp);

        Date end = new Date();//结束时间
        System.out.println("结束: " + simpleDateFormat.format(end));

        //System.out.println("归并排序：" + Arrays.toString(arr));
    }

    /**
     * 分+合
     * @param arr 原始数组
     * @param left 左边索引
     * @param right 右边索引
     * @param temp 中间数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int mid = (left + right) /2 ;//中间索引
            mergeSort(arr, left, mid, temp);//向左递归
            mergeSort(arr, mid+1, right, temp);//向右递归
            merge(arr,left,mid,right,temp);//合并
        }
    }

    /**
     * 合并的方法
     * @param arr 原始数组
     * @param left 左边索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 中间数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid +1;
        int t = 0;//中间数组的索引

        //第一步：把左右两边的数据按照规则填充到中间数组，直到有一边处理完成
        while (i <= mid && j <= right){
            //左边的数据小于右边的数据
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                i++;
            }else {//左边的数据大于右边的数据
                temp[t] = arr[j];
                j++;
            }
            t++;
        }

        //第二步：把有剩余的数据填充到中间数组
        while (i <= mid){//左边有剩余
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right){//右边有剩余
            temp[t] = arr[j];
            t++;
            j++;
        }

        //第三步：将temp数组的元素拷贝到原始数组，注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
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
