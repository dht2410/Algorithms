package leetcode;

/**
 * 剑指offer_19   正则表达式
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 */
public class ZhengZePiPei_JianZhi19 {
    public boolean isMatch(String s, String p) {
        return isMatch(s,p,0,0);
    }
    private boolean isMatch(String s, String p, int index1, int index2){

        //两个终止条件，都必须index1==s.length()
        //index2可以等于p.length()，或者下一个字符是'*',或者是一个普通字符一个'*'轮流出现
        if (index1==s.length()&& (index2==p.length() || (index2+1==p.length() && p.charAt(index2)=='*')))
            return true;
        if (index1==s.length()||index2==p.length()) {
            if (index1==s.length()){
               return change(p,index2);
            }
            return false;
        }

        //index2的后一个是'*'，先判断这个，再到下一个
        if (index2+1<p.length() && p.charAt(index2+1)=='*'){
            if (solve(s.charAt(index1),p.charAt(index2)))
                return isMatch(s,p,index1+1,index2)||isMatch(s,p,index1,index2+2);
            else
                return isMatch(s,p,index1,index2+2);
        }

        //index2后一个不为'*'，看index1和index2对应的是否相等
        if (solve(s.charAt(index1),p.charAt(index2))){
            return isMatch(s,p,index1+1,index2+1);
        }
        return false;
    }

    private boolean change(String p, int index2) {
        while (index2<p.length()){
            if (index2+1<p.length() && p.charAt(index2+1)=='*')
                index2+=2;
            else
                return false;
        }
        return true;
    }

    private boolean solve(char c1, char c2){
        if (c1==c2 || c2=='.')
            return true;
        return false;
    }

    public static void main(String[] args) {
        ZhengZePiPei_JianZhi19 regex = new ZhengZePiPei_JianZhi19();
        String s = "aaa";
        String p = "aaaa";
        System.out.println(regex.isMatch(s,p));
    }
}
