package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 按二进制中1的个数排序
 */

public class SortByBits {
    public int[] sortByBits(int[] arr) {
        int[] nums = new int[arr.length];
        for (int i=0;i<arr.length;i++){
            nums[i] = getBitsNum(arr[i]);
        }
        sort(arr,nums,0,arr.length-1);
        return arr;
    }
    private void sort(int[] arr, int[] nums, int lo, int hi){
        if (lo>=hi) return;
        int i = lo, j = hi+1;
        while (true){
            while (less(arr,nums,lo,++i)){
                i++;
                if (i>=hi) break;
            }
            while (!less(arr,nums,lo,j)){
                j--;
                if (j<=lo) break;
            }
            if (i>=j) break;
            swap(arr,nums,i++,j--);
        }
        swap(arr,nums,lo,j);
        sort(arr,nums,lo,j-1);
        sort(arr,nums,j+1,hi);
    }
    private void swap(int[] arr, int[] nums, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private boolean less(int[] arr, int[] nums, int lo, int index){
        if (nums[index]<nums[lo]) return true;
        if (nums[index]==nums[lo] && arr[index]<arr[lo]) return true;
        return false;
    }

    private int getBitsNum(int n){
        int ans = 0;
        while (n>0){
            ans+=(n%2==1)?1:0;
            n = n/2;
        }
        return ans;
    }

    private int[] anotherMethod(int[] arr){
        List<Integer> list = new ArrayList<>();
        for (int num:arr){
            list.add(num);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (getBitsNum(o1)!=getBitsNum(o2)){
                    return getBitsNum(o1)-getBitsNum(o2);
                }
                else {
                    return o1-o2;
                }
            }
        });
        for (int i=0;i<arr.length;i++){
            arr[i] = list.get(i);
        }
        return arr;
    }
}
