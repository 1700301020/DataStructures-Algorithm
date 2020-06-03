package Algorithm.DivideandConquer;

/**
 * 分治算法解决汉诺塔
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5,"A","B","C");
    }

    public static void hanoiTower(int num, String a,String b,String c){
        //如果是有一个盘， A->C
        if (num == 1){
            System.out.println("第" + num + "个盘子：" + a + "->" + c);
        }else {
            //如果我们有 n >= 2  情况，我们总是可以看做是两个盘 1.最下边的盘 2. 上面的盘
            //先把 最上面的盘 A->B
            hanoiTower(num-1,a,c,b);
            //把最下边的盘 A->C
            System.out.println("第" + num + "个盘子：" + a + "->" + c);
            //把 B 塔的所有盘 从 B->C
            hanoiTower(num-1,b,a,c);
        }
    }
}
