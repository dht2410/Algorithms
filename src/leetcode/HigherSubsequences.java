package leetcode;

import java.util.ArrayList;
import java.util.List;

public class HigherSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int N = nums.length;
        if (N<2) {
            return list;
        }
        for (int i=N-1;i>=0;i--){
            findSubsequences(nums,list,i);
        }
        List<List<Integer>> ret = new ArrayList<>();
        for (List<Integer> series:list){
            if (series.size()>1){
                ret.add(series);
            }
        }
        return ret;
    }

    private void findSubsequences(int[] nums, List<List<Integer>> list, int i){
        int size = list.size();
        for (int i1=0;i1<size;i1++){
            List<Integer> series = list.get(i1);
            int first = series.get(0);
            if (first>=nums[i]){
                List<Integer> newList = new ArrayList<>();
                newList.add(nums[i]);
                newList.addAll(series);
                if (first>nums[i]){
                    list.add(newList);
                }
                else{
                    list.set(i1,newList);
                }
            }
        }
        List<Integer> newList = new ArrayList<>();
        newList.add(nums[i]);
        list.add(newList);

    }

    public static void main(String[] args) {
        HigherSubsequences hs = new HigherSubsequences();
        int[] nums = new int[]{7,7,7,7};
        System.out.println(hs.findSubsequences(nums));
    }
}
