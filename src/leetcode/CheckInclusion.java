package leetcode;
import java.util.HashMap;
/**
 * LeetCode567 字符串排序
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 利用滑动窗口
 */
public class CheckInclusion {
    HashMap<Character,Integer> patternMap = new HashMap<>();
    HashMap<Character,Integer> strMap = new HashMap<>();
    public boolean checkInclusion(String s1, String s2) {
        for (char c:s1.toCharArray()){
            patternMap.put(c,patternMap.getOrDefault(c,0)+1);
        }
        int left=0, right=0;
        while(right<s2.length()){
            char c = s2.charAt(right);
            right++;
            if (!patternMap.containsKey(c)){
                left=right;
                strMap = new HashMap<>();
                continue;
            }
            int patternVal = patternMap.get(c);
            int strVal = strMap.getOrDefault(c,0);
            if (patternVal>strVal){
                if (right-left==s1.length()) {
                    return true;
                }
                strMap.put(c,strVal+1);
            }
            else {
                strMap.put(c,strVal+1);
                while(left<right && !check(c)){
                    char cLeft = s2.charAt(left);
                    strMap.put(cLeft, strMap.get(cLeft)-1);
                    left++;
                }
            }
        }
        return false;
    }

    private boolean check(char c){
        int num1 = patternMap.get(c);
        int num2 = strMap.get(c);
        return num2<=num1;
    }

    public static void main(String[] args) {
        String s1 = "ky";
        String s2 = "ainwkckifykxlribaypk";
        System.out.println(new CheckInclusion().checkInclusion(s1,s2));
    }
}
