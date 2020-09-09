package leetcode;

import java.util.*;

/**
 * LeetCode 347 前k个高频元素
 * 基于优先队列
 * 注意优先队列的比较方法
 * 添加元素要先poll然后再add
 */
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            map.put(num,map.getOrDefault(num,0)+1);
        }

        for (int key:map.keySet()){
            if (heap.size()<k){
                heap.add(new int[]{key,map.get(key)});
            }
            else if(map.get(key)>heap.peek()[1]){
                heap.poll();
                heap.add(new int[]{key,map.get(key)});
            }
        }

        int[] ans = new int[heap.size()];
        int index=0;
        for (int[] num:heap){
            ans[index++]=num[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,1,-1,2,-1,2,3};
        int k = 2;
        System.out.println(new TopKFrequent().topKFrequent(nums,k)[1]);
    }
}
