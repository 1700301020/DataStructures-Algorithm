package Algorithm.KMP;

/**
 * 暴力匹配
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";

        int i = violenceMatch(str1, str2);
        System.out.println(i);
    }

    //暴力匹配
    public static int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int i = 0;//指向s1
        int j = 0;//指向s2

        while (i < s1.length && j < s2.length){//保证不越界
            if (s1[i] == s2[j]){//匹配成功
                i++;
                j++;
            }else {//匹配失败
                i = i-(j-1);
                j=0;
            }
        }

        //判断是否匹配成功
        if (j == s2.length){
            return i-j;
        }else {
            return -1;
        }
    }
}
