package DataStructures.Recursion;

/**
 * 迷宫问题
 * 说明
 * 1、map 表示地图
 * 2、x,y 表示起点
 * 3、如果小球能到map[6][5]位置，则说明通路找到
 * 4、0表示没有走过、1表示为墙、2表示通路可以走、3表示该点已经走过，但是走不通
 * 5、前进策略：下->右->上->左，如果走不通再回溯
 */
public class MiGong {

    private static int count = 0;//统计步数

    public static void main(String[] args) {
        int[][] map = initMap();
        System.out.println("初始化地图");
        outMap(map);

        /*findWay(map,1,1);
        System.out.println("小球走过的路径");
        outMap(map);
        System.out.println("步数为: " + count);*/

        findWay2(map,1,1);
        System.out.println("小球走过的路径");
        outMap(map);
        System.out.println("步数为: " + count);
    }

    /**
     * 使用递归回溯来给小球找路
     * @param map 表示地图
     * @param x 起点横坐标
     * @param y 起点纵坐标
     * @return 找到返回true，否则false
     */
    public static boolean findWay(int[][] map, int x, int y){
        count = 0;
        if (map[6][5] == 2){//通路已经找到
            return true;
        }else {
            if (map[x][y] == 0){//如果当前的这个点还没有走过
                //前进策略：下->右->上->左
                map[x][y] = 2;//假设该点是可以走通的
                count++;
                if (findWay(map, x+1, y)){//向下
                    return true;
                }else if (findWay(map, x, y+1)){//向右
                    return true;
                }else if(findWay(map, x-1, y)){//向上
                    return true;
                }else if (findWay(map, x, y-1)){//向左
                    return true;
                }else {
                    //说明该点是死路
                    map[x][y] = 3;
                    count--;
                    return false;
                }
            }else {//如果不是等于0，可能是1，2，3
                return false;
            }
        }
    }

    public static boolean findWay2(int[][] map, int x, int y){
        if (map[6][5] == 2){//通路已经找到
            return true;
        }else {
            if (map[x][y] == 0){//如果当前的这个点还没有走过
                //前进策略：上->右->下->左
                map[x][y] = 2;//假设该点是可以走通的
                count++;
                if (findWay2(map, x-1, y)){//向上
                    return true;
                }else if (findWay2(map, x, y+1)){//向右
                    return true;
                }else if(findWay2(map, x+1, y)){//向下
                    return true;
                }else if (findWay2(map, x, y-1)){//向左
                    return true;
                }else {
                    //说明该点是死路
                    map[x][y] = 3;
                    count--;
                    return false;
                }
            }else {//如果不是等于0，可能是1，2，3
                return false;
            }
        }
    }

    /**
     * 初始化地图
     * @return
     */
    public static int[][] initMap() {
        //先创建一个二维数组模拟迷宫
        int [][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        return map;
    }

    /**
     * 输出地图
     * @param map
     */
    public static void outMap(int[][] map) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
