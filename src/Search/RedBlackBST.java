package Search;

import com.sun.org.apache.regexp.internal.RE;

public class RedBlackBST<Key extends Comparable<Key>,Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        Key key;           //键
        Value val;         //值
        Node left, right;  //左右子树
        int N;             //这棵子树中的结点总数
        boolean color;     //由其父节点指向它的链接的颜色
        public Node(Key key, Value val, int N, boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }
    private Node root;
    private boolean isRed(Node x){
        if (x==null) return false;
        return x.color== RED;
    }
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if (x==null) return 0;
        else
            return x.N;
    }
    private Node rotateLeft(Node h)
    {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1+size(h.left)+size(h.right);
        return x;
    }
    private Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1+size(h.left)+size(h.right);
        return x;
    }
    private void flipColors(Node h){
        h.color = BLACK;
        h.left.color = RED;
        h.right.color = RED;
    }
    public void put(Key key, Value val)
    {
        put(root,key,val);
        root.color=BLACK;
    }
    //这个实现真的牛逼
    private Node put(Node x, Key key, Value val){
        if (x==null)
            return new Node(key,val,1,RED);
        int cmp = key.compareTo(x.key);
        if (cmp<0) x.left = put(x.left,key,val);
        else if (cmp>0) x.right = put(x.right,key,val);
        else x.val = val;

        if(isRed(x.right)&&!isRed(x.left)) x = rotateLeft(x);
        if(isRed(x.left)&&isRed(x.left.left)) x = rotateRight(x);
        if(isRed(x.left)&&isRed(x.right)) flipColors(x);

        x.N = 1+size(x.left)+size(x.right);
        return x;
    }

}
