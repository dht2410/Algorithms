package Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public int a;
    int sub(){
        return --a;
    }
    public static int[] getList(){
        return new int[2];
    }
    public static void main(String[] args) {
        String a = new StringBuilder("a").append("a").toString();
        System.out.println(a.intern()==a);
        String b = "aa";
        int t = 'A';
        System.out.println(t);
        System.out.println(getList()[0]);

        PriorityQueue<Integer> heap = new PriorityQueue<>(2);

    }

}
