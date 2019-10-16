package Sort;

public class QuickwithoutRecursive {
    public static void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }
    /**
     * 将每一次划分开后的左右两个子数组的最前和最后的index压栈，弹栈两个作为lo和hi，重复压栈过程
     * 需要额外一个栈
     */
    private static void sort(Comparable[] a, int lo,int hi){
        if(lo>=hi) return;
        int top=0;
        int[] stack = new int[a.length];
        int middle;
        middle = partition(a,lo,hi);
        if (middle>lo+1){
            stack[top++]=lo;
            stack[top++]=middle-1;
        }
        if(middle<hi-1){
            stack[top++]=middle+1;
            stack[top++]=hi;
        }
        while(top>0){
            int high = stack[--top];
            int least = stack[--top];
            middle = partition(a,least,high);
            if(middle>least+1){
                stack[top++]=least;
                stack[top++]=middle+1;
            }
            if(middle<high-1){
                stack[top++]=middle+1;
                stack[top++]=high;
            }
        }
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
