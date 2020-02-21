package String;
/*
* 高位优先排序，递归进行
*
* */
public class MSD {
    private final static int R=256;    //字母表中字符数量
    private static String[] aux;       //额外空间
    //字符串s的第d位在字母表中的排序
    private static int charAt(String s, int d){
        int N = s.length();
        return (d<N)? s.charAt(d):-1;
    }
    public static void sort(String[] a){
        int N = a.length;
        aux = new String[N];
        sort(a,0,N-1,0);
    }
    //a[lo]到a[hi]的0到d-1位相同
    private static void sort(String[] a, int lo, int hi, int d){
        if(lo>=hi)
            return;
        int[] count = new int[R+2];  //为什么是R+2而不是R+1,因为需要一位来存储字符串长度为d的字符串数量
        for(int i=lo;i<=hi;i++)
            count[charAt(a[i],d)+2]++;
        for(int r=0;r<R+1;r++)
            count[r+1]+=count[r];
        for(int i=lo;i<=hi;i++)
            aux[count[charAt(a[i],d)+1]++]=a[i];
        for(int i=lo;i<=hi;i++)
            a[i]=aux[i-lo];
        for(int r=0;r<R;r++)
            sort(a,lo+count[r],lo+count[r+1]-1,d+1);
    }

    public static void main(String[] args){
        String[] a = {"a","ab","b","abb"};
        sort(a);
        for(String s:a)
            System.out.println(s);
    }
}
