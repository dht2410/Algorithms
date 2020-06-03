package leetcode;
/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 动态规划
 * 考虑数字时：int ten = (s.charAt(i)-'0')*10   int gewei = s.charAt(i+1)-'0'
 */

public class DecodingMethod_91 {
    public int numDecodings(String s) {
        int N = s.length();
        if (s.charAt(0)=='0') return 0;
        if (N==1) return 1;
        int[] dp = new int[N];
        dp[0]=1;
        char c = s.charAt(1);
        char prec = s.charAt(0);

        if (c=='0'){
            if (prec=='1'||prec=='2') dp[1]=1;
            else return 0;
        }
        else if (c>='7') {
            if (prec=='1') dp[1]=2;
            else dp[1]=1;
        }
        else {
            if (prec=='1'||prec=='2') dp[1]=2;
            else dp[1]=1;
        }

        for (int i=2;i<N;i++){
            c = s.charAt(i);
            prec = s.charAt(i-1);
            if (c=='0'){
                if(prec=='1'||prec=='2') dp[i]=dp[i-2];
                else return 0;
            }
            else if (c>='7'){
                if(prec=='1') dp[i]=dp[i-2]+dp[i-1];
                else dp[i]=dp[i-1];
            }
            else{
                if (prec=='1'||prec=='2') dp[i]=dp[i-1]+dp[i-2];
                else dp[i]=dp[i-1];
            }
        }

        return dp[N-1];
    }
}
