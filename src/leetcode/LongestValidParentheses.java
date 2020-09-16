package leetcode;

/**
 * LeetCode 32 最长有效括号
 * 动态规划，dp[i]表示以第i位为结束的最长有效括号
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int ans = 0;
        int len = s.length();
        int[] dp = new int[len];
        for (int i=1;i<len;i++){
            char c1 = s.charAt(i-1);
            char c2 = s.charAt(i);
            if (c2==')'){
                if (c1=='('){
                    if (i-2>=0){
                        dp[i]=dp[i-2]+2;
                    }
                    else{
                        dp[i] = 2;
                    }
                }
                else{
                    if (i-dp[i-1]-1>=0 && s.charAt(i-dp[i-1]-1)=='('){
                        if (i-dp[i-1]-1>0){
                            dp[i] = dp[i-1]+dp[i-dp[i-1]-2]+2;
                        }
                        else{
                            dp[i] = dp[i-1]+2;
                        }
                    }
                }
                ans = Math.max(ans,dp[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "())()(())";
        System.out.println(new LongestValidParentheses().longestValidParentheses(s));
    }
}
