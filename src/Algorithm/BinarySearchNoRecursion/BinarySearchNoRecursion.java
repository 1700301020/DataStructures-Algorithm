package Algorithm.BinarySearchNoRecursion;

/**
 * 二分查找的非递归实现
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearchNoRecursion(arr, 100);
        System.out.println(index);
    }

    /**
     * 二分查找的非递归实现
     * @param arr 目标数组，有序
     * @param target 查找的值
     * @return 找到返回下标，反之返回-1
     */
    public static int binarySearchNoRecursion(int arr[], int target){
        int left = 0;
        int right = arr.length-1;
        while (left <= right){
            int mid = (left + right) /2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] > target){
                right = mid -1;
            }else {
                left = mid +1;
            }
        }
        return -1;
    }
}
