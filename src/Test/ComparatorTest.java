package Test;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorTest implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1>o2) return 1;
        if (o1<o2) return -1;
        return 0;
    }
    public static void main(String[] args){
        Comparator c = new ComparatorTest();
        Integer[] a = {3,4,7,2,8,4,0};
        Arrays.sort(a,c);
        for (int i=0;i<a.length;i++) {
            System.out.println(a[i]);
        }
    }
}
