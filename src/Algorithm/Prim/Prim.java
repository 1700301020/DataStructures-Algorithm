package Algorithm.Prim;

import java.util.Arrays;

/**
 * Prim算法
 */
//定义一个图
class Graph{
    int verxs;//顶点个数
    char[] data;//存放结点数据
    int[][] weight;//存放边的权值

    public Graph (int verxs){
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}

//定义最小生成树
class MST{
    /**
     * 生成一个图
     * @param graph 图对象
     * @param verxs 顶点数
     * @param data 顶点的数据
     * @param weight 图的邻接矩阵
     */
    public void createGraph(Graph graph, int verxs, char[] data, int[][] weight){
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //输出一个图
    public void showGraph(Graph graph){
        for (int[] weight: graph.weight){
            System.out.println(Arrays.toString(weight));
        }
    }

    /**
     *
     * @param graph 图
     * @param start 开始结点
     */
    public void prim(Graph graph, int start){
        int[] visited = new int[graph.verxs];
        visited[start] = 1;//当前结点标记为已访问
        //记录顶点下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//记录最小权值
        for (int k = 1; k < graph.verxs ; k++){//算法结束后有graph.verxs-1条边
            //这个是确定每一次生成的子图 ，和哪个结点的距离最近
            for (int i = 0; i < graph.verxs; i++) {// i 结点表示被访问过的结点
                for (int j = 0; j < graph.verxs; j++) {//j 结点表示还没有访问过的结点
                    if (visited[i]==1 && visited[j]==0 && minWeight > graph.weight[i][j]){
                        //替换 minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">权值：" + minWeight);
            //当前结点标记已访问
            visited[h2] = 1;
            //重置最小权值
            minWeight = 10000;
        }
    }
}


public class Prim {
    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int vertex = data.length;
        int[][] weight = {
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};

        Graph graph = new Graph(vertex);
        MST mst = new MST();
        mst.createGraph(graph,vertex,data,weight);
        mst.showGraph(graph);

        mst.prim(graph,0);
    }
}

