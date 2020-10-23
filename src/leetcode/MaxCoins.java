package leetcode;

/**
 * LeetCode 312 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。
 * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。
 * 注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 解答：动态规划
 * 反向思考，把数字从小到大摆到里面
 * dp[i][j]表示开区间（i,j）最大的，最后求dp[0][n+1]
 * for (int mid = i+1;mid<=j-1;mid++){
        dp[i][j] = Math.max(dp[i][j],newNums[i]*newNums[mid]*newNums[j]+dp[i][mid]+dp[mid][j]);
   }
 */
public class MaxCoins {
    public int maxCoins(int[] nums){
        int n = nums.length;
        int[] newNums = new int[n+2];
        for (int i=0;i<n;i++){
            newNums[i+1] = nums[i];
        }
        newNums[0] = newNums[n+1] = 1;
        int[][] dp = new int[n+2][n+2];
        for (int l=2;l<=n+1;l++){
            for (int i=0;i<=n;i++){
                int j = i+l;
                if (j>n+1){
                    break;
                }
                for (int mid = i+1;mid<=j-1;mid++){
                    dp[i][j] = Math.max(dp[i][j],newNums[i]*newNums[mid]*newNums[j]+dp[i][mid]+dp[mid][j]);
                }
            }
        }
        return dp[0][n+1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,1,5,8};
        System.out.println(new MaxCoins().maxCoins(nums));
    }
}
