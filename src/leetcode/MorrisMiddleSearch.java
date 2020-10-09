package leetcode;

/**
 * 空间复杂度为O(1)的中序遍历
 * 找到节点的前驱节点，将该节点的右指针指向本节点
 */
public class MorrisMiddleSearch {
    public void middleSearch(TreeNode root){
        TreeNode cur = root, pre = null;
        while(cur!=null){
            if (cur.left==null){
                search(cur);
                cur = cur.right;
                continue;
            }
            pre = cur.left;
            while(pre.right!=null && pre.right!=cur){
                pre = pre.right;
            }
            if (pre.right==null){
                pre.right = cur;
                cur = cur.left;
            }
            else{
                pre.right = null;
                search(cur);
                cur = cur.right;
            }
        }
    }

    private void search(TreeNode cur) {
    }
}
