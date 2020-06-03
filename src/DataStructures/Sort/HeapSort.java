package DataStructures.Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 堆排序
 */
public class HeapSort {

    /**
     * 堆排序
     * @param arr 目标数组
     */
    public static void heapSort(int[] arr){
        int temp = 0;
        //将无序序列构件成一个堆
        for (int i = arr.length / 2 -1; i >= 0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }
        //将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        for (int j = arr.length-1; j >0 ; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
            adjustHeap(arr,0,j);
        }
        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 将一个数组调整为大根堆，完成将以i对应的非叶子结点的树调整成大根堆
     * @param arr 待调整的数组
     * @param i 非叶子结点的在数组中的索引
     * @param length 表示对多少个元素进行调整，length逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];//取出当前元素的值
        //k = i*2+1 k是i结点的左子节点
        //k = k*2+1 表示继续往左子树走
        for (int k = i*2+1 ; k < length; k = k*2+1) {
            if (k+1 < length && arr[k] < arr[k+1]) {//左子节点的值小于右子节点
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {//子节点大于父节点
                arr[i] = arr[k];//把大的值赋给当前结点
                i=k;//i指向k，继续循环比较
            }else {
                break;
            }
        }

        //for循环结束后，我们已经将以i为父节点的树的最大值放在了最顶(局部)
        arr[i] = temp;//将temp的值放到调整后的位置
    }

    //生成随机数数组
    public static int[] createNumbers(int arrSize) {
        int arr[] = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = (int) (Math.random()*8000000);//生成一个0到8000000的数
        }
        return arr;
    }

    public static void main(String[] args) {
        //int[] arr = {4,6,8,5,9};
        int arrSize = 8000000;//定义数组大小
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式化
        int[] arr = createNumbers(arrSize);

        Date start = new Date();//开始时间
        System.out.println("开始: " + simpleDateFormat.format(start));

        heapSort(arr);

        Date end = new Date();//结束时间
        System.out.println("结束: " + simpleDateFormat.format(end));
    }
}
