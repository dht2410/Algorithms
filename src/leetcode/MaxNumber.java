package leetcode;

/**
 * 两个数组按顺序挑选数字组成给定长度的数组，使其最大
 * 如果是一个数组，可利用单调栈
 * 本题为单调栈的推广，分别得到两个数组的单调栈，归并
 */
public class MaxNumber {
    int[] ans;
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        ans = new int[k];
        int n1 = nums1.length;
        int n2 = nums2.length;
        setNum(nums1,0,n1-1,nums2,0,n2-1,0,k);
        return ans;
    }
    private void setNum(int[] nums1, int lo1, int hi1, int[] nums2, int lo2, int hi2, int setPos, int k){
        int len1 = hi1-lo1+1;
        int len2 = hi2-lo2+1;
        int last = k-setPos;
        if (last<=0) return;
        int maxNum1 = -1, maxNum2 = -1;
        int index1 = -1, index2 = -1;
        for (int i=lo1;i<=hi1+len2+1-last&&i<=hi1;i++){
            if (nums1[i]>maxNum1){
                maxNum1 = nums1[i];
                index1 = i;
            }
        }
        for (int i=lo2;i<=hi2+len1+1-last&&i<=hi2;i++){
            if (nums2[i]>maxNum2){
                maxNum2 = nums2[i];
                index2 = i;
            }
        }

        if (Math.max(maxNum1,maxNum2)<ans[setPos]){
            return;
        }
        if (Math.max(maxNum1,maxNum2)>ans[setPos]){
            reset(ans,setPos+1);
        }

        if (maxNum1>maxNum2){
            ans[setPos] = maxNum1;
            setNum(nums1,index1+1,hi1,nums2,lo2,hi2,setPos+1,k);
        }
        else if (maxNum1<maxNum2){
            ans[setPos] = maxNum2;
            setNum(nums1,lo1,hi1,nums2,index2+1,hi2,setPos+1,k);
        }
        else{
            ans[setPos] = maxNum1;
            setNum(nums1,index1+1,hi1,nums2,lo2,hi2,setPos+1,k);
            setNum(nums1,lo1,hi1,nums2,index2+1,hi2,setPos+1,k);
        }

    }
    private void reset(int[] ans, int i) {
        while (i<ans.length){
            ans[i++] = 0;
        }
    }

    public int[] maxNumberWithDandiaozhan(int[] nums1, int[] nums2, int k){
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] ans = new int[k];
        int start = Math.max(0,k-n2);
        int end = Math.min(k,n1);
        for (int i=start;i<=end;i++){
            int[] array1 = maxSubArray(nums1,i);
            int[] array2 = maxSubArray(nums2,k-i);
            int[] mergeArray = merge(array1,array2);
            ans = max(ans,mergeArray);
        }
        return ans;
    }

    private int[] max(int[] A, int[] B) {
        for (int i = 0; i < A.length; i++) {
            if (A[i]>B[i]){
                return A;
            }
            else if (A[i]<B[i]){
                return B;
            }
        }
        return A;
    }

    private int[] merge(int[] array1, int[] array2) {
        int n1 = array1.length;
        int n2 = array2.length;
        int[] mergeArray = new int[n1+n2];
        int i=0, j=0, k=0;
        while (k<n1+n2){
            if (i==n1){
                mergeArray[k++] = array2[j++];
            }
            else if (j==n2){
                mergeArray[k++] = array1[i++];
            }
            else{
                if (compare(array1,i,array2,j)>0){
                    mergeArray[k++] = array1[i++];
                }
                else{
                    mergeArray[k++] = array2[j++];
                }
            }
        }
        return mergeArray;
    }

    private int compare(int[] array1, int i, int[] array2, int j) {
        int n1 = array1.length;
        int n2 = array2.length;
        while(i<n1 && j<n2){
            if (array1[i]!=array2[j]){
                return array1[i]-array2[j];
            }
            i++;
            j++;
        }
        return (n1-i)-(n2-j);
    }

    private int[] maxSubArray(int[] nums1, int n) {
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            while(top>-1 && stack[top]<num && (n-1-top)<(nums1.length-i)){
                top--;
            }
            if (top<n-1){
                stack[++top] = num;
            }
        }
        return stack;
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{4,9,5};
        int[] nums2 = new int[]{8,7,4};
        int k = 3;
        int[] ret = new MaxNumber().maxNumberWithDandiaozhan(nums1,nums2,k);
        for (int i:ret){
            System.out.println(i);
        }
    }
}
