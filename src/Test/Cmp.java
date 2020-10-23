package Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Cmp {
    public static void main(String[] args){
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        System.out.println(pq.getClass().getName());
        Object obj = new Object();

        int[] nums = new int[4];
        Arrays.sort(nums);
    }
}
