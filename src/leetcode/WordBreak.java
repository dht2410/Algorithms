package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 139 单词拆分
 * 动态规划 + 单词树
 * dp[i]代表从0到i是否可以被拆分
 * dp[i] = dp[j] + contains(s,j+1,i)
 */
public class WordBreak {
    class Node{
        int[] chars = new int[26];
        boolean flag = false;
    }
    private List<Node> wordsTree = new ArrayList<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        buildTree(wordDict);
        boolean[] dp = new boolean[s.length()];
        dp[0] = contains(s,0,0);
        for (int i = 1; i < dp.length; i++) {
            for (int j = i-1;j>=0;j--){
                if (dp[j] && contains(s,j+1,i)){
                    dp[i] = true;
                    break;
                }
            }
            if (contains(s,0,i)){
                dp[i] = true;
            }
        }
        return dp[s.length()-1];
    }
    private void buildTree(List<String> wordDict){
        wordsTree.add(new Node());
        for (String word:wordDict){
            int add = 0;
            for (int i=0;i<word.length();i++){
                int charIndex = word.charAt(i)-'a';
                if (wordsTree.get(add).chars[charIndex]==0){
                    int size = wordsTree.size();
                    wordsTree.get(add).chars[charIndex] = size;
                    wordsTree.add(new Node());
                }
                add = wordsTree.get(add).chars[charIndex];
            }
            wordsTree.get(add).flag = true;
        }
    }
//    private boolean isMatch(String s, int lo){
//        if (lo == s.length()) {
//            return true;
//        }
//        boolean ret = false;
//        for (int i=s.length()-1;i>=lo;i--){
//            if (contains(s,lo,i)){
//                ret = ret || isMatch(s,i+1);
//            }
//        }
//        return ret;
//    }

    private boolean contains(String s, int lo, int hi){
        int search = 0;
        for (int i=lo;i<=hi;i++){
            int charIndex = s.charAt(i)-'a';
            if (wordsTree.get(search).chars[charIndex]==0){
                return false;
            }
            search = wordsTree.get(search).chars[charIndex];
        }
        return wordsTree.get(search).flag;
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("a");
        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaa");
        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaa");
        wordDict.add("aaaaaaaa");
        wordDict.add("aaaaaaaaa");
        wordDict.add("aaaaaaaaaa");
        System.out.println(new WordBreak().wordBreak(s,wordDict));
    }
}
