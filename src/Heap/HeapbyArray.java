package Heap;

public class HeapbyArray<Item extends Comparable<Item>> {
    private Item[] a;
    private int M=0;
    public HeapbyArray(int N){
        a = (Item[])new Comparable[N];
    }
    public boolean isEmpty(){return M==0;}
    public int size(){return M;}
    private boolean less(int i,int j){
        return a[i].compareTo(a[j])<0;
    }
    private void exch(int i, int j){
        Item temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    //上浮
    private void swim(int k){
        while (k>1&&less(k/2,k)){
            exch(k/2,k);
            k=k/2;
        }
    }
    //和子结点中较大的交换
    private void sink(int k){
        while (2*k<=M){
            int j=2*k;
            if(j<M&&less(j,j+1)) j++;
            if(less(k,j)) exch(k,j);
            k=j;
        }
    }
    public void insert(Item item){
        a[++M]=item;
        swim(M);
    }
    public Item delMAX(){
        Item item = a[1];
        exch(1,M--);
        a[M+1]=null;
        sink(1);
        return item;
    }
    public static void main(String[] args){
        HeapbyArray<Integer> heap = new HeapbyArray<>(5);
        heap.insert(3);
        heap.insert(6);
        heap.insert(4);
        while(!heap.isEmpty()) {
            System.out.println(heap.delMAX());
        }
    }
}
