package Test;

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

        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,1);
        System.out.println(map.get(2));

    }

}
