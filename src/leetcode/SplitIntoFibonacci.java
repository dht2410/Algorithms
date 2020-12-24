package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SplitIntoFibonacci {
    List<Integer> ans = new ArrayList<>();
    boolean get = false;
    public List<Integer> splitIntoFibonacci(String S) {
        getList(S,0,new ArrayList<>());
        return ans;
    }
    private void getList(String S, int left, List<Integer> list){
        int len = S.length();
        if (left>=len && list.size()>2){
            ans = new ArrayList<>(list);
            get = true;
            System.out.println(list);
            return;
        }
        if (left>=len) return;
        if (list.size()>1){
            int sum = list.get(list.size()-1)+list.get(list.size()-2);
            int numOfBit = getNumOfBit(sum);
            int num = convertToNum(S,left,left+numOfBit-1);
            if (num==-1) return;
            if (num==sum){
                list.add(num);
                getList(S,left+numOfBit,list);
                if (get) return;
                list.remove(list.size()-1);
            }
        }
        else {
            for (int right=left;right<len;right++){
                int num=convertToNum(S,left,right);
                if (num==-1) return;
                list.add(num);
                getList(S,right+1,list);
                if (get) return;
                list.remove(list.size()-1);
                if (S.charAt(left)=='0'){
                    break;
                }
            }
        }
    }
    private int getNumOfBit(int sum){
        if (sum==0) return 1;
        int bits = 0;
        while(sum>0){
            bits++;
            sum/=10;
        }
        return bits;
    }
    private int convertToNum(String S, int left, int right){
        if (right>=S.length()) return -1;
        if (S.charAt(left)=='0') return 0;
        int ret = 0;
        for (int i=left;i<=right;i++){
            char c = S.charAt(i);
            ret = ret*10+(c-'0');
            if (ret<=0) return -1;
        }
        return ret;
    }
}
