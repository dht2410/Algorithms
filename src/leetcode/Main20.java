package leetcode;

import java.util.LinkedList;

/**
 * 有效的括号，基于栈，利用LinkedList的push和pop方法
 * */
public class Main20 {
    public boolean isValid(String s) {
        LinkedList<Character> stk = new LinkedList<>();
        for (char c:s.toCharArray()){
            if (c=='(' || c=='[' || c=='{'){
                stk.push(c);
            }
            if (c==')' || c==']' || c=='}'){
                if (stk.isEmpty()){
                    return false;
                }
                if (c==')' && stk.pop()!='('){
                    return false;
                }
                else if (c==']' && stk.pop()!='['){
                    return false;
                }
                else if (c=='}' && stk.pop()!='{'){
                    return false;
                }
            }
        }
        return stk.isEmpty();
    }
}
