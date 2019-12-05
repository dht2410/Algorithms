package Heap;

public class Leetcode215_findmaxk {
    public static int findKthLargest(int[] nums, int k) {
        int N=nums.length-1;
        int[] h = heap(nums,k);
        for (int i=k;i<=N;i++){
            if (nums[i]>h[0])
                change(h,k,nums[i]);
        }
        return h[0];
    }
    public static int[] heap(int[] nums, int k){
        int[] temph = new int[k];
        for (int i =0;i<k;i++){
            temph[i]=nums[i];
        }
        for(int j = (k-1)/2;j>=0;j--){
            sink(temph,k-1,j);
        }
        return temph;
    }
    public static void change(int[] temph,int k, int n){
        temph[0]=n;
        sink(temph,k-1,0);
    }

    public static void sink(int[] nums, int N, int i){
        int j=2*i+1;
        while(j<=N){
            if(j<N&&nums[j]>nums[j+1]){
                j++;
            }
            if(nums[i]<=nums[j])
                break;
            else{
                int temp = nums[j];
                nums[j]=nums[i];
                nums[i]=temp;
                i=j;
                j=2*j+1;
            }
        }
    }
    public static void main(String[] args){
        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums,4));
    }
}
