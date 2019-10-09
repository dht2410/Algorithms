package Sort;

/**
 * 插入排序
 * 对于要排入的某个元素来说，其左边的元素已经从小到大排好，如果左边的元素比这个元素大，则交换；
 * 对于有序或接近有序的数组排序会快
 */
public class Insertion {
    public static void sort(Comparable[] a){
        for(int i=1;i<a.length;i++){
            for(int j=i;j>0&&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
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
