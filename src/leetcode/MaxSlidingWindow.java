package leetcode;

import java.util.*;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k>nums.length) {
            return null;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int[] ans = new int[nums.length-k+1];
        for (int i = 0; i < nums.length; i++) {
            if (pq.size()<k){
                pq.add(nums[i]);
                if (pq.size()==k){
                    ans[i-k+1] = pq.peek();
                }
            }
            else{
                pq.remove(nums[i-k]);
                pq.add(nums[i]);
                ans[i-k+1] = pq.peek();
            }
        }
        return ans;
    }

    public int[] maxSlidingWindow2(int[] nums, int k){
        if (k>nums.length) {
            return nums;
        }
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(0);
        for (int i=1;i<k;i++){
            clear(deque,nums,i,k);
            deque.addLast(i);
        }
        int[] ans = new int[nums.length-k+1];
        ans[0] = nums[deque.getFirst()];
        for (int i=k;i<nums.length;i++){
            clear(deque,nums,i,k);
            deque.addLast(i);
            ans[i-k+1] = nums[deque.getFirst()];
        }
        return ans;
    }

    private void clear(Deque<Integer> deque, int[] nums, int i, int k) {
        int headIndex = deque.getFirst();
        if (i-headIndex==k){
            deque.removeFirst();
        }
        while (!deque.isEmpty() && nums[deque.getLast()]<=nums[i]){
            deque.removeLast();
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(new MaxSlidingWindow().maxSlidingWindow2(nums,k)));
    }
}
