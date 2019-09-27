package Queue;

public class RingBufferbyQueue<Item> {
    private Node first;
    private Node last;
    private int N; //目前的元素个数
    private final int size; //环形队列长度
    private class Node{
        Item item;
        Node next;
    }
    //构造器不需要<Item>
    public RingBufferbyQueue (int size){
        this.size = size;
        N=0;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public boolean isFull(){
        return N==size;
    }
    public void enqueue(Item it){
        if (isFull())
            throw new RuntimeException("full!");
        else{
            Node newlast = new Node();
            newlast.item = it;
            if (!isEmpty()) {
                last.next = newlast;
                last = newlast;
            }
            else{
                last = newlast;
                first = newlast;
            }
            N++;
        }
        if(isFull())
            last.next = first;
    }
    public Item dequeue(){
        if (isEmpty())
            throw new RuntimeException("empty!");
        else{
            Item it = first.item;
            first = first.next;
            last.next = null;
            N--;
            if(isEmpty()) last=null;
            return it;
        }
    }


    public static void main(String[] args){
        RingBufferbyQueue<Integer> rb = new RingBufferbyQueue<>(3);
        for (int i =0;i<3;i++){
            rb.enqueue(i);
        }
        //System.out.println(rb.isFull());
        while (!rb.isEmpty())
            System.out.println(rb.dequeue());
    }

}
