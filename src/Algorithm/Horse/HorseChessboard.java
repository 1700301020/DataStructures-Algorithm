package Algorithm.Horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘问题
 */
public class HorseChessboard {
    private static int X;//棋盘列数
    private static int Y;//棋盘行数
    private static boolean visited[];//标记当前位置是否被访问
    private static boolean finished;//标记棋盘所有位置是否都被访问 如果为true 表示成功

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;//初始位置
        int column = 1;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X* Y];
        long start = System.currentTimeMillis();//开始时间
        traversalChessboard(chessboard,row-1,column-1,1);
        long end = System.currentTimeMillis();//结束时间
        System.out.println("耗时 " + (end - start));

        for (int[] cows : chessboard){
            for (int step : cows){
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 骑士周游算法
     * @param chessboard 棋盘
     * @param row 骑士当前位置的横坐标 行
     * @param column 骑士当前位置的纵坐标 列
     * @param step 是第几步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step){
        chessboard[row][column] = step;
        visited[row * X + column] = true;//标记当前位置已访问
        ArrayList<Point> ps = next(new Point(column, row));//x代表的是列
        //对ps内的所有点的下一步的位置的数目进行非递减排序
        sort(ps);//贪心算法，选最少的
        //遍历ps
        while (!ps.isEmpty()){
            Point p = ps.remove(0);//取出下一个可以走的位置
            //判断该点是否已经访问过
            if (!visited[p.y* X + p.x]){
                traversalChessboard(chessboard,p.y,p.x,step+1);
            }
        }
        //判断骑士是否完成了任务，使用step和应该走的步数比较
        if (step < X * Y && !finished){//表示没有完成任务，将整个棋盘置0
            chessboard[row][column] = 0;
            visited[row *X +column] = false;
        }else {
            finished = true;
        }
    }

    /**
     * 根据当前位置计算还能走哪些位置
     * @param curPoint 当前位置
     * @return 可以走的位置的数组
     */
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //表示可以走的8个位置
        if ((p1.x = curPoint.x - 2) >= 0 &&  (p1.y = curPoint.y -1) >=0 ){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 &&  (p1.y = curPoint.y -2) >=0 ){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X &&  (p1.y = curPoint.y -2) >=0 ){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X &&  (p1.y = curPoint.y -1) >=0 ){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 &&  (p1.y = curPoint.y +1) < Y ){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 &&  (p1.y = curPoint.y +2 ) < Y){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X &&  (p1.y = curPoint.y +1) < Y ){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x  +1) < X &&  (p1.y = curPoint.y +2) < Y ){
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前这一步的所有的下一步的选择位置，进行非递减排序,减少回溯
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取点1的下一步的所有位置个数
                int size1 = next(o1).size();
                //获取点2的下一步的所有位置个数
                int size2 = next(o2).size();
                if (size1 < size2){
                    return -1;
                }else if (size1 == size2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
