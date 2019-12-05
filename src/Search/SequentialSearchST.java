package Search;


import java.util.Queue;
import Queue.Deque;
public class SequentialSearchST<Key, Value> {
    private class Node{
        Key key;
        Value value;
        Node next;
        public Node(Key key, Value value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    private Node first;
    public int size(){
        int N=0;
        for(Node x = first;x!=null;x=x.next){
            N++;
        }
        return N;
    }
    public void delete(Key key){
        if (key.equals(first.key)){
            first = first.next;
        }
        else{
            Node x =first;
            while (x.next!=null){
                if(key.equals(x.next.key)){
                    x.next=x.next.next;
                    break;
                }
                x=x.next;
            }
        }
    }
    public Iterable<Key> keys(){
        Deque<Key> queue = new Deque<Key>();
        for(Node x = first;x!=null;x=x.next){
            queue.pushleft(x.key);
        }
        return queue;
    }
    public Value get(Key key){
        for(Node x=first;x!=null;x=x.next){
            if(key.equals(x.key))
                return x.value;
        }
        return null;
    }
    public void put(Key key, Value value){
        for(Node x = first;x!=null;x=x.next){
            if(key.equals(x.key))
                {x.value = value;return;}
        }
        first = new Node(key,value,first);

    }
    public static void main(String[] args){
        SequentialSearchST<String,Integer> st = new SequentialSearchST<>();

    }
}
