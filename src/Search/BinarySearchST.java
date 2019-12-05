package Search;

public class BinarySearchST<Key extends Comparable<Key>,Value>
{
    private Key[] keys;
    private Value[] vals;
    private int N=0;
    public BinarySearchST(int capacity)
    {
        keys = (Key[])new Comparable[capacity];
        vals = (Value[])new Object[capacity];
    }
    public int size()
    {
        return N;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public Value get(Key key){
        if (isEmpty()) return null;
        int i = rank(key);
        if(i<N&&key.compareTo(keys[i])==0)
            return vals[i];
        return null;
    }
    public void set(Key key, Value value){
        int i = rank(key);
        if (i<N&&key.compareTo(keys[i])==0){
            vals[i]=value;return;
        }
        for(int j=N;j>i;j--){
            keys[j]=keys[j-1];
            vals[j]=vals[j-1];
        }
        keys[i]=key;
        vals[i]=value;
        N++;
    }
    public int rank(Key key){
        int lo = 0;
        int hi = N-1;
        while(lo<=hi){
            int mid = (lo+hi)/2;
            if(key.compareTo(keys[mid])>0){
                lo = mid+1;
            }
            else if(key.compareTo(keys[mid])<0){
                hi=mid-1;
            }
            else
                return mid;
        }
        return lo;
    }
    public void delete(Key key){
        int i = rank(key);
        if(i<N&&key.compareTo(keys[i])==0) {
            for (int j = i; j < N-1; j++) {
                keys[j] = keys[j+1];
                vals[j] = vals[j+1];
            }
            N--;
        }
    }
    public Key floor(Key key){
        int i = rank(key);
        if(i<N&&key.compareTo(keys[i])==0)
            return keys[i];
        else if (i==0) return null;
        else
            return keys[i-1];
    }
}
