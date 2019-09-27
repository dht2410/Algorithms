package Stack;

import java.util.Iterator;

public class StackbyLinkedList<Item> implements Iterable<Item>{
    private Node first;
    private int N;
    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void push(Item it){
        Node oldfirst = first;
        first = new Node();
        first.item = it;
        first.next = oldfirst;
        N++;
    }
    public Item pop(){
        Item i = first.item;
        first = first.next;
        N--;
        return i;
    }
    public Item peek(){
        return first.item;
    }
    public Iterator<Item> iterator(){
        return new StackIterator();
    }
    private class StackIterator implements Iterator<Item>{
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

    public static StackbyLinkedList<String> copy(StackbyLinkedList<String> s){
        StackbyLinkedList<String> new_s = new StackbyLinkedList<>();
        StackbyLinkedList<String> new1_s = new StackbyLinkedList<>();
        for (String i : s){
            new_s.push(i);
        }
        for (String i :new_s){
            new1_s.push(i);
        }

        return new1_s;
    }
//    public void bianli(){
//        for(Node x=first;x!=null;x=x.next){
//            System.out.println(x.item);
//        }
//    }

    //空链表，first返回的是null，没有构造器默认初始值null
    public Node retfirst(){
        return first;
    }
    //test
    public static void main(String[] Args){
        StackbyLinkedList<Integer> s = new StackbyLinkedList<>();

        for (int i = 0; i<10;i++){
            s.push(i);
        }
        for (Integer i : s){
            System.out.println(i);
        }
//        Iterator<Integer> iter = s.iterator();
//        while (iter.hasNext()){
//            System.out.println(iter.next());
//        }

//        for (int i = 0; i<10;i++){
//            System.out.println(s.pop()+" "+s.size()+" "+s.isEmpty());
//        }
    }
    }
