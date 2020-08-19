package leetcode;

import java.util.ArrayList;

public class Trie_DictionaryTree {

    class Node{
        int[] ch = new int[26];
        int flag;
        Node(){
            flag=-1;
        }
    }

    private ArrayList<Node> tree;

    /** Initialize your data structure here. */
    public Trie_DictionaryTree() {
        tree = new ArrayList<>();
        tree.add(new Node());
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        int N = word.length();
        int add = 0;
        for (int i=0;i<N;i++){
            int index = word.charAt(i)-'a';
            if (tree.get(add).ch[index]==0){
                Node newNode = new Node();
                tree.add(newNode);
                tree.get(add).ch[index]=tree.size()-1;
            }
            add = tree.get(add).ch[index];
        }
        tree.get(add).flag=0;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        int N = word.length();
        int add = 0;
        for (int i=0;i<N;i++){
            add = getAdd(word,add,i);
            if (add<0){
                return false;
            }
        }
        return tree.get(add).flag==0;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        int N = prefix.length();
        int add = 0;
        for (int i=0;i<N;i++){
            add = getAdd(prefix,add,i);
            if (add<0){
                return false;
            }
        }
        return true;
    }

    private int getAdd(String word, int add, int i){
        int index = word.charAt(i)-'a';
        if (tree.get(add).ch[index]==0){
            return -1;
        }
        add = tree.get(add).ch[index];
        return add;
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
