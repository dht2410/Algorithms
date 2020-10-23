package leetcode;

/**
 * LeetCode 234 回文链表
 * 判断一个链表是否为回文链表
 */
public class IsPalindromeList {
    public boolean isPalindrome(ListNode head) {
        if (head==null) {
            return true;
        }
        ListNode middle = getMiddle(head);
        ListNode secondHead = reverse(middle);

        while (head!=null && secondHead!=null){
            if (head.val!=secondHead.val){
                return false;
            }
            head = head.next;
            secondHead = secondHead.next;
        }

        return true;
    }
    private ListNode getMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr!=null){
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
