package Algorithm.DynamicProgramming;

/**
 * 动态规划解决背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品重量
        int[] val = {1500,3000,2000};//物品价值
        int m = 4;//背包容量
        int n = val.length;//物品个数

        //创建二维数组，v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];
        //记录放入商品的情况
        int[][] path = new int[n+1][m+1];

        //初始化 v[i][0]=v[0][j]=0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //动态规划
        for (int i = 1; i < v.length; i++) {//不处理第一行 i 是从 1 开始的
            for (int j = 1; j < v[0].length; j++) {//不处理第一列 j 是从 1 开始的
                if (w[i-1] > j){ // 因为我们程序i是从1开始的，因此原来公式中的w[i]修改成 w[i-1], v[i]修改成val[i-1]
                    v[i][j]=v[i-1][j];
                }else {
                    //v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    if (v[i-1][j] < val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        //记录最优情况
                        path[i][j] = 1;
                    }else {
                        v[i][j]=v[i-1][j];
                    }
                }
            }
        }

        show(v);

        //输出结果
        int i = path.length -1;
        int j = path[0].length - 1;
        while ( i > 0 && j > 0){//从最后开始找
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入背包\n", i);
                j -= w[i-1]; //背包剩余多少容量
            }
            i--;
        }
    }

    /**
     * 打印矩阵
     * @param v 目标矩阵
     */
    public static void show(int[][] v) {
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
    }
}
