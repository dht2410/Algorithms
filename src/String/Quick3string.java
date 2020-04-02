package String;
/*
 * 三项字符串快速排序，以快速排序为基础
 * */
public class Quick3string {
    private static int charAt(String a, int d){
        return (a.length()>d)?a.charAt(d):-1;
    }
    private static void exch(String[] a, int i, int j){
        String temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static void sort(String[] a){
        sort(a,0,a.length-1,0);
    }
    private static void sort(String[] a, int lo, int hi, int d){
        if(lo>=hi) return;
        int lt = lo, gt=hi;
        int v = charAt(a[lo],d);
        int i = lo+1;
        while(i<=gt){
            int w = charAt(a[i],d);
            if (w<v) exch(a,i++,lt++);
            else if (w>v) exch(a,i,gt--);
            else i++;
        }
        sort(a,lo,lt-1,d);
        if (v>=0) sort(a,lt,gt,d+1);
        sort(a,gt+1,hi,d);
    }
}
