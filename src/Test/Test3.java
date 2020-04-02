package Test;

import java.util.Scanner;

public class Test3 {
    public int minimumLengthEncoding(String[] words) {
        int N = words.length;
        if (N==0) return 0;
        if (N==1) return (words[0].length()+1);
        int len = words[0].length()+1;
        for (int i=1;i<N;i++){
            int j;
            for (j=0;j<i;j++){

                if (contains(words[i],words[j])){
                    System.out.println(words[0]+words[1]);
                    if (words[i].length()>words[j].length()){
                        len+=(words[i].length()-words[j].length());
                        break;
                    }
                }
            }
            if (j==i){
                len+=words[i].length();
                len++;
            }
        }
        return len;
    }
    public boolean contains(String a, String b){
        int N1 = a.length();
        int N2 = b.length();
        if (N1>N2){
            return a.substring(N1-N2,N1).equals(b);
        }
        else{
            return b.substring(N2-N1,N2).equals(a);
        }
    }
    public static void main(String[] args){
       String[] words = new String[3];
       words[0]="time";
       words[1]="me";
       words[2]="read";
       Test3 t = new Test3();
       System.out.println(t.minimumLengthEncoding(words));
       System.out.println(t.contains(words[0],words[1]));
    }
}
