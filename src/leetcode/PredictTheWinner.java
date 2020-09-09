package leetcode;

/**
 * LeetCode 486 预测赢家
 * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，
 * 然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。
 * 最终获得分数总和最多的玩家获胜。
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 *
 * 方法一：递归，记录一个差值，用一个turn是1还是-1记录这轮是谁
 * 方法二：dp, dp[i][j]表示当前取数的人取完后比对方大的值
 */
public class PredictTheWinner {
    public boolean PredictTheWinner(int[] nums) {
        return compare(nums,0,nums.length-1,1)>-1;
    }
    private int compare(int[] nums, int lo, int hi, int turn){
        if (lo==hi){
            return nums[lo]*turn;
        }
        int maxLeft = nums[lo]*turn+compare(nums,lo+1,hi,-turn);
        int maxRight = nums[hi]*turn+compare(nums,lo,hi-1,-turn);
        if (turn==1){
            return Math.max(maxLeft,maxRight);
        }
        else{
            return Math.min(maxLeft,maxRight);
        }
    }

    public boolean PredictTheWinnerByDP(int[] nums){
        int N = nums.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = nums[i];
        }
        for (int l = 2;l<=N;l++){
            for (int begin=0;begin+l-1<N;begin++){
                dp[begin][begin+l-1] = Math.max(nums[begin]-dp[begin+1][begin+l-1],nums[begin+l-1]-dp[begin][begin+l-2]);
            }
        }
        return dp[0][N-1]>-1;
    }
}
