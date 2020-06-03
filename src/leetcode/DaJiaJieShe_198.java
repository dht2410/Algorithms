package leetcode;

/**
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 动态规划
 */
public class DaJiaJieShe_198 {
    public int rob(int[] nums) {
        int N = nums.length;
        if (N==0) return 0;
        if (N==1) return nums[0];
        int[] dp = new int[N];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i=2;i<N;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[N-1];
    }
}
