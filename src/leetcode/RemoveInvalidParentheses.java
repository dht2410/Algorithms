package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 301 删除无效的括号
 * dfs
 * 首先找出来多余的左括号数left和右括号数right
 * dfs删除，如果left和right同时为0，则判断目前的字符串是否符合要求
 * 用一个boolean[]数组帮助去重
 * */
public class RemoveInvalidParentheses {
    List<String> ans = new ArrayList<>();
    boolean[] del;
    public List<String> removeInvalidParentheses(String s) {
        int left = 0;
        int right = 0;
        del = new boolean[s.length()];
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (c=='('){
                left++;
            }
            else if (c==')'){
                if (left>0) left--;
                else right++;
            }
        }
        dfs(s,0,new StringBuilder(),left,right);
        return ans;
    }
    private void dfs(String s, int index, StringBuilder sb, int left, int right){
        if (left==0 && right==0){
            String str = sb.toString()+s.substring(index);
            if (check(str)){
                ans.add(str);
            }
            return;
        }
        if (index==s.length()) return;
        char c = s.charAt(index);
        if (c!='(' && c!=')'){
            sb.append(c);
            dfs(s,index+1,sb,left,right);
            sb.deleteCharAt(sb.length()-1);
        }
        else if (c=='('){
            if (left>0){
                if (index==0 || s.charAt(index-1)!='(' || (s.charAt(index-1)=='(' && del[index-1])){
                    del[index] = true;
                    dfs(s,index+1,sb,left-1,right);
                    del[index] = false;
                }
            }
            sb.append(c);
            dfs(s,index+1,sb,left,right);
            sb.deleteCharAt(sb.length()-1);
        }
        else {
            if (right>0){
                if (index==0 || s.charAt(index-1)!=')' || (s.charAt(index-1)==')' && del[index-1])){
                    del[index] = true;
                    dfs(s,index+1,sb,left,right-1);
                    del[index] = false;
                }
            }
            sb.append(c);
            dfs(s,index+1,sb,left,right);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    private boolean check(String s){
        int left = 0;
        int right = 0;
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (c=='('){
                left++;
            }
            else if (c==')'){
                if (left>0) left--;
                else right++;
            }
        }
        return left==0 && right==0;
    }

    public static void main(String[] args) {
        String s = ")(";
        System.out.println(new RemoveInvalidParentheses().removeInvalidParentheses(s));
    }
}
