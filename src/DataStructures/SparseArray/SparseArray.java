package DataStructures.SparseArray;

/**
 * 稀疏数组与二维数组的互相转换
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0:表示没有棋子，1：表示黑子 2：表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组:");
        output(chessArr1);

        //将二维数组转稀疏数组
        //1、先遍历二维数组得到非零数据的个数
        int sum = 0;
        for (int[] row: chessArr1) {
            for (int data: row) {
                if (data != 0) {
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非零的值存放到sparseArr中
        int count = 0;//用于记录是第几个非零数据
        for (int i = 0; i <11 ; i++) {
            for (int j = 0; j <11 ; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                   sparseArr[count][0] = i;
                   sparseArr[count][1] = j;
                   sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println("\n稀疏数组为:");
        output(sparseArr);

        //将稀疏数组恢复成原始二维数组
        /**
         * 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
         * 在读取稀疏数组后几行的数据，并赋给 原始的二维数组
         */
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i <sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出还原后的二维数组
        System.out.println("\n处理后数组为:");
        output(chessArr2);
    }

    //输出函数
    public static void output(int[][] chessArr1) {
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
