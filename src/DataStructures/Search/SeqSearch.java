package DataStructures.Search;

/**
 * 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        int index = seqSearch(arr,10);
        if (index == -1){
            System.out.println("没有找到");
        }else {
            System.out.println(index);
        }
    }

    /**
     * 这里解决了数组中没有元素值相同的情况
     * @param arr 目标数组
     * @param target 查找的值
     * @return
     */
    private static int seqSearch(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target){
                return i;
            }
        }
        return -1;
    }
}
