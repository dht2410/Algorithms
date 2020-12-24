package Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        String a = new StringBuilder("a").append("a").toString();
        System.out.println(a.intern()==a);
        String b = "aa";

        int i=3;
        System.out.println(-4^5);

    }

}
