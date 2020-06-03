package leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
/**
 * 用一个HashMap<Integer,Node>来记录键值对
 * 用一个HashMap<freq,LinkedHashSet<Node>>来记录每个节点的使用频率，相同的组成一个LinkedHashSet<Node>
 */
class LFUcache {

    class Node{
        int key;
        int value;
        int freq=1;
        Node(){}
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    int capacity;
    HashMap<Integer,Node> cache;
    HashMap<Integer,LinkedHashSet<Node>> frequency;
    int min;
    int N;

    public LFUcache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        frequency = new HashMap<>();
    }

    public int get(int key) {
        if (cache.get(key)==null) {
            return -1;
        }
        Node node = cache.get(key);
        changeFreq(key);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.get(key)!=null){
            cache.get(key).value = value;
            changeFreq(key);
        }
        else if (N<capacity){
            addNode(key,value);
            N++;
        }
        else{
            removeNode();
            N--;
            addNode(key,value);
            N++;
        }
    }

    void removeNode(){
        Node removedNode = frequency.get(min).iterator().next();
        frequency.get(min).remove(removedNode);
        cache.remove(removedNode.key);
    }

    void addNode(int key, int value){
        Node newNode = new Node(key,value);
        cache.put(key,newNode);
        if (!frequency.containsKey(newNode.freq)){
            frequency.put(newNode.freq,new LinkedHashSet<Node>());
        }
        frequency.get(newNode.freq).add(newNode);
        min=1;
    }

    void changeFreq(int key){
        Node node = cache.get(key);
        int freq = node.freq;
        frequency.get(freq).remove(node);
        if (freq==min && frequency.get(freq).isEmpty()){
            min++;
        }
        int newfreq = ++node.freq;
        if (!frequency.containsKey(newfreq)){

            frequency.put(newfreq,new LinkedHashSet<Node>());
        }
        frequency.get(newfreq).add(node);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
