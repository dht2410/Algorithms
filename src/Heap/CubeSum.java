package Heap;

public class CubeSum implements Comparable<CubeSum> {
    private int sum;
    private int i;
    private int j;
    public CubeSum(int i, int j){
        this.i=i;
        this.j=j;
        sum = i*i*i+j*j*j;
    }
    public int getSum(){
        return sum;
    }
    public int geti(){
        return i;
    }
    public int getj(){
        return j;
    }
    public int compareTo(CubeSum c){
        if (this.sum<c.getSum())
            return 1;
        return 0;
    }
    public static void heap(CubeSum[] cube, int N){
        for (int k=N/2;k>=1;k--)
            sink(cube,N,k);
    }
    public static void sink(CubeSum[] cube, int N, int k){
        while(k*2<=N){
            int j=k*2;
            if(j<N&&cube[j+1].compareTo(cube[j])==1)
                j=j+1;
            if(cube[j].compareTo(cube[k])==0)
                break;

            CubeSum cc = cube[k];
            cube[k]=cube[j];
            cube[j]=cc;

            k=j;
        }
    }
    public static CubeSum insert(CubeSum[] cube,CubeSum c, int N){
        if (c.compareTo(cube[1])==1)
            return c;
        else{
            CubeSum cc = cube[1];
            cube[1]=c;
            sink(cube,N,1);
            return cc;
        }
    }
    public static void main(String[] args){
        int N = 1000;
        CubeSum[] cube = new CubeSum[N+1];
        for(int i=1;i<=N;i++)
            cube[i]= new CubeSum(i,0);
        heap(cube,N);
        CubeSum last = new CubeSum(-1,-1);
        for(int i = 1;i<=N;i++){
            for (int j =1;j<=i;j++){
                CubeSum c=insert(cube,new CubeSum(i,j),N);
                if (c.getSum() == last.getSum()) {
                    System.out.print(c.getSum() + " " + c.geti() + " " + c.getj()+" "+ last.geti() + " " + last.getj());
                    System.out.println();
                }
                last=c;
            }
        }
        for(int k=N;k>0;k--){
            CubeSum c = cube[1];
            cube[1]=cube[N--];
            sink(cube,N,1);
            if (c.getSum() == last.getSum()) {
                System.out.print(c.getSum() + " " + c.geti() + " " + c.getj()+" "+ last.geti() + " " + last.getj());
                System.out.println();
            }
            last=c;
        }
    }
}
