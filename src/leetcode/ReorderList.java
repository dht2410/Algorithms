package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class ReorderList {
    public void reorderList(ListNode head) {
        Deque<ListNode> deque = new LinkedList<>();
        ListNode curr = head;
        while (curr!=null){
            deque.addLast(curr);
            curr = curr.next;
        }
        boolean from = true;
        ListNode dummy = new ListNode(-1);
        head = dummy.next;
        while (!deque.isEmpty()){
            if (from){
                dummy.next = deque.pollFirst();
                dummy = dummy.next;
                from = false;
            }else{
                dummy.next = deque.pollLast();
                dummy = dummy.next;
                from = true;
            }
        }
        dummy.next = null;
    }
}
