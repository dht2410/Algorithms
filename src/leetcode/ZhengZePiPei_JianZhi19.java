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

    public boolean isMatchWithDP(String s, String p){
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen+1][pLen+1];
        dp[0][0] = true;

        //初始化，如果'*'出现在偶数位置，则表示可以匹配空字符串
        boolean isOk = true;  //状态机
        for (int i=1;i<=sLen;i++){
            if (s.charAt(i-1)!='*'){
                if (i%2==0) isOk = false;
            }
            else if (s.charAt(i-1)=='*' && isOk){
                dp[i][0] = true;
            }
        }
        isOk = true;
        for (int j=1;j<=pLen;j++){
            if (p.charAt(j-1)!='*'){
                if (j%2==0) isOk = false;
            }
            else if (p.charAt(j-1)=='*' && isOk){
                dp[0][j] = true;
            }
        }

        //分是不是'*'，四种情况讨论
       for (int i=1;i<=sLen;i++){
            for (int j=1;j<=pLen;j++){
                char c1 = s.charAt(i-1);
                char c2 = p.charAt(j-1);
                //都不是，看是不是相等或有一个为'.'
                if (c1!='*'&&c2!='*'){
                    if (!dp[i-1][j-1]){
                        dp[i][j] = false;
                    }
                    else if (c1=='.' || c2=='.' || c1==c2){
                        dp[i][j] = true;
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
                //有一个是，先看能不能和0个或1个匹配，再看上一个能不能和它匹配
                else if (c1=='*' && c2!='*'){
                    char c11 = s.charAt(i-2);
                    if (dp[i-1][j]||dp[i-2][j]){
                        dp[i][j] = true;
                    }
                    else if (c11==c2 || c11=='.' || c2=='.'){
                        dp[i][j] = dp[i][j-1];
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
                else if (c1!='*' && c2=='*'){
                    char c22 = p.charAt(j-2);
                    if (dp[i][j-1]||dp[i][j-2]){
                        dp[i][j] = true;
                    }
                    else if (c22==c1 || c22=='.' || c1=='.'){
                        dp[i][j] = dp[i-1][j];
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
                //都是，则是前几个的混合
                else {
                    if (dp[i][j-1] || dp[i-1][j] || dp[i][j-2] || dp[i-2][j]){
                        dp[i][j] = true;
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }
    public static void main(String[] args) {
        ZhengZePiPei_JianZhi19 regex = new ZhengZePiPei_JianZhi19();
        String s = "aaa";
        String p = "aaaa";
        System.out.println(regex.isMatch(s,p));
    }
}
