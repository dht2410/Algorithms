package leetcode;
/*
给出二叉搜索树的根节点，该二叉树的节点值各不相同，
修改二叉树，使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

思路：右中左的遍历顺序，用一个全局变量sum记录当前和值
 */
public class BiggerTreeFromBST {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    public TreeNode bstToGst(TreeNode root) {
        sumNode(root);
        return root;
    }
    int sum = 0;
    private void sumNode(TreeNode x){
        if(x==null) return;
        sumNode(x.right);
        sum += x.val;
        x.val = sum;
        sumNode(x.left);
    }

    //另一种不用全局变量的递归，传进去参数parentSum
    private int updateTree(TreeNode tree, int parentSum) {
        if (tree == null) {
            return parentSum;
        }
        int right = updateTree(tree.right, parentSum);
        tree.val += right;
        return updateTree(tree.left, tree.val);
    }
}
