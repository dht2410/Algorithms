package leetcode;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

//class Solution {
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        int N;
//        N=ListNodetoInt(l1)+ListNodetoInt(l2);
//        return InttoListNode(N);
//    }
//    public int ListNodetoInt(ListNode l){
//        int i = 1;
//        int sum = 0;
//        for (ListNode x = l; x!=null;x=x.next){
//            sum+=x.val*i;
//            i=i*10;
//        }
//        return sum;
//    }
//
//    public ListNode InttoListNode(int N){
//        int i = N%10;
//        N=N/10;
//        ListNode l = new ListNode(i);
//        ListNode l1 = l;
//        while (N>0){
//            i = N%10;
//            N=N/10;
//            l1.next = new ListNode(i);
//            l1=l1.next;
//        }
//        return l;
//    }
//    public static void main(String[] args) {
//        ListNode l1 = new ListNode(9);
//        ListNode l11 = l1;
//        for (int i = 0; i < 9; i++) {
//            l11.next = new ListNode(9);
//            l11 = l11.next;
//        }
////        for (ListNode x=l1;x!=null;x=x.next){
////            System.out.println(x.val);
////        }
//        ListNode l2 = new ListNode(1);
//        l2.next = new ListNode(3);
//        Solution sl = new Solution();
//        ListNode l = sl.addTwoNumbers(l1, l2);
//        for (ListNode x = l; x != null; x = x.next) {
//            System.out.print(x.val);
//        }
////    }
//}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l11 = l1;
        ListNode l22 = l2;
        ListNode ltemp = new ListNode(0);
        ListNode l=ltemp;
        int result;
        int carry = 0;
        int N;
        do {
            if (l11 != null && l22 != null) {
                N = l11.val + l22.val + carry;
                result = N % 10;
                carry = N/10;
                ltemp.next = new ListNode(result);
                ltemp = ltemp.next;
                l11 = l11.next;
                l22 = l22.next;
            } else if (l11 == null && l22 != null) {
                N = l22.val + carry;
                result = N % 10;
                carry = N / 10;
                ltemp.next = new ListNode(result);
                ltemp = ltemp.next;
                l22 = l22.next;
            } else if (l22 == null && l11 != null) {
                N = l11.val + carry;
                result = N % 10;
                carry = N / 10;
                ltemp.next = new ListNode(result);
                ltemp = ltemp.next;
                l11 = l11.next;
            } else if (l22 == null && l11 == null){
                ltemp.next = new ListNode(carry);
                ltemp = ltemp.next;
                carry=0;
            }
        } while (l11 != null || l22 != null || carry == 1);
        return l.next;
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l11 = l1;
        for (int i = 0; i < 9; i++) {
            l11.next = new ListNode(9);
            l11 = l11.next;
        }
//        for (ListNode x=l1;x!=null;x=x.next){
//            System.out.println(x.val);
//        }
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(1);
        Solution sl = new Solution();
        ListNode l = sl.addTwoNumbers(l1, l2);
        for (ListNode x = l; x != null; x = x.next) {
            System.out.print(x.val);
        }

    }
}