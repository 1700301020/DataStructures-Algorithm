package Algorithm.Dijkstra;

import java.util.Arrays;

/**
 * Dijkstra算法
 */
//定义图
class Graph{
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//已访问顶点集合

    public Graph(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    public void show(){
        for (int[] link : matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * dijkstra算法
     * @param index 出发顶点的下标
     */
    public void dijkstra(int index){
        vv = new VisitedVertex(vertexs.length, index);
        upDate(index);//更新 index 顶点到周围顶点的距离和前驱顶点
        for (int i = 1; i < vertexs.length; i++) {
            index = vv.upDateArr();//选择新的访问结点
            upDate(index);//更新 index 顶点到周围顶点的距离和前驱顶点
        }
    }



    /**
     * 更新 index 下标顶点到周围顶点的距离和周围顶点的前驱顶点,
     * @param index
     */
    private void upDate(int index){
        int len = 0;
        //根据遍历我们的邻接矩阵的matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            //len含义是: 出发顶点到index顶点的距离 + 从index顶点到i顶点的距离的和
            len = vv.getDis(index) + matrix[index][i];
            // 如果 i 顶点没有被访问过，并且 len  小于出发顶点到 i 顶点的距离，就需要更新
            if (!vv.isVisited(i) && len < vv.getDis(i)){
                //更新 j 顶点的前驱为 index 顶点
                vv.updatePre(i,index);
                //更新出发顶点到 j 顶点的距离
                vv.updateDis(i,len);
            }
        }
    }

    public void showResult(){
        vv.showResult();
    }
}

//已访问顶点集合
class VisitedVertex{
    // 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
    public int[] already_visited_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited_vertex;
    // 记录出发顶点到其他所有顶点的距离
    public int[] dis;

    /**
     * 构造器
     * @param length 顶点个数
     * @param index 出发顶点对应的下标
     */
    public VisitedVertex(int length, int index){
        this.already_visited_arr = new int[length];
        this.pre_visited_vertex = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis,Integer.MAX_VALUE);
        this.already_visited_arr[index] = 1;//设置出发顶点被访问过
        this.dis[index] = 0;//设置出发顶点的访问距离为 0
    }

    public void showResult(){
        for (int i : already_visited_arr){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : pre_visited_vertex){
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis){
            System.out.print(i + " ");
        }
        System.out.println();

        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for (int i : dis){
            if (i != 65535){
                System.out.print(vertex[count] + "(" + i+ ") ");
            }else {
                System.out.println("N");
            }
            count++;
        }
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return 访问过就返回true 否则返回false
     */
    public boolean isVisited(int index){
        return already_visited_arr[index] == 1;
    }

    /**
     * 更新出发点到顶点index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 更新pre这个结点的前驱结点为index结点
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index){
        pre_visited_vertex[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点
     * @return 新节点的下标
     */
    public int upDateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_visited_arr.length; i++) {
            if (already_visited_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        already_visited_arr[index] = 1;//更新被访问过
        return index;
    }
}

public class Dijkstra {
    private static final int INF = 65535;

    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0]=new int[]{INF,5,7,INF,INF,INF,2};
        matrix[1]=new int[]{5,INF,INF,9,INF,INF,3};
        matrix[2]=new int[]{7,INF,INF,INF,8,INF,INF};
        matrix[3]=new int[]{INF,9,INF,INF,INF,4,INF};
        matrix[4]=new int[]{INF,INF,8,INF,INF,5,4};
        matrix[5]=new int[]{INF,INF,INF,4,5,INF,6};
        matrix[6]=new int[]{2,3,INF,INF,4,6,INF};

        //创建一个图
        Graph graph = new Graph(vertex, matrix);
        //graph.show();
        graph.dijkstra(2);
        graph.showResult();
    }
}


