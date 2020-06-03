package DataStructures.HuffmanCode;

import java.io.*;
import java.util.*;

/**
 * Huffman编码以及文件解压缩
 */
//定义结点
class Node implements Comparable<Node>{
    Byte data;//数据
    int weight;//权值
    Node leftNode;
    Node rightNode;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //从小到大排序
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.leftNode != null){
            this.leftNode.preOrder();
        }
        if (this.rightNode != null){
            this.rightNode.preOrder();
        }
    }
}

public class HuffmanCode {

    /**
     * 统计字符个数并存入到nodes列表
     * @param bytes 字节接收数组
     * @return 结点的List形式
     */
    public static List<Node> getNodes(byte[] bytes){
        //创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        //遍历bytes，统计每一个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b: bytes){
            Integer count = counts.get(b);
            if (count == null){//Map中没有过出现当前字符
                counts.put(b,1);
            }else {
                counts.put(b,count+1);
            }
        }
        //遍历Map，把每一个键值对转换成一个Node并加入到nodes集合
        for (Map.Entry<Byte,Integer> entry: counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }

        return nodes;
    }

    /**
     * 生成Huffman树
     * @param nodes 结点的List形式
     * @return Huffman树
     */
    public static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            //排序
            Collections.sort(nodes);
            //取出前两个数
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            //构建新的二叉树,没有数据，只有权值
            Node parent = new Node(null,left.weight + right.weight);
            parent.leftNode = left;
            parent.rightNode = right;
            //从列表删除已使用的结点
            nodes.remove(left);
            nodes.remove(right);
            //将新节点加入列表
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 前序遍历
     * @param root Huffman树根节点
     */
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("空树");
        }
    }

    //将Huffman编码存放在Map<Byte,String>形式
    static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
    //定义一个StringBuilder存储某个叶子结点
    static StringBuilder stringBuilder = new StringBuilder();

    //重载getCodes
    public static Map<Byte,String> getCodes(Node root){
        if (root == null){
            return null;
        }
        //处理root的左子树
        getCodes(root.leftNode,"0",stringBuilder);
        //处理root的右子树
        getCodes(root.rightNode,"1",stringBuilder);
        return huffmanCodes;
    }

    /**
     *  计算node的所有叶子结点的Huffman编码，并放入到huffmanCodes
     * @param node 传入的结点
     * @param code 左边为0，右边为1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder inStringBuilder = new StringBuilder(stringBuilder);
        //将code加入到inStringBuilder
        inStringBuilder.append(code);
        if (node != null) {//如果当前结点为空不处理
            if (node.data == null){//当前结点为非叶子结点
                //向左递归
                getCodes(node.leftNode,"0",inStringBuilder);
                //向右递归
                getCodes(node.rightNode,"1",inStringBuilder);
            }else {//当前结点为叶子结点
                huffmanCodes.put(node.data,inStringBuilder.toString());
            }
        }
    }

    /**
     * 将字符串对应的byte[]传入，返回一个经过生成的Huffman编码压缩后的byte[]
     * @param bytes 原始的字符串生成的byte[]
     * @param huffmanCodes 生成的编码
     * @return 压缩之后的byte[]
     */
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //1.利用huffmanCodes将bytes转成huffman对应的字符串 zipHuffmanCodeBytes
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b: bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        //2.统计返回的huffmanCodeBytes长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() /8;
        }else {
            len = stringBuilder.length() /8 + 1 ;
        }
        //3.创建存储压缩后的新Byte数组
        byte[] zipHuffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {//每8位对应一个byte,步长为8
            String strByte;
            if (i+8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i,i+8);
            }
            //将strByte转换成一个byte,放入到新的byte数组中
            zipHuffmanCodeBytes[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }
        return zipHuffmanCodeBytes;
    }

    /**
     * 将前面的方法封装起来
     * @param bytes 原始的字节数组
     * @return 压缩之后的编码数组
     */
    public static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);//获得Node类型的列表
        Node huffmanTreeRoot = createHuffmanTree(nodes);//创建Huffman树，获得huffman树的根节点
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);//获得Huffman编码
        return zip(bytes, huffmanCodes);//压缩
    }

    /**
     * 将一个byte转成对应的二进制字符串
     * @param flag 是否要补位的标志 true为需要补高位，false不用，最后一个字节不用补高位
     * @param b 传入的byte
     * @return byte对应的二进制字符串
     */
    public static String byteToBitString(boolean flag,byte b){
        int temp = b;//使用变量保存byte并转换成int类型
        //如果是正数我们还需要补高位
        if (flag){
            temp |= 256;//按位与256
        }
        String str = Integer.toBinaryString(temp);//返回的是temp的二进制补码
        if (flag){
            return str.substring(str.length()-8);//截取后八位
        }else {
            return str;
        }
    }

    /**
     * 完成对压缩数据的解码
     * @param huffmanCodes Huffman编码表
     * @param zipHuffmanBytes 压缩完成的byte数组
     * @return 未压缩的byte数组
     */
    public static byte[] decode(Map<Byte,String> huffmanCodes, byte[] zipHuffmanBytes){
        //1、先得到对应的二进制编码
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转换成二进制字符串
        for (int i = 0; i < zipHuffmanBytes.length; i++) {
            byte b = zipHuffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == zipHuffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //2、根据Huffman编码进行反编译
        //调换Huffman表
        Map<String,Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        //3.创建要给的集合，存放byte
        ArrayList<Byte> list = new ArrayList<>();
        //扫描stringBuilder提取字符
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag){
                //count递增取出key
                String key = stringBuilder.substring(i, i + count);//i不动，count递增，直到匹配到一个字符
                b = map.get(key);//回到Huffman表里面查找
                if (b == null){//没找到
                    count++;
                }else {
                    flag = false;
                }
            }
            list.add(b);//加入到集合
            i += count;//i直接跳到count
        }
        //4.把list里面的内容添加到byte数组中返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }

        return b;
    }

    /**
     * 将一个文件进行压缩
     * @param srcFile 目标文件路径
     * @param dstFile 输出文件路径
     */
    public static void zipFile(String srcFile,String dstFile){
        //创建文件的输入流
        FileInputStream is = null;
        //创建文件的输出流和对象输出流
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try{
            is = new FileInputStream(srcFile);
            //创建与源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //压缩
            byte[] huffmanZip = huffmanZip(b);
            //输出流存放文件
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            //把压缩后的字节数组写入文件
            oos.writeObject(huffmanZip);
            //以对象流的方式写入Huffman编码
            oos.writeObject(huffmanCodes);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 将一个文件进行解压
     * @param srcFile 目标文件路径
     * @param dstFile 输出文件路径
     */
    public static void unZipFile(String srcFile, String dstFile){
        //创建文件输入流
        InputStream is = null;
        //创建文件输出流
        OutputStream os = null;
        //创建文件输入流对象
        ObjectInputStream ois = null;
        try {
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);
            //读取压缩后的字节数组
            byte[] zipHuffmanBytes = (byte[]) ois.readObject();
            //读取Huffman表
            Map<Byte,String> huffmanCodes = (Map<Byte, String>)ois.readObject();
            //解码
            byte[] result = decode(huffmanCodes, zipHuffmanBytes);
            //将结果写入文件
            os = new FileOutputStream(dstFile);
            os.write(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        /*String content = "i like like like java do you like a java";
        System.out.println("原始数据： " + content);

        byte[] huffmanZip = huffmanZip(content.getBytes());
        System.out.println("压缩之后的数据： " + Arrays.toString(huffmanZip));

        byte[] decodeHuffman = decode(huffmanCodes, huffmanZip);
        System.out.println("解压之后的数据： "+ new String(decodeHuffman));*/

        //测试压缩文件
        //String srcFile = "C://Users//liuchang//Desktop//src.bmp";
        //String dstFile = "D://dst.zip";
        //zipFile(srcFile,dstFile);
        //System.out.println("成功");

        //测试解压文件
        String srcFile = "D://dst.zip";
        String dstFile = "D://dst2.bmp";
        unZipFile(srcFile,dstFile);
        System.out.println("成功");
    }
}
