package leetcode;

/**
 * LeetCode 34  在排序数组中查找元素的第一个和最后一个位置
 * 二分查找，注意相等的处理
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int left = leftSearch(nums,target);
        int right = (left==-1)?-1: rightSearch(nums,target);
        return new int[]{left,right};
    }
    public int leftSearch(int[] nums, int target){
        int lo = 0;
        int hi = nums.length-1;
        while (lo<hi){
            int mid = lo+(hi-lo)/2;
            int num = nums[mid];
            if (num==target){
                hi = mid;
            }else if (num>target){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        return nums[lo]==target?lo:-1;
    }
    public int rightSearch(int[] nums, int target){
        int lo = 0;
        int hi = nums.length-1;
        while (lo<hi){
            //这里注意
            int mid = lo+(hi-lo+1)/2;
            int num = nums[mid];
            if (num==target){
                lo = mid;
            }else if (num>target){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        return nums[hi]==target?hi:-1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        System.out.println(new SearchRange().leftSearch(nums,8));
        System.out.println(new SearchRange().rightSearch(nums,8));
    }
}
