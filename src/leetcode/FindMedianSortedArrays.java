package leetcode;

/**
 * Leetcode 第4题，寻找两个有序数组的中位数
 * 等价于寻找两个有序数组中第k小的数
 * 先看两个数组中第k/2位数，较小的数及其前面的数都不可能是中位数，移动index下限，递归
 * 递归结束条件为k==1，则选择两个里面较小的值
 * 同时考虑其中一个数组到底的情况
 */
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        //第多少个，从1开始算而不是0
        int left = (n1+n2+1)/2;
        int right = (n1+n2+2)/2;

        return (getMidian(nums1,0,n1-1,nums2,0,n2-1,left)+getMidian(nums1,0,n1-1,nums2,0,n2-1,right))/2;
    }

    private double getMidian(int[] nums1, int lo1, int hi1, int[] nums2, int lo2, int hi2, int k) {
        int len1 = hi1-lo1+1;
        int len2 = hi2-lo2+1;
        if (len1>len2){
            return getMidian(nums2,lo2,hi2,nums1,lo1,hi1,k);
        }
        if (len1==0){
            return nums2[lo2+k-1];
        }
        if (k==1){
            return Math.min(nums1[lo1],nums2[lo2]);
        }

        if (len1>=k/2){
            int num1 = nums1[lo1+k/2-1];
            int num2 = nums2[lo2+k/2-1];
            if (num1<num2){
                return getMidian(nums1,lo1+k/2,hi1,nums2,lo2,hi2,k-k/2);
            }
            else{
                return getMidian(nums1,lo1,hi1,nums2,lo2+k/2,hi2,k-k/2);
            }
        }
        else{
            int num1 = nums1[hi1];
            int num2 = nums2[k-hi1+lo1+lo2-2];
            if (num1<num2){
                return num2;
            }else{
                return getMidian(nums1,lo1,hi1,nums2,lo2+(k-hi1+lo2)-1,hi2,len1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{4};
        int[] nums2 = new int[]{1,2,3,5,6};
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(nums1,nums2));
    }
}
