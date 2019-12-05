package Search;

public class SeparateChainingHashST<Key,Value> {
    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;
    public SeparateChainingHashST(){
        this(997);
    }
    public SeparateChainingHashST(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for(int i=0;i<M;i++)
            st[i]=new SequentialSearchST<>();
    }
    private int hash(Key key){
        int k = key.hashCode()&0x7fffffff;
        return k%M;
    }
    public void put(Key key, Value val){
        int k = hash(key);
        int s = st[k].size();
        st[k].put(key,val);
        if (st[k].size()>s)
            N++;
    }
    public Value get(Key key){
        int k = hash(key);
        return st[k].get(key);
    }
    public void delete(Key key){
        st[hash(key)].delete(key);
        N--;
    }
    public int getSize(){
        return N;
    }
    public static void main(String[] args){
        SeparateChainingHashST<Integer,Integer> sc = new SeparateChainingHashST<>();
        sc.put(1,1);
        sc.put(1,998);
        System.out.println(sc.getSize());
        sc.delete(1);
        System.out.println(sc.get(1));
        System.out.println(sc.getSize());
    }
}
