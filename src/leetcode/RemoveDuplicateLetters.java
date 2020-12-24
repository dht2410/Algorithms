package leetcode;

import java.util.LinkedList;

/**
 * Leetcode 316 去除重复字母
 * 贪心加栈，类似于单调栈
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int[] freq = new int[26];
        for (int i=0;i<s.length();i++){
            freq[s.charAt(i)-'a']++;
        }
        LinkedList<Character> stk = new LinkedList<>();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            freq[c-'a']--;
            if (stk.contains(c)){
                continue;
            }

            while (!stk.isEmpty() && stk.peek()-c>0 && freq[stk.peek()-'a']>0){
                stk.pop();
            }
            stk.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c:stk){
            sb.insert(0,c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "bcabc";
        new RemoveDuplicateLetters().removeDuplicateLetters(s);
    }
}
