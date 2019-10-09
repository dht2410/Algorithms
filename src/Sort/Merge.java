package Sort;

/**
 * 自顶向下的归并递归
 * 递归地，将数组分成两半分别排序，然后将结果归并起来。
 * 分治思想，将大问题分解成小问题
 * 缺点：需要额外一个和数组a一样大小的数组aux
 */
public class Merge {
//    private static Comparable[] aux;
    public static void sort(Comparable[] a){
//        aux = new Comparable[a.length];
        Comparable[] aux = new Comparable[a.length];
        sort(a,aux,0,a.length-1);
    }
    public static void sort(Comparable[] a,Comparable[] aux, int lo, int hi){
        if (lo>=hi) return;
        int mid = (lo+hi)/2;
        sort(a,aux,lo,mid);
        sort(a,aux,mid+1,hi);
        merge(a,aux,lo,mid,hi);
    }
    public static void merge(Comparable[] a,Comparable[] aux, int lo, int mid, int hi){
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
