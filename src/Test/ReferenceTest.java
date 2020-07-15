package Test;

import java.util.ArrayList;
/**
 * 形参传进来的是引用的复制
 */

public class ReferenceTest {
    static void print(ArrayList al){
        al.add(2);
    }
    public static void main(String[] args){
        ArrayList al = new ArrayList();
        al.add(1);
        print(al);
        System.out.println(al.get(1));

        String s = "abc";
        String t = "abc";
        System.out.println(s==t);
    }
}
