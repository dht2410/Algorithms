package Sort;

public class KendallTau {
    private int[] a;
    private int[] b;
    KendallTau(int[] a, int[] b){
        this.a = a;
        this.b = b;
    }
    /*
     假如b[i]=n，ainv[b[i]]就是看看这个n在a[]里面排多少位，如果a[j]=n，那么ainv[a[j]]==j,即ainv[n]==j
     说明在a里面第j位是n,那么bnew的第i位存的j就是b[i]在a[]中是第j位
    */
    public int distance(){
        int N=a.length;
        int[] ainv = new int[N];
        int[] bnew = new int[N];
        for(int i=0; i<N;i++){
            ainv[a[i]]=i;
        }
        for(int i=0;i<N;i++){
            bnew[i]=ainv[b[i]];
        }
        return InsertionNum(bnew);
    }
    private int InsertionNum(int arr[]){
        int num = 0;
        for (int i=1;i<arr.length;i++){
            for(int j=i;j>0&&arr[j]<arr[j-1];j--){
                int temp = arr[j];
                arr[j]=arr[j-1];
                arr[j-1] =temp;
                num++;
            }
        }
        return num;
    }
    public static void main(String[] args){
        int[] a = { 0, 3, 1, 6, 2, 5, 4 };
        int[] b = { 1, 0, 3, 6, 4, 2, 5 };
        KendallTau ken = new KendallTau(a,b);
        System.out.println(ken.distance());
    }
}
