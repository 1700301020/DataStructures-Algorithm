package DataStructures.Tree.ThreadBinaryTree;

/**
 * 线索化二叉树，中序遍历
 */

//定义结点
class Node{
    private int no;
    private String name;
    private Node leftNode;
    private Node rightNode;

    private int leftType;//0为指向左子树，1为指向前驱结点
    private int rightType;//0为指向右子树，1为指向后继结点

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        //向左递归
        if (this.leftNode != null){
            this.leftNode.preOrder();
        }
        //向右递归
        if (this.rightNode != null){
            this.rightNode.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        //向左递归
        if (this.leftNode != null){
            this.leftNode.infixOrder();
        }
        System.out.println(this);
        //向右递归
        if (this.rightNode != null){
            this.rightNode.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        //向左递归
        if (this.leftNode != null){
            this.leftNode.postOrder();
        }
        //向右递归
        if (this.rightNode != null){
            this.rightNode.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @param no 关键词
     * @return 返回结点或者null
     */
    public Node perOrderSearch(int no){
        //比较当前结点是不是
        System.out.println("前序查找+1");
        if (this.no == no){
            return this;
        }

        Node resNode = null;//辅助结点
        //向左递归
        if (this.leftNode != null){
            resNode = this.leftNode.perOrderSearch(no);
        }
        //如果左递归找到则返回
        if (resNode != null){
            return resNode;
        }

        //向右递归
        if (this.rightNode != null){
            resNode = this.rightNode.perOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序查找
     * @param no 关键词
     * @return 结点或者null
     */
    public Node infixOrderSearch(int no){
        Node resNode = null;//辅助结点
        //向左递归
        if (this.leftNode != null){
            resNode = this.leftNode.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        //比较当前结点
        System.out.println("中序查找+1");
        if (this.no == no){
            return this;
        }

        //向右递归
        if (this.rightNode != null){
            resNode = this.rightNode.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序查找
     * @param no 关键词
     * @return 结点或者null
     */
    public Node postOrderSearch(int no){
        Node resNode = null;//辅助结点
        //向左递归
        if (this.leftNode != null){
            resNode = this.leftNode.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        //向右递归
        if (this.rightNode != null){
            resNode = this.rightNode.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        //比较当前结点
        System.out.println("后序查找+1");
        if (this.no == no){
            return this;
        }
        return null;
    }

    //递归删除结点
    //如果删除的是叶子结点，则删除该结点
    //如果删除的是非叶子结点，则删除该子树
    public void delNode(int no){
        //当前结点的左结点不为空，且就是要删除的结点
        if (this.leftNode != null && this.leftNode.no == no){
            this.leftNode = null;
            return;
        }
        //当前结点的右结点不为空，且就是要删除的结点
        if (this.rightNode != null && this.rightNode.no == no) {
            this.rightNode = null;
            return;
        }
        //向左递归
        if (this.leftNode != null){
            this.leftNode.delNode(no);
        }
        //向右递归
        if (this.rightNode != null){
            this.rightNode.delNode(no);
        }
    }
}

//定义一个线索化二叉树
class ThreadBinaryTree {
    private Node rootNode;
    //前驱结点
    private Node preNode = null;

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    //前序遍历
    public void preOrder(){
        if (this.rootNode != null){
            this.rootNode.preOrder();
        }else {
            System.out.println("根节点为空");
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.rootNode != null){
            this.rootNode.infixOrder();
        }else {
            System.out.println("根节点为空");
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.rootNode != null) {
            this.rootNode.postOrder();
        }else {
            System.out.println("根节点为空");
        }
    }

    //前序查找
    public Node preOrderSearch(int no){
        if (rootNode != null){
            return rootNode.perOrderSearch(no);
        }else {
            return null;
        }
    }

    //中序查找
    public Node infixOrderSearch(int no){
        if (rootNode != null){
            return rootNode.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    //后序查找
    public Node postOrderSearch(int no){
        if (rootNode != null){
            return rootNode.postOrderSearch(no);
        }else {
            return null;
        }
    }

    //删除结点
    public void delNode(int no){
        if (rootNode != null) {
            //如果只有一个结点,且为删除结点
            if (rootNode.getNo() == no){
                rootNode = null;
            }else {
                //递归删除
                rootNode.delNode(no);
            }
        }else {
            System.out.println("空树");
        }
    }


    //重载
    public void threadedNodes(){
        this.threadedNodes(rootNode);
    }
    /**
     * 二叉树中序线索化
     * @param node 需要线索化的结点
     */
    public void threadedNodes(Node node){
        //结点为空
        if (node == null) {
            return;
        }

        //先线索化左子树
        threadedNodes(node.getLeftNode());

        //线索化当前结点
        //处理当前结点的前驱结点
        if (node.getLeftNode() == null) {
            //当前结点的左指针指向前驱结点
            node.setLeftNode(preNode);
            //修改当前结点的左指针的类型,指向前驱结点
            node.setLeftType(1);
        }
        //处理当前结点的后继结点
        if (preNode != null && preNode.getRightNode() == null) {
            //让前驱结点的右指针指向当前结点
            preNode.setRightNode(node);
            //修改前驱结点的右指针类型,指向后驱结点
            preNode.setRightType(1);
        }
        //前驱结点跟着node走!!!
        preNode = node;

        //线索化右子树
        threadedNodes(node.getRightNode());
    }

    //遍历线索化二叉树的方法，中序
    public void threadList(){
        Node node = rootNode;//辅助变量
        while (node != null){
            //找到leftType=1的结点
            while (node.getLeftType() ==0){
                node = node.getLeftNode();
            }
            System.out.println(node);
            //当前结点的右指针是指向后继结点
            while (node.getRightType() == 1){
                node = node.getRightNode();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRightNode();
        }
    }
}

public class ThreadBinaryTreeDemo {
    //{8,3,10,1,14,6}
    public static void main(String[] args) {
        //创建结点
        Node root = new Node(1, "tom");
        Node node1 = new Node(3, "jack");
        Node node2 = new Node(6, "smith");
        Node node3 = new Node(8, "marry");
        Node node4 = new Node(10, "king");
        Node node5 = new Node(14, "dim");

        //创建二叉树
        root.setLeftNode(node1);
        root.setRightNode(node2);
        node1.setLeftNode(node3);
        node1.setRightNode(node4);
        node2.setLeftNode(node5);

        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        threadBinaryTree.setRootNode(root);
        threadBinaryTree.threadedNodes();

        //System.out.println(node4.getLeftNode());
        //System.out.println(node4.getRightNode());

        threadBinaryTree.threadList();
    }
}
