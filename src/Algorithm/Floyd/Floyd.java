package Algorithm.Floyd;

import java.util.Arrays;

/**
 * Floyd算法
 */
//定义图
class Graph {
    private char[] vertex;//顶点数组
    private int[][] dis;//从各顶点出发到其他顶点的距离
    private int[][] pre;//保存到达目标顶点的前驱结点

    public Graph(int len, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[len][len];

        //初始化,存放前驱结点的下标
        for (int i = 0; i < len; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    public void show(){
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        for (int i = 0; i < dis.length; i++) {
            //输出pre
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            //输出dis
            for (int j = 0; j < dis.length; j++) {
                System.out.print("(" + vertex[i] + "到" + vertex[j] + "的最短路径是" + dis[i][j] + ")");
            }
            System.out.println();
        }
    }

    public void floyd(){
        int len = 0;
        for (int mid = 0; mid < dis.length; mid++) {//遍历中间结点
            for (int start = 0; start < dis.length; start++) {//遍历开始结点
                for (int end = 0; end < dis.length; end++) {//遍历结束结点
                    len = dis[start][mid] + dis[mid][end];
                    if (len < dis[start][end]){
                        dis[start][end] = len;
                        pre[start][end] = pre[mid][end];//更新前驱结点
                    }
                }
            }
        }
    }
}
public class Floyd {
    private static final int INF = 65535;

    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[] { 0, 5, 7, INF, INF, INF, 2 };
        matrix[1] = new int[] { 5, 0, INF, 9, INF, INF, 3 };
        matrix[2] = new int[] { 7, INF, 0, INF, 8, INF, INF };
        matrix[3] = new int[] { INF, 9, INF, 0, INF, 4, INF };
        matrix[4] = new int[] { INF, INF, 8, INF, 0, 5, 4 };
        matrix[5] = new int[] { INF, INF, INF, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, INF, INF, 4, 6, 0 };

        Graph graph = new Graph(vertex.length,matrix,vertex);
        graph.floyd();
        graph.show();

    }
}
