package leetcode;

/**
 * LeetCode 33 搜索排序旋转数组
 * 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2]
 * O(logn)复杂度
 * 二分查找，一半必定是有序数组，另一半必定是无序数组
 * 如果在有序数组里，则直接二分查找
 * 如果在无序数组里，则进行递归或迭代
 */
public class SearchSortedSpinArray {
    public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length-1;
        while (lo<=hi){
            int mid = lo+(hi-lo)/2;
            int midNum = nums[mid];
            if (midNum==target) return mid;
            else if (nums[lo]<=midNum && target>=nums[lo] && target<=midNum){
                return binarySearch(nums,target,lo,mid-1);
            }
            else if (nums[hi]>=midNum && target>=midNum && target<=nums[hi]){
                return binarySearch(nums,target,mid+1,hi);
            }
            else if (nums[lo]<=midNum && (target<nums[lo] || target>midNum)){
                lo = mid+1;
            }
            else{
                hi = mid-1;
            }
        }
        return -1;
    }
    public int binarySearch(int[] nums, int target, int lo, int hi){
        if (lo>hi) return -1;
        int mid = lo+(hi-lo)/2;
        if (nums[mid]==target) return mid;
        if (nums[mid]>target) return binarySearch(nums,target,lo,mid-1);
        return binarySearch(nums,target,mid+1,hi);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(new SearchSortedSpinArray().search(nums,4));
    }
}
