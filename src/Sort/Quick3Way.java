package Sort;

/**
 * 取三个指针维护，初始lt=lo,i=lo+1,gt=hi
 * 	lt指向第一个等于a[lo]的元素，i指向最后一个等于a[lo]的后一个元素，不确定大小
 * 	gt指向最前一个大于a[lo]的元素的前一个元素，不确定大小
 * 	如果a[i]<a[lo]，则交换a[i]和a[lt]，i++，lt++
 * 	如果a[i]>a[lo]，则交换a[i]和a[gt]，gt--
 * 	如果a[i]=a[lo]，则i++
 * 	最后，迭代[lo,lt-1]和[gt+1,hi]区间的元素
 */
public class Quick3Way {
    public static void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a, int lo,int hi){
        if(lo>=hi) return;
        int lt=lo, i=lo+1, gt=hi;
        Comparable v =a[lt];
        while(i<=gt){
            int cmp = a[i].compareTo(v);
            if (cmp<0) exch(a,i++,lt++);
            else if (cmp>0) exch(a,i,gt--);
            else i++;
        }
        sort(a,lo,lt-1);
        sort(a,gt+1,hi);
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
        Integer[] a = {6,8,7,9,9,9,9,9,9,9,5,10,45,2};
        sort(a);
        show(a);
    }
}
