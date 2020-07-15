package leetcode;

import java.util.Arrays;

public class PartitionKSubsets_698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k==1) return true;
        int sum=0;
        for (int num:nums) sum+=num;
        if (sum%k>0) return false;
        int target = sum/k;

        Arrays.sort(nums);
        int N = nums.length;
        if (nums[N-1]>target) return false;
        return search(new int[k], nums, k, N-1, target);
    }
    private boolean search(int[] buckets, int[] nums, int k, int index, int target){
        if (index<0) return true;
        int thisNum = nums[index--];
        for (int i=0;i<k;i++){
            if (buckets[i]+thisNum<=target){
                buckets[i]+=thisNum;
                if (search(buckets,nums,k,index,target)) return true;
                buckets[i]-=thisNum;
            }
        }
        return false;
    }

}
