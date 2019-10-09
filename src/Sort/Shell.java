package Sort;

/**
 * 希尔排序
 * 分成h个子数组，对于子数组进行插入排序，最后对数组进行插入排序
 * 优势：子数组规模小，最后数组是部分有序的，都适用于插入排序
 * 适用于大数组，运行时间达不到N方数量级
 */
public class Shell {
    public static void sort(Comparable[] a){
        int N=a.length;
        int h=1;
        while (h<N/3)
            h=h*3+1;
        while (h>=1){
            for(int i=h;i<N;i++){
                for(int j=i;j>=h&&less(a[j],a[j-h]);j-=h){
                    exch(a,j,j-h);
                }
            }
            h=h/3;
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
