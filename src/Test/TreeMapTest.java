package Test;

import java.util.HashMap;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args){
        TreeMap<String, String> map = new TreeMap<>();
        map.put("b","b");
        map.put("a","a");
        map.put("ab","ab");
        map.put("caacd","c");
        map.put("vdfb","ab");
        map.put("vdfb1","ab");
        map.put("vdfb2","ab");
        map.put("vdfb3","ab");
        map.put("vdfb4","ab");
        map.put("vdfb5","ab");
        map.put("vdfb6","ab");
        map.put("vdfb7","ab");
        for (String s:map.keySet()){
            System.out.println(s);
        }

        System.out.println("--------------------");
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("b","b");
        map2.put("a","a");
        map2.put("ab","ab");
        map2.put("caacd","c");
        map2.put("vdfb","ab");
        map2.put("vdfb1","ab");
        map2.put("vdfb2","ab");
        map2.put("vdfb3","ab");
        map2.put("vdfb4","ab");
        map2.put("vdfb5","ab");
        map2.put("vdfb6","ab");
        map2.put("vdfb7","ab");


        for (String s:map2.keySet()){
            System.out.println(s);
        }
    }
}
