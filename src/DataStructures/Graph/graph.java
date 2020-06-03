package DataStructures.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class graph {

    private ArrayList<String> vertexList;//顶点集合
    private int[][] edges;//邻接矩阵
    private int numOfEdges;//边的数目
    private boolean[] isVisited;//记录某个结点是否被访问

    //初始化
    public graph(int number) {
        edges = new int[number][number];
        vertexList = new ArrayList<>(number);
        numOfEdges = 0;
    }

    /**
     * 插入顶点
     * @param vertex 待插入顶点
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 插入边
     * @param v1 顶点1
     * @param v2 顶点2
     * @param weight 0代表无连接，1代表有连接
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 得到第一个邻接结点的下标
     * @param index 当前结点的下标
     * @return 若找到则返回邻接结点的下标，不存在则返回-1
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接结点的下标来获取下一个邻接结点
     * @param v1 当前结点
     * @param v2 已经被访问过的邻接结点
     * @return 若找到则返回邻接结点的下标，不存在则返回-1
     */
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 对一个结点深度搜索
     * @param isVisited
     * @param i
     */
    private void DFS(boolean[] isVisited,int i){
        //首先访问该节点
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设置为已访问
        isVisited[i] = true;
        //查找当前结点的第一个邻接结点
        int w = getFirstNeighbor(i);
        while (w != -1){//说明找到
            if (!isVisited[w]){//如果该邻接结点没有被访问
                DFS(isVisited,w);
            }
            w = getNextNeighbor(i, w);//如果被访问，那就继续找当前结点的下一个邻接结点
        }
    }

    //重载DFS，遍历所有结点
    public void DFS(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){//没有被访问
                DFS(isVisited,i);
            }
        }
    }
    /**
     * 对一个结点广度度搜索
     * @param isVisited
     * @param i
     */
    private void BFS(boolean[] isVisited,int i){
        int u; //队列头节点的下标
        int w; //邻接结点的下标
        LinkedList<Integer> queue = new LinkedList<>();//定义队列
        //访问结点
        System.out.print(getValueByIndex(i) + "->");
        //标记为已访问
        isVisited[i] = true;
        //将当前结点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()){
            //取出头节点的下标
            u = queue.removeFirst();
            //查找当前第一个邻接结点的下标
            w = getFirstNeighbor(u);
            while (w != -1){//说明找到
                if (!isVisited[w]){//如果第一个邻接结点未访问
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;//标记访问
                    queue.addLast(w);//入队
                }
                w = getNextNeighbor(u,w);//查找当前结点的下一个邻接结点的下一个邻接结点
            }
        }
    }

    //重载BFS
    public void BFS(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                BFS(isVisited,i);
            }
        }
    }

    //获取顶点的数目
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //获取边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }

    //获取某个下标对应的顶点
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //获取某条边的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //显示图的矩阵
    public void show(){
        for(int[] cow : edges){
            System.out.println(Arrays.toString(cow));
        }
    }

    public static void main(String[] args) {
        graph graph = new graph(8);
        String[] vertexs = {"1","2","3","4","5","6","7","8"};
        for (String vertex :vertexs){
            graph.insertVertex(vertex);
        }

        //添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);

        //graph.show();

        graph.DFS();

        graph.BFS();
    }
}
