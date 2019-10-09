package List;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTest {
    public static void main(String[] args){
        LinkedList<String> list1 = new LinkedList<>();
        list1.add("a");
        list1.add("b");
        LinkedList<String> list2 = (LinkedList<String>)list1.clone();
        list1.set(0,"asd");
        //System.out.println(list2.equals(list1));
        ListIterator<String> ltr = list1.listIterator(0);
        while(ltr.hasNext())
            System.out.println(ltr.next());
    }
}
