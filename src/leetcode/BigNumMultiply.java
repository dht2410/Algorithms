package leetcode;

/**
 * 大数相乘
 * 列竖式相乘
 * 注意高位的index小，低位的index大
 */
public class BigNumMultiply {
    public String multiply(String num1, String num2) {

        int lengthNum1 = num1.length();
        int lengthNum2 = num2.length();
        int[] strArray = new int[lengthNum1+lengthNum2];
        char[] num1Array = num1.toCharArray();
        char[] num2Array = num2.toCharArray();

        for (int i=lengthNum1-1;i>=0;i--){
            int multiNum1 = num1Array[i]-'0';
            if (multiNum1==0) {
                continue;
            }
            int add = 0;
            for (int j=lengthNum2-1;j>=0;j--){
                int multiNum2 = num2Array[j]-'0';
                int multiplyNum = multiNum1*multiNum2+add+strArray[i+j+1];
                strArray[i+j+1] = multiplyNum%10;
                add = multiplyNum/10;
            }
            strArray[i]+=add;
            if (strArray[i]>9){
                strArray[i] = add%10;
                strArray[i-1] = add/10;
            }
        }

        int index=0;
        while(strArray[index]==0){
            index++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i=index;i<strArray.length;i++){
            sb.append(strArray[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BigNumMultiply bigNumMultiply = new BigNumMultiply();
        String s1 = "123";
        String s2 = "11798";
        System.out.println(bigNumMultiply.multiply(s1,s2));
    }
}
