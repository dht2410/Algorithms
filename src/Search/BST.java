package Search;


import Queue.Deque;

import java.awt.geom.QuadCurve2D;
import java.util.Stack;

public class BST<Key extends Comparable<Key>,Value> {
    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
    private Node root;
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if (x==null) return 0;
        else
            return x.N;
    }
    public Value get(Key key){
        return get(root, key);
    }
    private Value get(Node x, Key key){
        if (x==null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp<0) return get(x.left,key);
        else if (cmp>0) return get(x.right,key);
        else return x.val;
    }
    public void put(Key key, Value val){
        root = put(root,key,val);
    }
    private Node put(Node x,Key key, Value val){
        if (x==null) return new Node(key,val,1);
        int cmp = key.compareTo(x.key);
        if (cmp<0) x.left = put(x.left,key,val);
        else if(cmp>0) x.right = put(x.right,key,val);
        else x.val = val;
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node x){
        if (x.left==null) return x;
        return min(x.left);
    }
    public Key floor(Key key){
        Node x = floor(root,key);
        if (x==null) return null;
        return x.key;
    }
    private Node floor(Node x, Key key){
        if (x==null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp==0) return x;
        if (cmp<0) return floor(x.left,key);
        Node t =floor(x.right,key);
        if(t!=null) return t;
        else return x;
    }
    public Key select(int k){
        return select(root,k).key;
    }
    private Node select(Node x, int k){
        if (x==null) return null;
        int N = size(x.left);
        if (N>k) return select(x.left,k);
        else if(N<k) return select(x.right,k-N-1);
        else return x;
    }
    public int rank(Key key){
        return rank(root,key);
    }
    private int rank(Node x, Key key){
        if (x==null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp<0) return rank(x.left,key);
        else if (cmp>0) return size(x.left)+1+rank(x.right,key);
        else return size(x.left);
    }
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        if(x.left==null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }
    //deleteMax()相似
    public void delete(Key key){
        root = delete(root,key);
    }
    private Node delete(Node x, Key key){
        if (x==null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp<0) x.left = delete(x.left,key);
        else if(cmp>0) x.right = delete(x.right,key);
        else{
            if(x.left ==null) return x.right;
            if(x.right==null) return x.left;
            Node t = x;
            x = min(x.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }

    //中序遍历,从左到右，从小到大,和keys方法相关
    private void print(Node x){
        if(x==null) return;
        print(x.left);
        System.out.println(x.key);
        print(x.right);
    }
    public Iterable<Key> keys(){
        return keys(min(),min());
    }
    public Iterable<Key> keys(Key lo, Key hi){
        Deque<Key> deque = new Deque<>();
        keys(root,deque,lo,hi);
        return deque;
    }
    private void keys(Node x,Deque deque,Key lo, Key hi){
        if(x==null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo<0) keys(x.left,deque,lo,hi);
        if (cmplo<=0&&cmphi>=0) deque.pushleft(x.key);
        if (cmphi>0) keys(x.right,deque,lo,hi);
    }
    public int height(){
        return height(root);
    }
    private int height(Node x){
        if(x==null) return -1;
        return 1+((height(x.left)>height(x.right))? height(x.left):height(x.right));
    }
    public int avgCompares(){
        return avgCompares(root,0);
    }
    private int avgCompares(Node x, int k){
        if(x==null) return 0;
        return k+avgCompares(x.left,k+1)+avgCompares(x.right,k+1);
    }
    public Value getWithoutRecursive(Key key){
        Node x = root;
        while(x!=null){
            int cmp = key.compareTo(x.key);
            if(cmp<0) x=x.left;
            else if(cmp>0) x=x.right;
            else return x.val;
        }
        return null;
    }
    public void putWithoutRecursive(Key key, Value val){
        Node x = new Node(key,val,1);
        if(root==null){
            root = x;
            return;
        }
        Node parent = null, r=root;
        while(r!=null){
            parent=r;
            int cmp = key.compareTo(r.key);
            if (cmp<0) r=r.left;
            else if(cmp>0) r=r.right;
            else {
                r.val=val;
                return;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp<0) parent.left=x;
        else parent.right=x;
        addSize(root,key);
    }
    private void addSize(Node x, Key key){
        while(x!=null){
            int cmp = key.compareTo(x.key);
            if (cmp<0) {x.N++;x=x.left;}
            else if(cmp>0) {x.N++;x=x.right;}
            else {x.N++;return;}
        }
    }
    public Value minWithoutRecursive(){
        Node x = root;
        if (x==null) return null;
        while(x.left!=null){
            x=x.left;
        }
        return x.val;
    }
    public Value floorWithoutRecursive(Key key){
        Node x = root;
        Value v = null;
        while(x!=null){
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if(cmp<0){
                x = x.left;
                if(x==null) break;
            }
            else {
                v = x.val;
                x = x.right;
                if (x==null) break;
            }
        }
        return v;
    }
    //用一个栈来实现中序遍历
    public Iterable<Key> keysWithoutRecursive(){
        Stack<Node> stack = new Stack<>();
        Deque<Key> deque = new Deque<>();
        Node x = root;
        while(x!=null || !stack.isEmpty()){
            if (x!=null){
                stack.push(x);
                x=x.left;
            }
            else{
                x = stack.pop();
                deque.pushleft(x.key);
                x = x.right;
            }
        }
        return deque;
    }
    //层序遍历，用一个队列存储
    public void printLevel(){
        Deque<Node> deque = new Deque<>();
        Node x = root;
        if (x==null) return;
        deque.pushleft(x);
        while(!deque.isEmpty()){
            Node t = deque.popRight();
            if (t.left!=null) deque.pushleft(t.left);
            if (t.right!=null) deque.pushleft(t.right);
            System.out.println(t.key);
        }
    }
}
