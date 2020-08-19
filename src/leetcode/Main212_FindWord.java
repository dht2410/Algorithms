package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个字符串集合和一个二维char数组，在char数组中看有没有连续合起来的字符串在字符串集合中
 * 对字符串集合做一个单词查找树
 * 对char数组中每一个字母为首进行dfs，看有没有存在于单词查找树中
 */
public class Main212_FindWord {
    class Node{
        int[] ch = new int[26];
        boolean isEnd;
        Node(){
            isEnd = false;
        }
    }

    ArrayList<Node> wordTree = new ArrayList<>();

    private void buildTree(String[] words){
        wordTree.add(new Node());
        for (String word:words){
            insert(word);
        }
    }

    private void insert(String word) {
        int N = word.length();
        int add = 0;
        for (int i=0;i<N;i++){
            int index = word.charAt(i)-'a';
            if (wordTree.get(add).ch[index]==0){
                Node newNode = new Node();
                wordTree.add(newNode);
                wordTree.get(add).ch[index]=wordTree.size()-1;
            }
            add = wordTree.get(add).ch[index];
        }
        wordTree.get(add).isEnd = true;
    }

    public List<String> findWords(char[][] board, String[] words) {
        buildTree(words);
        List<String> ret = new ArrayList<>();
        boolean[][] isReached = new boolean[board.length][board[0].length];
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                dfs(ret,isReached,board,sb,i,j,0);
            }
        }
        return ret;
    }

    private void dfs(List<String> ret, boolean[][] isReached, char[][] board, StringBuilder sb, int i, int j, int add) {
        if (i<0 || j<0 || i==board.length || j==board[0].length || isReached[i][j]){
            return;
        }
        isReached[i][j]=true;
        int c = board[i][j]-'a';
        sb.append(board[i][j]);

        if (wordTree.get(add).ch[c]==0){
            sb.deleteCharAt(sb.length()-1);
            isReached[i][j]=false;
            return;
        }
        add = wordTree.get(add).ch[c];

        if (wordTree.get(add).isEnd){
            ret.add(sb.toString());
            wordTree.get(add).isEnd=false;
        }

        for (int u=-1;u<=1;u+=2){
                dfs(ret,isReached,board,sb,i+u,j,add);
                dfs(ret,isReached,board,sb,i,j+u,add);
        }
        isReached[i][j]=false;
        sb.deleteCharAt(sb.length()-1);
    }
}
