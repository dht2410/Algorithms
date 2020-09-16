package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    List<List<Integer>> ans = new ArrayList<>();
    List<int[]> map = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int i=0;i<candidates.length;i++){
            if (i==0 || candidates[i]>candidates[i-1]){
                int[] keyVal = new int[2];
                keyVal[0] = candidates[i];
                keyVal[1] = 1;
                map.add(keyVal);
            }
            else{
                map.get(map.size()-1)[1]++;
            }
        }
        List<Integer> combination  = new ArrayList<>();
        dfs(combination,target,0);
        return ans;

    }

    private void dfs(List<Integer> combination, int target, int pos) {
        if (target==0){
            ans.add(new ArrayList<>(combination));
            return;
        }
        if (pos==map.size() || target<0){
            return;
        }
        int newTarget = target;
        int index = 0;
        for (int i=0;i<=map.get(pos)[1];i++){
            target=newTarget-map.get(pos)[0]*i;
            if (target<0){
                break;
            }
            if (i>0){
                combination.add(map.get(pos)[0]);
                index++;
            }
            dfs(combination,target,pos+1);
        }
        for (int i=0;i<index;i++){
            target+=map.get(pos)[0];
            combination.remove(combination.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{10,1,8,2,7,6,1,5};
        int target = 8;
        System.out.println(new CombinationSum2().combinationSum2(candidates,target));
    }
}
