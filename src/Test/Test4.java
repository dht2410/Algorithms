package Test;


import java.util.Arrays;
import java.util.Comparator;

public class Test4 {
    public static void main(String[] args)  {
        int[][] points = new int[][]{
                new int[]{-2147483646,-2147483645},
                new int[]{2147483646,2147483647}
        };
        Arrays.sort(points,new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if (a[0]!=b[0]){
                    return (a[0]>b[0])?1:-1;
                }
                return (a[1]>b[1])?1:-1;
            }
        });
        int end = Integer.MIN_VALUE;
        int num = 0;
        if (points[0][0]==end){
            num++;
        }
        for (int i=0;i<points.length;i++) {
            int lo = points[i][0];
            int hi = points[i][1];
            if (lo > end) {
                num++;
                end = hi;
            } else {
                end = Math.min(end, hi);
            }
        }
        System.out.println(num);
    }
}
