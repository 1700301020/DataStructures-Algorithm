package DataStructures.Tree;

/**
 * 顺序存储二叉树
 */

//顺序存储二叉树类
class ArrayBinaryTree{
    private int[] arr;//存储数据

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void preOrder(){
        this.preOrder(0);
    }

    public void infixOrder(){
        this.infixOrder(0);
    }

    public void postOrder(){
        this.postOrder(0);
    }

    /**
     * 数组->二叉树前序遍历
     * @param index 索引
     */
    public void preOrder(int index){
        //数组为空或者数组长度为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左遍历
        if ((index *2 + 1) < arr.length){
            preOrder(index *2 + 1);
        }
        //向右遍历
        if ((index *2 +2) < arr.length){
            preOrder(index *2 +2);
        }
    }

    /**
     * 数组->二叉树中序遍历
     * @param index 索引
     */
    public void infixOrder(int index){
        //数组为空或者数组长度为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        //向左遍历
        if ((index *2 + 1) < arr.length ){
            infixOrder(index *2 + 1);
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向右遍历
        if ((index *2 +2) < arr.length){
            infixOrder(index *2 +2);
        }
    }

    /**
     * 数组->二叉树后序遍历
     * @param index 索引
     */
    public void postOrder(int index){
        //数组为空或者数组长度为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
        }
        //向左遍历
        if ((index *2 + 1) < arr.length ){
            postOrder(index *2 + 1);
        }
        //向右遍历
        if ((index *2 +2) < arr.length){
            postOrder(index *2 +2);
        }
        //输出当前元素
        System.out.println(arr[index]);
    }
}
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        //arrayBinaryTree.preOrder();
        //arrayBinaryTree.infixOrder();
        arrayBinaryTree.postOrder();
    }
}
