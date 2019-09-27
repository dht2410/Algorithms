package Queue;
import java.util.Iterator;


public class QueuebyLinkedList<Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int N;
    private class Node{
        Item item;
        Node next;
    }
    //method
    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public void enqueue(Item it){
        Node oldlast = last;
        last = new Node();
        last.item = it;
        last.next = null;
        if (isEmpty()) first=last;
        else oldlast.next = last;
        N++;
    }
    public Item dequeue(){
        Item it = first.item;
        first = first.next;
        if (isEmpty()) last = first;//或者last = null;
        N--;
        return it;
    }

    public Iterator<Item> iterator(){
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            Item tempitem = current.item;
            current = current.next;
            return tempitem;
        }
        //not necessary
        public void remove(){}
    }

    //test
    public static void main(String[] args){
        QueuebyLinkedList<Integer> Q = new QueuebyLinkedList<>();
        for (int i=0; i<10; i++){
            Q.enqueue(i);
        }
        //用一个栈进行队列的倒序


        for (Integer i : Q){
            System.out.println(i);
        }
    }
}
