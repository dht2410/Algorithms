package Test;

import java.util.Arrays;

public class XingCe {
    int nums[] = new int[4];
    boolean[] marked = new boolean[10];
    boolean checked = false;
    public void getW(){
        int count = 0;
        dfs(count);
    }
    private void dfs(int count){
        if (count==4){
            if (check()){
                checked = true;
                System.out.println(Arrays.toString(nums));
            }
            return;
        }
        for (int i=0;i<=9;i++){
            if (!marked[i]){
                nums[count] = i;
                marked[i] = true;
                dfs(count+1);
//                if (checked){
//                    return;
//                }
                marked[i] = false;
            }
        }
    }
    private boolean check(){
        if (nums[0]==0){
            return false;
        }
        int what = 1000*nums[0]+100*nums[1]+10*nums[2]+nums[3];
        int a = nums[2];
        int show = what*a;
        if (show>9999){
            return false;
        }
        int w = show%10;
        int o = show/10%10;
        int h = show/100%10;
        int s = show/1000;
        if (w==nums[0] && h==nums[1] && !contains(s) && !contains(o) && s!=o){
            return true;
        }
        return false;
    }

    private boolean contains(int x){
        for (int num:nums){
            if (num==x){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new XingCe().getW();
    }
}
