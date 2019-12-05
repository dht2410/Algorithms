package Search;
//平衡二叉树
public class AVL <Key extends Comparable<Key>,Value> {
    private Node root;
    private class Node{
        Key key;
        Value val;
        int height;
        Node left, right;
        public Node(Key key, Value val, int height){
            this.key = key;
            this.val = val;
            this.height = height;
        }
    }
    private int getHeight(Node x){
        return (x==null)? -1:x.height;
    }
    private Node rotateRight(Node x){
        Node T= x.left;
        x.left = T.right;
        T.right = x;
        x.height = Math.max(getHeight(x.left),getHeight(x.right))+1;
        T.height = Math.max(getHeight(x.left),getHeight(x.right))+1;
        return T;
    }
    private Node rotateLeft(Node x){
        Node T = x.right;
        x.right = T.left;
        T.left = x;
        x.height = Math.max(getHeight(x.left),getHeight(x.right))+1;
        T.height = Math.max(getHeight(x.left),getHeight(x.right))+1;
        return T;
    }
    private Node rotateLeftRight(Node x){
        x.left = rotateLeft(x.left);
        return rotateRight(x);
    }
    private Node rotateRightLeft(Node x){
        x.right = rotateRight(x.right);
        return rotateLeft(x);
    }
    public void put(Key key, Value val){
        root = put(root,key,val);
    }
    private Node put(Node x, Key key, Value val){
        if (x==null) return new Node(key,val,0);
        int cmp = key.compareTo(x.key);
        if (cmp<0) {
            x.left = put(x.left,key,val);
            if(getHeight(x.left)-getHeight(x.right)==2){
                if(key.compareTo(x.left.key)<0)
                    x = rotateRight(x);
                else
                    x = rotateLeftRight(x);
            }
        }
        else if(cmp>0){
            x.right = put(x.right,key,val);
            if(getHeight(x.right)-getHeight(x.left)==2){
                if (key.compareTo(x.right.key)>0)
                    x = rotateLeft(x);
                else
                    x = rotateRightLeft(x);
            }
        }
        else x.val = val;
        x.height = Math.max(getHeight(x.left),getHeight(x.right))+1;
        return x;
    }
    public static void main(String[] args){
        AVL<Integer,Integer> avl = new AVL<>();
        avl.put(3,3);
        avl.put(1,1);
        avl.put(2,2);
        System.out.println("hahaha");
    }
}
