package Algorithm.Kruskal;

/**
 * Kruskal算法
 */

//定义边
class EData{
      char start;
      char end;
      int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}

public class Kruskal {

    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF表示两个顶点不能联通
    private static final int INF = Integer.MAX_VALUE;

    //构造函数
    public Kruskal(char[] vertexs,int[][] matrix){
        //初始化顶点
        this.vertexs = new char[vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.matrix = new int[vertexs.length][vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的数量
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if (this.matrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }
    }

    public void show(){
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d",this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 使用冒泡排序从小到大排序边的权值
     * @param edges
     */
    private void sortEdges(EData[] edges){
        for (int i = 0; i < edges.length-1; i++) {
            for (int j = 0; j < edges.length-1-i; j++) {
                if (edges[j].weight > edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     * 找到对应顶点的下标
     * @param ch 顶点的值
     * @return 找到则返回顶点的下标，否则返回-1
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch){
                return i;
            }
        }
        return -1;
    }

    //获取图中的边放入到EData数组中
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为 i 的顶点的终点, 用于后面判断两个顶点的终点是否相同
     * @param ends 数组就是记录了各个顶点对应的终点是哪个,ends 数组是在遍历过程中，逐步形成
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是下标为 i 的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    public void kruskal(){
        int index = 0;//表示最后结果数组的索引
        //用于保存已有的最小生成树中的每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];
        //获取图中所有的边的集合，一共有12边
        EData[] edges = getEdges();
        //System.out.println("图的边的集合=" + Arrays.toString(edges) + " 共" + edges.length);
        //按照边的权值大小进行排序
        sortEdges(edges);

        //遍历 edges 数组，将边添加到最小生成树中时，判断准备加入的边是否形成了回路，如果没有，就加入 rets,否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第 i 条边的第1个顶点
            int p1 = getPosition(edges[i].start);
            //获取到第 i 条边的第 2 个顶点
            int p2 = getPosition(edges[i].end);
            //获取 p1 这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取 p2 这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            //是否构成回路
            if ( m!=n ){ //没有构成回路
                ends[m] = n;//设置 m 在"已有最小生成树"中的终点
                rets[index++] = edges[i];//有一条边加入到 rets 数组
            }
        }
        System.out.println("最小生成树");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}};

        Kruskal kruskal = new Kruskal(vertexs, matrix);
        kruskal.kruskal();
    }
}
