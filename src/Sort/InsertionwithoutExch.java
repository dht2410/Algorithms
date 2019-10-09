package Sort;

/**
 * 插入排序无需交换元素版本
 * 对于一个元素，其左边数组中元素为有序的，把它放在temp里，如果前面元素比它大，则依次往后挪一位
 * 内循环结束后，将temp放在结束时的位置
 */
public class InsertionwithoutExch {
    public static void sort(Comparable[] a){
        int N=a.length;
        for (int i=1;i<N;i++){
            Comparable temp = a[i];
            int j=i;
            for(;j>0&&less(temp,a[j-1]);j--){
                a[j]=a[j-1];
            }
            a[j]=temp;
        }
    }
    private static boolean less (Comparable v, Comparable w){
        return v.compareTo(w)<0;
    }
    private static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    private static void show(Comparable[] a){
        for (int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }
    public static boolean isSorted(Comparable[] a){
        for (int i=1; i<a.length;i++){
            if (less(a[i],a[i-1]))
                return false;
        }
        return true;
    }
    public static void main(String[] args){
        Integer[] a = {};
        sort(a);
        show(a);
    }
}
