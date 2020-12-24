package leetcode;

public class MonotoneIncreasingDigits {
    public int monotoneIncreasingDigits(int N) {
        if (N==0) return 0;
        int[] numArray = new int[10];
        int index = setArray(numArray,N);
        int notMatchIndex = getNotMatchIndex(numArray,index);
        System.out.println(notMatchIndex);

        if (notMatchIndex>0){
            while (notMatchIndex<index){
                if (numArray[notMatchIndex]!=numArray[notMatchIndex+1]){
                    break;
                }
                notMatchIndex++;
                if (notMatchIndex==index){
                    break;
                }
            }
        }

        int ans = 0;
        while (index>=0){
            if (index>notMatchIndex){
                ans=ans*10+numArray[index];
            }else if (index==notMatchIndex){
                ans=ans*10+(numArray[index]-1);
            }else{
                ans=ans*10+9;
            }
            index--;
        }
        return ans;
    }
    private int setArray(int[] numArray, int N){
        int index = 0;
        while (N>0){
            int mod = N%10;
            numArray[index++] = mod;
            N/=10;
        }
        return index-1;
    }
    private int getNotMatchIndex(int[] numArray, int index){
        for (int i=index;i>0;i--){
            if (numArray[i]>numArray[i-1]){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new MonotoneIncreasingDigits().monotoneIncreasingDigits(1234));
    }
}
