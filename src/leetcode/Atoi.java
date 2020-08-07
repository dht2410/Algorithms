package leetcode;

public class Atoi {
    public int myAtoi(String str) {
        int ret;
        int index = findIndex(str);
        if (index==-1) {
            return 0;
        }
        ret = getAtoi(str, index);
        return ret;
    }

    private int getAtoi(String str, int index) {
        long num = 0;
        int t=1;
        if (str.charAt(index)=='+' ||str.charAt(index)=='-'){
            if (str.charAt(index)=='-'){
                t=-1;
            }
            index++;
            if (index==str.length()){
                return 0;
            }
        }
        while (Character.isDigit(str.charAt(index))){

            num = num*10+(str.charAt(index)-'0');
            if (num>=Integer.MAX_VALUE && t==1) {
                return Integer.MAX_VALUE;
            }
            if (num>Integer.MAX_VALUE && t==-1) {
                return Integer.MIN_VALUE;
            }
            index++;
            if (index==str.length()){
                break;
            }
        }
        return (int)num*t;
    }

    private int findIndex(String str){
        int N = str.length();
        for (int i=0;i<N;i++){
            char c = str.charAt(i);
            if (c!=' ') {
                if (!Character.isDigit(c) && c!='+' && c!='-'){
                    return -1;
                }
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi(" a4133333333333"));
    }
}
