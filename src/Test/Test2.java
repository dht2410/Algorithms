package Test;

import java.util.Scanner;

public class Test2 {
    public static int[] getList(int N, int L){
        int sum;
        int start = N/L-L/2;

        if(start<0) {
            start = 0;
        }
        while(true){
            sum = (start*2+L-1)*L/2;

            if (sum==N){
                int[] a = new int[2];
                a[0] = start;
                a[1] = L;
                return a;
            }
            else if (sum>N){
                L++;
                if (L>100){
                    int[] a = new int[2];
                    return a;
                }
                start = N/L-L/2;
            }
            else {

                start++;
            }
        }

    }
    public static void main(String[] args){
        char[] c = new char[4];
        String s = new String(c);
        System.out.println(s.length());
    }
}
