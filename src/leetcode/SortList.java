package leetcode;

public class SortList {
    public ListNode sortList(ListNode head) {
        if(head.next==null)
            return head;
        ListNode mid = findMid(head);
        ListNode head2 = mid.next;
        mid.next = null;
        sortList(head);
        sortList(head2);
        return Merge(head, head2);
    }
    public ListNode Merge(ListNode head1,ListNode head2){
        ListNode dim = new ListNode(0);
        ListNode curr = dim;
        while(head1!=null && head2!=null){
            if(head1.val<head2.val){
                curr.next = new ListNode(head1.val);
                head1=head1.next;
                curr=curr.next;
            }
            else{
                curr.next = new ListNode(head2.val);
                head2=head2.next;
                curr=curr.next;
            }
        }
        curr.next = (head1==null)? head2: head1;
        return dim.next;
    }
    public ListNode findMid(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast==null)
                break;
        }
        return slow;
    }
    public static void main(String[] args){

    }
}
