package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 47 全排列2
 * 没有重复数字的全排列
 * 回溯（先进行排序，如果它的前一个和它相等，并且前一个没有被标记，那么它就不能被标记）
 * if (marked[i] || i>0 && nums[i]==nums[i-1] && !marked[i-1]){
        continue;
    }
 */
public class PermuteUnique {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        boolean[] marked = new boolean[len];
        dfs(nums,new ArrayList<Integer>(),marked);
        return ans;
    }
    private void dfs(int[] nums, List<Integer> com, boolean[] marked){
        if (com.size()==nums.length){
            ans.add(new ArrayList<>(com));
            return;
        }
        for (int i=0;i<nums.length;i++){
            if (marked[i] || i>0 && nums[i]==nums[i-1] && !marked[i-1]){
                continue;
            }
            marked[i] = true;
            com.add(nums[i]);
            dfs(nums,com,marked);
            com.remove(com.size()-1);
            marked[i] = false;
        }
    }
}
