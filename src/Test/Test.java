package Test;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public int a;
    int sub(){
        return --a;
    }
    public static void main(String[] args) {
        String a = new StringBuilder("a").append("a").toString();
        System.out.println(a.intern()==a);
        String b = "aa";
        int t = 'A';
        System.out.println(t);
//        String b = new StringBuilder("b").append("a").toString();
//        System.out.println(b.intern()==b);
//        String c = new StringBuilder("b").append("a").toString();
//        System.out.println(c.intern()==c);

//        int t1 = 2;
//        Integer t2 = new Integer(2);
//        System.out.println(t1==t2);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(null);
        System.out.println(list.remove(1));
    }

}
