package leetcode;

import java.util.ArrayList;

/**
 * 有序链表转平衡二叉树
 * 找到中点，然后左右分治
 */
public class SortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        if (head==null){
            return null;
        }
        ArrayList<Integer> array = new ArrayList<>();
        for (ListNode x = head; x!=null; x=head.next){
            array.add(x.val);
        }
        int length = array.size();

        return getBST(array, 0, length-1);
    }

    private TreeNode getBST(ArrayList<Integer> array, int lo, int hi){
        if (hi < lo){
            return null;
        }
        if (lo == hi){
            return new TreeNode(array.get(lo));
        }
        int mid = (lo+hi)/2;
        TreeNode newNode = new TreeNode(array.get(mid));
        newNode.left = getBST(array,lo,mid-1);
        newNode.right = getBST(array,mid+1,hi);
        return newNode;
    }
}


class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }