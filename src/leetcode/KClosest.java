package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosest {
    public int[][] kClosest(int[][] points, int K){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (getDis(a)-getDis(b));
            }
        });
        for (int i = 0; i < points.length; i++) {
            if (pq.size()<K){
                pq.add(points[i]);
            }
            else{
                int dis = getDis(points[i]);
                if (dis<getDis(pq.peek())){
                    pq.remove();
                    pq.add(points[i]);
                }
            }
        }
        int[][] ans = new int[pq.size()][2];
        int index = 0;
        for (int[] ints : pq) {
            ans[index++] = ints;
        }
        return ans;
    }
    private int getDis(int[] a){
        return a[0]*a[0]+a[1]*a[1];
    }
}
