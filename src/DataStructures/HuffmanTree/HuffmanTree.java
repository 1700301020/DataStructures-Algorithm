package DataStructures.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 哈夫曼树
 */
//结点类
//为了让 Node 对象支持排序
// Collections 集合排序
//让 Node 实现 Comparable 接口
class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//从小到大
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this.value);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}


public class HuffmanTree {

    /**
     * 创建Huffman树
     * @param arr 目标数组
     * @return Huffman树根节点
     */
    public static Node createHuffmanTree(int[] arr){
        //遍历 arr 数组，将 arr 的每个元素构成成一个 Node放入到 ArrayList 中
        ArrayList<Node> nodes = new ArrayList<>();
        for(int value: arr){
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1){
            //排序从小到大
            Collections.sort(nodes);
            //取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArrayList删除已使用的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //把新生成的结点加入
            nodes.add(parent);
        }

        //返回Huffman树的根节点
        return nodes.get(0);
    }

    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("空树");
        }
    }


    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1 };
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }
}
