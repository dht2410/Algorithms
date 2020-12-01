package leetcode;

public class FindTargetSumWays {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = sumNums(nums)+S;
        if (sum%2==1) return 0;
        int target = sum/2;
//        int[][] dp = new int[nums.length][target+1];
//        dp[0][0] = 1;
//        for (int i=1;i<=target;i++){
//            dp[0][i] = (nums[0]==i)?1:0;
//        }
//
//        for (int i=1;i<nums.length;i++){
//            for (int j=0;j<=target;j++){
//                if (nums[i]==0){
//                    dp[i][j] = 2*dp[i-1][j];
//                }
//                else if (j-nums[i]<0){
//                    dp[i][j] = dp[i-1][j];
//                }
//                else{
//                    dp[i][j] = dp[i-1][j]+dp[i-1][j-nums[i]];
//                }
//            }
//        }
//        return dp[nums.length-1][target];
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i=1;i<=target;i++){
            dp[i] = (nums[0]==i)?1:0;
        }
        for (int i=1;i<nums.length;i++){
            for (int j=target;j>=0;j--){
                if (nums[i]==0){
                    dp[j]*=2;
                }
                else if (j-nums[i]>=0){
                    dp[j]+=dp[j-nums[i]];
                }
            }
        }
        return dp[target];

    }
    private int sumNums(int[] nums){
        int sum = 0;
        for(int num:nums){
            sum+=num;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,1};
        int S = 3;
        System.out.println(new FindTargetSumWays().findTargetSumWays(nums,S));
    }
}
