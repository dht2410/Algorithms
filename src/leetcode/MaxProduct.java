package leetcode;

/**
 * LeetCode 152 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组
 * 动态规划
 * 维护两个数组，一个是最大值，一个是最小值
 */
public class MaxProduct {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] positiveMax = new int[len];
        int[] negativeMax = new int[len];
        positiveMax[0] = nums[0];
        negativeMax[0] = nums[0];
        int ans = nums[0];
        for (int i=1;i<nums.length;i++){
            positiveMax[i] = Math.max(positiveMax[i-1]*nums[i],Math.max(negativeMax[i-1]*nums[i],nums[i]));
            negativeMax[i] = Math.min(positiveMax[i-1]*nums[i],Math.min(negativeMax[i-1]*nums[i],nums[i]));
            ans = Math.max(ans,positiveMax[i]);
        }
        return ans;
    }
}
