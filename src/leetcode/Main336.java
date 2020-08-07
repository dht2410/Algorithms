package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 基于字典树实现的回文对
 * 将每一个字符串分成两部分，查找每一部分有没有逆序的，利用字典树
 * 注意边界条件
 */
class Main336 {

    /**
     * 字典树节点
     */
    class Node{
        int[] ch = new int[26];
        int flag;
        Node(){
            flag = -1;
        }
    }

    /**
     * 字典树实现
     */
    List<Node> tree = new ArrayList<>();

    public List<List<Integer>> palindromePairs(String[] words){
        tree.add(new Node());
        List<List<Integer>> ret = new LinkedList<>();
        int N = words.length;
        for (int i=0;i<N;i++){
            insert(words[i],i);
        }
        for (int i=0;i<N;i++){
            int n = words[i].length();
            for (int j=0;j<=n;j++){
                //查看单词右半部分是否回文
                if (isHuiWen(words[i],j,n-1)){
                    int rightId = find(words[i],0,j-1);
                    if (rightId!=-1 && rightId!=i){
                        ret.add(Arrays.asList(i,rightId));
                    }
                }
                //查看单词左半部分是否回文，注意j!=0为了防止重复
                if (j!=0 && isHuiWen(words[i],0,j-1)){
                    int leftId = find(words[i],j,n-1);
                    if (leftId!=-1 && leftId!=i){
                        ret.add(Arrays.asList(leftId,i));
                    }
                }
            }
        }
        return ret;
    }

    private int find(String word, int left, int right) {
        int pos = 0;
        for (int i=right;i>=left;i--){
            int index = word.charAt(i)-'a';
            pos = tree.get(pos).ch[index];
            if (pos==0){
                return -1;
            }
        }
        return tree.get(pos).flag;
    }

    public boolean isHuiWen(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }

    private void insert(String word, int i) {
        int n = word.length();
        int add=0;
        for (int j=0;j<n;j++){
            char c = word.charAt(j);
            int in = c-'a';
            if (tree.get(add).ch[in]==0){
                tree.add(new Node());
                tree.get(add).ch[in]=tree.size()-1;
            }
            add = tree.get(add).ch[in];
        }
        tree.get(add).flag = i;
    }

    public static void main(String[] args) {
        Main336 main336 = new Main336();
        String[] words = new String[]{"a",""};
        System.out.println(main336.palindromePairs(words).size());
        for (List<Integer> myList : main336.palindromePairs(words)){
            System.out.println();
            for (int i : myList){
                System.out.print(i);
                System.out.print(" ");
            }
        }
    }

}