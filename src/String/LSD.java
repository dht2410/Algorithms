package String;
/*
* 字符串排序，低位优先
* 一般适用于字符长度相同的排序
* 先按地位排，再按倒数第二位排，最后按最高位排，类似于扑克牌排序（基数排序）
* 具有稳定性
* */
public class LSD {
    public static void sort(String[] a, int w){
        int N = a.length;
        int R = 256;
        String[] aux = new String[R+1];
        for (int d=w-1;d>=0;d--){
            int[] count = new int[R+1];
            //计算频率
            for (int i=0;i<N;i++)
                count[a[i].charAt(d)+1]++;
            //频率加和
            for (int r=0;r<R;r++)
                count[r+1]+=count[r];
            //额外数组，按频率加和累加的排序
            for (int i=0;i<N;i++)
                aux[count[a[i].charAt(d)]+1]=a[i];
            //回写
            for (int i=0;i<N;i++)
                a[i]=aux[i];
        }
    }
}
