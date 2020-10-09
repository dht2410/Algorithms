package leetcode;

/**
 * LeetCode 221 最大正方形
 * 动态规划
 * dp[i][j] 表示以（i,j）为右下角的最大正方形边长
 * dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int len = matrix.length;
        if (len==0) {
            return 0;
        }
        int wid = matrix[0].length;
        int[][] dp = new int[len][wid];
        int area = 0;
        for (int i=0;i<len;i++){
            dp[i][0] = matrix[i][0]=='1'?1:0;
            area = Math.max(dp[i][0],area);
        }
        for (int i=1;i<wid;i++){
            dp[0][i] = matrix[0][i]=='1'?1:0;
            area = Math.max(dp[0][1],area);
        }

        for (int i=1;i<len;i++){
            for (int j=1;j<wid;j++){
                if (matrix[i][j]=='0'){
                    dp[i][j]=0;
                }
                else{
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
                    area = Math.max(dp[i][j],area);
                }
            }
        }
        return area*area;
    }
}
