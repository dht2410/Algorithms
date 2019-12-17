package Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

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

        Integer t1 = new Integer(2);
        Integer t2 = new Integer(2);

    }

}
