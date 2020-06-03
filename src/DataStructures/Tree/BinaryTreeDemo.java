package DataStructures.Tree;

/**
 * 二叉树的遍历
 */
//定义结点
class Node{
    private int no;
    private String name;
    private Node leftNode;
    private Node rightNode;

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

//定义一个二叉树
class BinaryTree{
    private Node rootNode;

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
}

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建结点
        Node root = new Node(1, "宋江");
        Node node1 = new Node(2, "吴用");
        Node node2 = new Node(3, "卢俊义");
        Node node3 = new Node(4, "林冲");
        Node node4 = new Node(5, "关胜");

        //手动创建二叉树
        root.setLeftNode(node1);
        root.setRightNode(node2);
        node2.setRightNode(node3);
        node2.setLeftNode(node4);
        binaryTree.setRootNode(root);

        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();
        //System.out.println("中序遍历");
        //binaryTree.infixOrder();
        //System.out.println("后序遍历");
        //binaryTree.postOrder();

        //Node resNode = binaryTree.preOrderSearch(5);//比较4次
        //Node resNode = binaryTree.infixOrderSearch(5);//比较3次
        //Node resNode = binaryTree.postOrderSearch(5);//比较3次
        /*if (resNode != null){
            System.out.printf("no=%d name=%s",resNode.getNo(),resNode.getName());
        }else {
            System.out.println("找不到");
        }*/
        //binaryTree.delNode(5);//删除叶节点
        binaryTree.delNode(3);//删除子数
        System.out.println("删除后");
        binaryTree.preOrder();
    }
}
