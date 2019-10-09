package Sort;

public class Selection {
    public static void sort(Comparable[] a){
        for(int i=0;i<a.length;i++){
            int min = i;
            for (int j = i;j<a.length;j++){
                if (!less(a[min],a[j]))
                    min = j;
            }
            exch(a,i,min);
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
        Integer[] a = {2,4,6,3,7};
        sort(a);
        show(a);
    }
}
