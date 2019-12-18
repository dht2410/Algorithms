package Sort;
/*
计数排序
适用于：1.整数排序  2.数据比较集中
时间复杂度：O(n)
 */
public class CountSort {
    private static int[] sort(int[] a){
        int N = a.length;
        int[] b = new int[N];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        //找出最大值和最小值
        for (int i=0;i<N;i++){
            if (a[i]>max) max=a[i];
            if (a[i]<min) min=a[i];
        }
        //计数
        int[] c = new int[max-min+1];
        for (int i=0;i<N;i++)
            c[a[i]-min]++;
        //累加
        for (int i=1;i<c.length;i++)
            c[i] = c[i-1]+c[i];
        //排序
        for (int i=N-1;i>=0;i--){
            b[c[a[i]-min]-1]=a[i];
            c[a[i]-min]--;
        }
        return b;
    }
    public static void main(String[] args){
        //int[] a ={1,4,3,3,6,7,7,2,9};
        int[] a ={1};
        int[] b = sort(a);
        for (int i=0;i<b.length;i++)
            System.out.println(b[i]);
    }
}
