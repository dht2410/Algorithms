package Sort;

/**
 * 除了记录数组的最前和最后索引的lo和hi,以及记录左右扫描位置的索引i和j
 * 加入索引p和q，用于代表左边与a[lo]相等的元素子数组中最右边那个位置，
 * 和代表右边与a[lo]相等的元素子数组中最左边那个位置
 * 算法过程只在快速排序的基础上增加一步：a[i]和a[j]交换后，判断a[i]和a[j]与a[lo]是否相等
 * 若a[i]==a[lo]，将其与a[++p]交换位置
 * 若a[j]==a[lo]，将其与a[--q]交换位置
 * 跳出循环后，i指向大于a[lo]数组的第一个，j指向小于a[lo]数组的最后一个
 * 最后将两边等于a[lo]数组交换到中间，此时j指向左边即将迭代数组最后一个，i指向右边迭代子数组的第一个
 */
public class Quick3Way2 {
    public static void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }
    private static void sort(Comparable[] a, int lo,int hi){
        if(lo>=hi) return;
        Comparable v = a[lo];
        int i = lo, j=hi+1;
        int p = lo, q=hi+1;
        while (true){
            while(less(a[++i],v)){
                if(i==hi) break;
            }
            while(less(v,a[--j])){
                if(j==lo) break;
            }

            //结束循环时，i==j，或者j=i-1
            if(i==j&&eq(a[i],v))
                exch(a,i,++p);
            if(i>=j) break;

            exch(a,i,j);
            if(eq(a[i],v)) exch(a,i,++p);
            if(eq(a[j],v)) exch(a,j,--q);
        }
        i=j+1;
        for(int k = lo;k<=p;k++){
            exch(a,k,j--);
        }
        for(int k = hi;k>=q;k--){
            exch(a,k,i++);
        }
        sort(a,lo,j);
        sort(a,i,hi);
    }
    private static boolean eq(Comparable v,Comparable w){
        return v.compareTo(w)==0;
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
        Integer[] a = {6,8,7,9,9,9,9,9,9,5,10,45,2};
        sort(a);
        show(a);
    }
}
