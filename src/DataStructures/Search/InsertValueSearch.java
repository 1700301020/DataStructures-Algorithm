package DataStructures.Search;

/**
 * 插值查找
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }

        System.out.println(insertValueSearch(arr,0,arr.length-1,10));
    }

    /**
     * 插值查找，默认没有相同值
     * @param arr 目标数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findValue 目标值
     */
    private static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        //退出条件
        if (left > right || findValue <arr[0] || findValue > arr[arr.length-1]) {//防止mid越界
            return -1;
        }

        //求出mid
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if (findValue > midValue){//向右递归
            return insertValueSearch(arr,mid+1,right,findValue);
        }else if (findValue < midValue){
            return insertValueSearch(arr,left,mid-1,findValue);
        }else {
            return mid;
        }
    }


}
