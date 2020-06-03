package DataStructures.Search;

import java.util.ArrayList;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,1000,1000,1000,1000,1234};
        //int index = binarySearch1(arr,0,arr.length-1,88);
        //System.out.println(index);

        ArrayList arrayList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(arrayList);
    }

    /**
     * 二分查找，数组中没有相同值
     * @param arr 目标数组
     * @param left 左索引
     * @param right 右索引
     * @param value 查找元素
      */
    private static int binarySearch1(int[] arr, int left, int right, int value) {
        int mid = (left + right) /2;
        int midValue = arr[mid];

        if (left > right) return -1;//当left>right 找不到，退出

        if (value > midValue){//向右递归
            return binarySearch1(arr,mid+1,right,value);
        }else if (value < midValue){//向左递归
            return binarySearch1(arr,left,mid-1,value);
        }else {
            return mid;
        }
    }

    /**
     * 二分查找，数组中有相同值
     *@param arr 目标数组
     * @param left 左索引
     * @param right 右索引
     * @param value 查找元素
     */
    private static ArrayList binarySearch2(int[] arr, int left, int right, int value) {
        int mid = (left + right) /2;
        int midValue = arr[mid];

        if (left > right) return null;//当left>right 找不到，退出

        if (value > midValue){//向右递归
            return binarySearch2(arr,mid+1,right,value);
        }else if (value < midValue){//向左递归
            return binarySearch2(arr,left,mid-1,value);
        }else {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(mid);//再把中间的加入到数组
            //向midValue左边找
            for (int i = mid-1; i >=0; i--) {
                if (arr[i] == midValue)
                    result.add(i);
            }
            //向midValue右边找
            for (int i = mid+1; i <=arr.length-1; i++) {
                if (arr[i] == midValue)
                    result.add(i);
            }
            return result;
        }
    }
}
