package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            if(i==0 || nums[i]!=nums[i-1]){
                search(ans,nums,-nums[i],i+1,nums.length-1);
            }
        }
        return ans;
    }
    private void search(List<List<Integer>> ans, int[] nums, int target, int lo, int hi){
        while(lo<hi){
            int sum = nums[lo]+nums[hi];
            if (sum<target){
                while(lo+1<nums.length && nums[lo]==nums[lo+1]){
                    lo++;
                }
                lo++;
            }
            else if (sum>target){
                while(hi-1>-1 && nums[hi]==nums[hi-1]){
                    hi--;
                }
                hi--;
            }
            else{
                List<Integer> combination = new ArrayList<>();
                combination.add(-target);
                combination.add(nums[lo]);
                combination.add(nums[hi]);
                ans.add(combination);
                while(lo+1<nums.length && nums[lo]==nums[lo+1]){
                    lo++;
                }
                lo++;
                while(hi-1>-1 && nums[hi]==nums[hi-1]){
                    hi--;
                }
                hi--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,4};
        System.out.println(new ThreeSum().threeSum(nums));
    }
}
