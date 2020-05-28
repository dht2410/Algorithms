package leetcode;

import java.util.HashMap;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * LRUCache
 * HashMap+双向链表的组合
 * 注意Node的构造加入key 和 value, 加入key是为了容易删
 */

class LRUCache {

    class Node{
        int key;
        int val;
        Node pre;
        Node next;
    }

    HashMap<Integer,Node> map = new HashMap<>();
    int capacity;
    int N = 0;
    Node head = null;
    Node tail = null;

    public LRUCache(int capacity) {
        this.capacity=capacity;
    }

    public int get(int key) {
        if (map.get(key)!=null) {
            Node node = map.get(key);
            if (node.pre!=null){
                node.pre.next = node.next;
                if (node.next == null){
                    tail = node.pre;
                }
                else{
                    node.next.pre = node.pre;
                }
                node.pre = null;
                node.next = head;
                head.pre = node;
                head = node;
            }
            return map.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node!=null) {
            node.val = value;
            this.get(key);
        }
        else if (N<capacity){
            putNotEnough(key,value);
        }
        else {
            Node tempTail = tail;
            tail = tail.pre;
            if (tail==null){
                head=null;
            }
            else{
                tail.next = null;
            }
            N--;
            map.remove(tempTail.key);
            putNotEnough(key,value);
        }
    }

    private void putNotEnough(int key, int value){
        Node newNode = new Node();
        newNode.key = key;
        newNode.val = value;
        map.put(key,newNode);
        if (N==0){
            newNode.pre = null;
            newNode.next = null;
            head = newNode;
            tail = newNode;
        }
        else{
            newNode.next = head;
            head.pre = newNode;
            head = newNode;
        }
        N++;
    }

    public static void main(String[] args){
//        LRUCache lru = new LRUCache(2);
//        lru.put(1,1);
//        lru.put(2,2);
//        System.out.println(lru.get(1));
//        lru.put(3,3);
//        System.out.println(lru.get(2));
//        lru.put(4,4);
//        System.out.println(lru.get(1));
//        System.out.println(lru.get(3));
//        System.out.println(lru.get(4));
        LRUCache lru = new LRUCache(1);
        lru.put(2,1);
        System.out.println(lru.get(2));
        lru.put(3,2);
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));
    }
}


