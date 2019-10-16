package Sort;

public class Quick {
    public static void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a, int lo,int hi){
        if(lo>=hi) return;
        int middle;
        middle = partition(a,lo,hi);
        sort(a,lo,middle-1);
        sort(a,middle+1,hi);
    }
    private static int partition(Comparable[] a, int lo, int hi){
        Comparable v = a[lo];
        int i = lo, j=hi+1;
        while(true){
            while(less(a[++i],v)){
                if(i==hi)
                    break;
            }
            while(less(v,a[--j])){
                if(j==lo)
                    break;
            }
            if(i>=j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
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
