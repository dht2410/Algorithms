package Sort;

/**
 * 自底向上的归并
 * 适用于链表数据结构
 */
public class MergeBU {
    private static Comparable[] aux;
    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz=1; sz<N;sz+=sz){
            for (int lo=0;lo<N-sz;lo+=sz+sz){
                merge(a,lo,lo+sz-1,Math.min(lo+sz*2-1,N-1));
            }
        }
    }
    public static void merge(Comparable[] a, int lo, int mid, int hi){
        int i=lo;
        int j=mid+1;
        for(int k=lo;k<=hi;k++)
            aux[k]=a[k];
        for(int k=lo;k<=hi;k++){
            if(i>mid)
                a[k]=aux[j++];
            else if (j>hi)
                a[k]=aux[i++];
            else if (less(aux[j],aux[i]))
                a[k]=aux[j++];
            else
                a[k]=aux[i++];
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
            System.out.print(a[i]);
            System.out.print(" ");
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
        Integer[] a = {6,8,7,9,5,10,45,2};
        sort(a);
        show(a);
    }
}
