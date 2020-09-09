package leetcode;

import java.util.Arrays;

/**
 * Leetcode214  最短回文串
 * 在一个字符串左边加上最短的字符串，使其成为回文串
 * 转换成求最长前缀回文串
 *
 * 利用KMP算法，s为pattern，s的逆序为被匹配的，看s逆序后一个字母被匹配的index
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        int N = s.length();
        double center = getCenter(s);
        System.out.println((int)(center*2));
        int begin = (int)(center*2)+1;
        System.out.println(begin);
        StringBuilder sb = new StringBuilder(s);
        while (begin<N){
            sb.insert(0,s.charAt(begin));
            begin++;
        }
        return sb.toString();
    }
    private double getCenter(String s){
        int N = s.length();
        if (N%2==0){
            int left = N/2-1;
            if (isMatched(s,left,left+1)) return (double)(left+0.5);
            while (left>0){
                if (isMatched(s,left,left)) return left;
                if (isMatched(s,left-1,left)) return (double)(left-0.5);
                left--;
            }
            return 0;
        }
        else {
            int left = N/2;
            while (left>0){
                if (isMatched(s,left,left)) return left;
                if (isMatched(s,left-1,left)) return (double)(left-0.5);
                left--;
            }
            return 0;
        }
    }
    private boolean isMatched(String s, int left, int right){
        while (left>=0){
            if (s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left--;
            right++;
        }
        return true;
    }

    public String shortestPalindromeWithKMP(String s){
        int N = s.length();
        int[] fail = new int[N];
        Arrays.fill(fail,-1);
        for (int i=1;i<N;i++){
            int j = fail[i-1];
            while(j!=-1 && s.charAt(j+1)!=s.charAt(i)){
                j = fail[j];
            }
            if (s.charAt(j+1)==s.charAt(i)){
                fail[i]=j+1;
            }
        }

        int m = -1;
        for (int i=N-1;i>=0;i--){
            while(m!=-1 && s.charAt(i)!=s.charAt(m+1)){
                m=fail[m];
            }
            if (s.charAt(i)==s.charAt(m+1)){
                m++;
            }
        }

        if (m==N-1){
            return s;
        }
        StringBuilder sb = new StringBuilder(s.substring(m+1,N)).reverse();
        sb.append(s);
        return sb.toString();

    }
    public static void main(String[] args) {
        ShortestPalindrome sp = new ShortestPalindrome();
        String s = "a";
        System.out.println(sp.shortestPalindromeWithKMP(s));
    }
}
