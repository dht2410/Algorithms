package leetcode;

import java.util.Arrays;

/**
 * Leetcode题目，判断一个字符串是否可以用它的字串重复多次构成
 * 利用KMP算法，注意fail数组的构造
 * 转化为判断s+s中能不能找到s子串
 */
public class RepeatedSubstring {
    public boolean repeatedSubstringPattern(String s) {
        int N = s.length();
        if (N<2) {
            return false;
        }
        return KMP(s+s,s);
    }
    private boolean KMP(String str, String pattern){
        int n = str.length();
        int m = pattern.length();
        int[] fail = new int[m];
        Arrays.fill(fail,-1);
        for (int i=1;i<m;i++){
            int j = fail[i-1];
            while(j!=-1 && pattern.charAt(i)!=pattern.charAt(j+1)){
                j=fail[j];
            }
            if (pattern.charAt(i)==pattern.charAt(j+1)){
                fail[i]=j+1;
            }
        }
        int match = -1;
        for (int i=1;i<n-1;i++){
            while(match!=-1 && pattern.charAt(match+1)!=str.charAt(i)){
                match = fail[match];
            }
            if (pattern.charAt(match+1)==str.charAt(i)){
                match++;
                if (match==m-1){
                    return true;
                }
            }
        }
        return false;
    }
}
