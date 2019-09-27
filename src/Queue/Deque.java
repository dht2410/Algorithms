package Queue;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private class Node{
        Item item;
        Node LeftNode;
        Node RightNode;
    }
    int N;
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void pushleft(Item it){
        Node newfirst = new Node();
        newfirst.item = it;
        if (first!=null) {
            newfirst.RightNode = first;
            first.LeftNode = newfirst;
            first = newfirst;
        }
        else{
            first = newfirst;
            last = newfirst;
        }
        N++;
    }
    public void pushright(Item it){
        Node newlast = new Node();
        newlast.item = it;
        if (last!=null){
            newlast.LeftNode = last;
            last.RightNode = newlast;
            last = newlast;
        }
        else{
            last = newlast;
            first = newlast;
        }
        N++;
    }
    public Item popLeft(){
        Item it = first.item;
        if (first.RightNode!=null) {
            first = first.RightNode;
            first.LeftNode = null;
        }
        else{
            first = null;
            last = null;
        }
        N--;
        return it;
    }
    public Item popRight(){
        Item it = last.item;
        if(last.LeftNode!=null) {
            last = last.LeftNode;
            last.RightNode = null;
        }
        else{
            last = null;
            first = null;
        }
        N--;
        return it;
    }

    public Iterator<Item> iterator(){
        return null;
    }

    public static void main(String[] args){
        Deque<Integer> dq = new Deque<>();
        for (int i = 0; i<10;i++){
            dq.pushright(i);
        }
        for (int i=0;i<10;i++){
            System.out.println(dq.popLeft());
        }
    }
}
