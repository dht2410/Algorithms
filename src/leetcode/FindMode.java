package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindMode {
    List<Integer> ansList = new ArrayList<>();
    int base,count,maxCount;
    public int[] findMode(TreeNode root) {
        TreeNode cur = root, pre = null;
        while (cur!=null){
            if (cur.left==null){
                update(cur.val);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while (pre.right!=null && pre.right!=cur){
                pre = pre.right;
            }
            if (pre.right==null){
                pre.right = cur;
                cur = cur.left;
            }
            else{
                pre.right = null;
                update(cur.val);
                cur = cur.right;
            }
        }
        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    private void update(int val) {
        if (val == base){
            count++;
        }
        else {
            count = 1;
            base = val;
        }
        if (count==maxCount){
            ansList.add(val);
        }
        else if (count>maxCount){
            maxCount = count;
            ansList.clear();
            ansList.add(val);
        }
    }

    public static void main(String[] args) {

    }
}
