package Search;

public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M;
    private Key[] keys;
    private Value[] vals;
    public LinearProbingHashST(int M){
        this.M = M;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }
    private int hash(Key key){
        return (key.hashCode()& 0x7fffffff)%M;
    }
    public void put(Key key, Value val){
        if(N>=M/2) reSize(M*2);
        int i;
        for (i = hash(key);keys[i]!=null;i=(i+1)%M){
            if(key.equals(keys[i])){vals[i]=val;return;}
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    public Value get(Key key){
        for (int i = hash(key);keys[i]!=null;i=(i+1)%M){
            if(key.equals(keys[i])){return vals[i];}
        }
        return null;
    }
    private void reSize(int cap){
        LinearProbingHashST<Key,Value> t = new LinearProbingHashST<>(cap);
        for(int i=0;i<M;i++)
            if (keys[i]!=null)
                t.put(keys[i],vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = cap;
    }
    public void delete(Key key){
        int i = hash(key);
        while(!keys[i].equals(key)){
            i=(i+1)%M;
        }
        keys[i] = null;
        vals[i] = null;
        N--;
        i = (i+1)%M;
        while(keys[i]!=null){
            Key tempKey = keys[i];
            Value tempVal = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(tempKey,tempVal);
            i=(i+1)%M;
        }
        if(N>0 && N<M/8) reSize(M/2);
    }
    public int getSize(){
        return N;
    }
    public static void main(String[] args){
        LinearProbingHashST<Integer,Integer> lp = new LinearProbingHashST<>(17);
        lp.put(1,1);
        lp.put(18,18);
        lp.put(2,2);
        lp.delete(2);
        System.out.println(lp.getSize());
    }
}
