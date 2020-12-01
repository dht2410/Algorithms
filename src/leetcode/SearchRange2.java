package leetcode;

public class SearchRange2 {
    public int[] searchRange(int[] nums, int target) {
        int[] firstAndLast = new int[2];
        firstAndLast[0] = findFirst(nums,target,0,nums.length-1);
        firstAndLast[1] = findLast(nums,target,0,nums.length-1);
        return firstAndLast;
    }
    private int findFirst(int[] nums, int target, int lo, int hi){
        if (lo>hi) return -1;
        int mid = lo+(hi-lo)/2;
        if (nums[mid]>target) return findFirst(nums,target,lo,mid-1);
        else if (nums[mid]<target) return findFirst(nums,target,mid+1,hi);
        else {
            if (lo==mid) return mid;
            else return findFirst(nums,target,lo,mid);
        }
    }
    private int findLast(int[] nums, int target, int lo, int hi){
        if (lo>hi) return -1;
        int mid = lo+(hi-lo)/2;
        if (nums[mid]>target) return findLast(nums,target,lo,mid-1);
        else if (nums[mid]<target) return findLast(nums,target,mid+1,hi);
        else{
            if (lo==mid) return nums[hi]==target?hi:lo;
            else return findLast(nums,target,mid,hi);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        System.out.println(new SearchRange2().searchRange(nums,6)[0]);
        System.out.println(new SearchRange2().searchRange(nums,6)[1]);
    }
}
