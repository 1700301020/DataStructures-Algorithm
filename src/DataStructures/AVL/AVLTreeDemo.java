package DataStructures.AVL;

/**
 * AVL树
 */
//定义结点
class Node{
    int value;
    Node leftNode;
    Node rightNode;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加结点
     * @param node 待添加的结点
     */
    public void add(Node node){
        if(node == null){
            return;
        }
        //判断传入结点的值和当前子树根节点的值的关系
        if (node.value < this.value){
            if (this.leftNode == null){//当前结点的左子树是否为空
                this.leftNode = node;
            }else {
                this.leftNode.add(node);//递归处理
            }
        }else {
            if (this.rightNode == null){//当前结点的右子树是否为空
                this.rightNode = node;
            }else {
                this.rightNode.add(node);
            }
        }

        //当添加完一个结点后，如果左右子树的高度差的绝对值大于1,左旋
        if ((rightHeight() - leftHeight()) >1){
            //如果当前结点的右子树的左子树的高度大于右子树的高度
            if (rightNode != null && rightNode.leftHeight() > rightNode.rightHeight()) {
                //当前结点的右子树进行右旋
                rightNode.rightRotate();
                //当前结点进行左旋
                leftRotate();
            }else {
                leftRotate();//直接左旋
            }
            return;//处理完当前添加的结点就返回，不继续
        }

        //当添加完一个结点后，如果左右子树的高度差的绝对值大于1,右旋
        if ((leftHeight() - rightHeight()) >1){
            //如果当前结点的左子树的右子树的高度大于左子树的高度
            if (leftNode != null && leftNode.rightHeight() > leftNode.leftHeight()) {
                //当前结点的左子树进行左旋
                leftNode.leftRotate();
                //当前结点进行右旋
                rightRotate();
            }else {
                rightRotate();//直接右旋
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this.leftNode != null){
            this.leftNode.infixOrder();
        }
        System.out.println(this.value);
        if (this.rightNode != null) {
            this.rightNode.infixOrder();
        }
    }

    /**
     * 查找要删除的结点
     * @param value 希望删除的值
     * @return 找到则返回结点，找不到则返回null
     */
    public Node search(int value){
        if (value == this.value){//就是当前结点
            return this;
        }else if (value < this.value){//小于当前结点
            //左子节点为空
            if (this.leftNode == null){
                return null;
            }
            return this.leftNode.search(value);
        }else {//大于等于当前结点
            //右子结点为空
            if (this.rightNode == null){
                return null;
            }
            return this.rightNode.search(value);
        }

    }

    /**
     * 找到删除结点的父节点
     * @param value 要删除的结点值
     * @return 找到则返回结点，找不到则返回null
     */
    public Node searchParent(int value){
        if ((this.leftNode != null && this.leftNode.value == value)//当前结点为待删除结点的父节点
                || (this.rightNode != null && this.rightNode.value == value)) {
            return this;
        }else {
            //如果小于，往左找
            if (value < this.value && this.leftNode != null){
                return this.leftNode.searchParent(value);
            }else if (value >= this.value && this.rightNode != null){//大于等于往右边找
                return this.rightNode.searchParent(value);
            }else {
                return null;//没有找到返回空
            }
        }
    }

    /**
     * 返回左子树的高度
     * @return
     */
    public int leftHeight(){
        if (leftNode == null){
            return 0;
        }
        return leftNode.height();
    }

    /**
     * 返回右子树的高度
     * @return
     */
    public int rightHeight(){
        if (rightNode == null){
            return 0;
        }
        return rightNode.height();
    }

    /**
     * 返回以该结点为根节点的树的高度
     * @return 高度
     */
    public int height(){
        return Math.max(leftNode == null ? 0:leftNode.height(),rightNode == null ? 0:rightNode.height()) + 1;
    }

    /**
     * 左旋
     */
    public void leftRotate(){
        //创建一个新的结点等于当前结点的值
        Node newNode = new Node(value);
        //新节点的左子树等于当前结点的左子树
        newNode.leftNode = leftNode;
        //新节点的右子树等于当前结点的右子树的左子树
        newNode.rightNode = rightNode.leftNode;
        //当前结点的值替换成当前结点的右子树的值
        value = rightNode.value;
        //当前结点的右子树等于右子树的右子树
        rightNode = rightNode.rightNode;
        //当前结点的左子树等于新结点
        leftNode = newNode;
    }

    /**
     * 右旋
     */
    public void rightRotate(){
        //创建一个新的结点等于当前结点的值
        Node newNode = new Node(value);
        //新节点的右子树等于当前结点的右子树
        newNode.rightNode = rightNode;
        //新节点的左子树等于当前结点的左子树的右子树
        newNode.leftNode = leftNode.rightNode;
        //当前结点的值等于左子树结点的值
        value = leftNode.value;
        //当前结点的左子树等于左子树的左子树
        leftNode = leftNode.leftNode;
        //当前结点的右子树等于新节点
        rightNode = newNode;
    }
}

//创建AVLTree
class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 添加结点
     * @param node 待添加结点
     */
    public void add(Node node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }else {
            System.out.println("空树");
        }
    }

    /**
     * 查找待删除结点
     * @param value 待删除的值
     * @return 找到则返回结点，找不到则返回null
     */
    public Node search(int value){
        if (root == null){
            return null;
        }else {
            return root.search(value);
        }
    }

    /**
     * 查找待删除结点的父节点
     * @param value 待删除的值
     * @return 找到则返回结点，找不到则返回null
     */
    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    /**
     * 删除结点
     * @param value 待删除结点的值
     */
    public void delNode(int value){
        if (root == null){//空树
            return;
        }else {
            //1.先去找到要删除的结点
            Node targetNode = search(value);
            //没有找到
            if (targetNode == null){
                return;
            }
            //如果只有一个根结点，此时root=targetNode
            if (root.leftNode == null && root.rightNode == null){
                root = null;
                return;
            }
            //2.找到父节点
            Node parent = searchParent(value);
            //3.如果删除的是叶子结点
            if (targetNode.leftNode == null && targetNode.rightNode ==null) {
                //判断是父节点的左子节点还是右子结点
                if (parent.leftNode != null && parent.leftNode.value == value){//左节点
                    parent.leftNode = null;
                }else if (parent.rightNode != null && parent.rightNode.value == value){//右节点
                    parent.rightNode = null;
                }
            }else if (targetNode.leftNode != null && targetNode.rightNode != null){//4.如果删除两棵子树
                int rightTreeMin = delRightTreeMin(targetNode.rightNode);//递归找到右边子树的最小值
                targetNode.value = rightTreeMin;//待删除的结点值赋值为找到的最小值
            }else {//5.删除只有一棵子树
                //如果要删除的结点有左子结点
                if (targetNode.leftNode != null){
                    if (parent!=null) {
                        //如果 targetNode 是 parent 的左子结点
                        if (parent.leftNode.value == value) {
                            parent.leftNode = targetNode.leftNode;
                        } else {//如果 targetNode 是 parent  的右子结点
                            parent.rightNode = targetNode.leftNode;
                        }
                    }else {//parent为空，删除的是根节点，且只有一个左子树
                        root = targetNode.leftNode;
                    }
                }else {//如果要删除的结点有右子结点
                    if (parent != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if (parent.leftNode.value == value) {
                            parent.leftNode = targetNode.rightNode;
                        } else {//如果 targetNode 是 parent  的右子结点
                            parent.rightNode = targetNode.rightNode;
                        }
                    }else {//parent为空，删除的是根节点，且只有一个右子树
                        root = targetNode.rightNode;
                    }
                }
            }
        }
    }

    /**
     * 返回的 以 node 为根结点的二叉排序树的最小结点的值
     * 删除 node 为根结点的二叉排序树的最小结点
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 以 node 为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node){
        Node temp = node;
        //循环的查找左子节点，直到最小值
        while (temp.leftNode != null){
            temp = temp.leftNode;
        }
        //退出循环，找到了最小值并删除
        delNode(temp.value);
        //返回最小值
        return temp.value;
    }
}

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        //创建AVLTree对象
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历
        //System.out.println("中序遍历");
        //avlTree.infixOrder();

        System.out.println("调整了的树的高度：" + avlTree.getRoot().height());
        System.out.println("调整了的左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("调整了的右子树的高度：" + avlTree.getRoot().rightHeight());
        System.out.println("根节点为：" + avlTree.getRoot());
    }
}
