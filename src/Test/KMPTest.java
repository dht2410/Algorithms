package Test;

import java.util.Arrays;

public class KMPTest {
    public static void main(String[] args) {
        String pattern = "abcabda";
        int m = pattern.length();
        int[] fail = new int[m];
        Arrays.fill(fail,-1);
        for (int i = 1; i < m; ++i) {
            int j = fail[i - 1];
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        for (int i : fail) {
            System.out.println(i);
        }
    }
}
