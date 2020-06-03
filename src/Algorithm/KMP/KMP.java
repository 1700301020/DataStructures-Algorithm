package Algorithm.KMP;

import java.util.Arrays;

/**
 * KMP算法
 */
public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = getKmpNext(str2);
        System.out.println(Arrays.toString(next));

        int kmpSearch = kmpSearch(str1, str2, next);
        System.out.println(kmpSearch);
    }

    /**
     * KMP算法
     * @param str1 源字符串
     * @param str2 子串
     * @param next 子串匹配表
     * @return 返回第一个匹配的位置，反之返回-1
     */
    public static int kmpSearch(String str1,String str2, int[] next){
        for (int i = 0,j = 0; i < str1.length(); i++) {
            //当 dest.charAt(i) != dest.charAt(j) ，我们需要从 next[j-1]获取新的 j
            //直到我们发现有dest.charAt(i) == dest.charAt(j)成立才退出
            while (j> 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    /**
     * 获取一个子串的部分匹配值表
     * @param dest 子串
     * @return 部分匹配值表
     */
    public static int[] getKmpNext(String dest){
        int[] next = new int[dest.length()];//创建next数组
        next[0] = 0;//如果字符串的长度为1，部分匹配值就是0
        for (int i = 1, j =0 ; i < dest.length(); i++) {
            //当 dest.charAt(i) != dest.charAt(j) ，我们需要从 next[j-1]获取新的 j
            //直到我们发现有dest.charAt(i) == dest.charAt(j)成立才退出
            while (j> 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            //当 dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
