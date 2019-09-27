package Search;

public class StringSwap {
    public static void main(String[] args){
        String s = "abcdefg";
        System.out.println(Swap1(s));
    }
    //实现字符串逆序
    public static String Swap(String s){
        int len = s.length();
        if (len==1){
            return s;
        }else{
            String a = s.substring(0,len/2);
            String b = s.substring(len/2,len);
            return Swap(b)+Swap(a);
        }
    }
    public static String Swap1(String s){
        int len = s.length();
        //char[] c = new char[len];
        StringBuffer c = new StringBuffer();
        for (int i=0;i<len;i++){
            c.append(s.charAt(len-i-1));
        }
        return c.toString();
    }
}
