package Stack;

import java.util.*;

public class StackTest<items> implements Iterable<items>{
    private items[] a;
    private int N; //栈元素个数
    public StackTest(int cap){
        a = (items[]) new Object[cap];
    }
    public void push(items i){
        if (N==a.length){
            resize(a.length*2);
        }
        a[N++]= i;
    }
    public items pop(){
        items item = a[--N];
        a[N]=null;
        if(N>0 && N<=(a.length/4)){
            resize(a.length/2);
        }
        return item;
    }
    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public void resize(int max){
        items[] it = (items[])new Object[max];
        for (int i=0; i<N; i++){
            it[i]=a[i];
        }
        a=it;
    }
    public int arraysize()
    {
        return a.length;
    }

    @Override
    public Iterator<items> iterator() {
        return new RearveArrayIterator();
    }

    private class RearveArrayIterator implements Iterator<items>{
        private int i = N;

        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public items next() {
            return a[--i];
        }
    }


    //test
    public static void main(String[] Args){
        StackTest<Integer> s = new StackTest<Integer>(5);
        for (int i =0; i<12; i++){
            s.push(i);
            //System.out.println(s.arraysize()+" "+s.size());
        }
        for (Integer i : s){
            System.out.println(i);
        }
    }
}
