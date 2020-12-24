package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Candy {
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len==1) return 1;
        List<Integer> minIndexList = getMinIndex(ratings);
        int[] candys = new int[len];
        for (int i:minIndexList){
            candys[i] = 1;
        }
        for (int i:minIndexList){
            setLeft(candys,ratings,i);
            setRight(candys,ratings,i);
        }
        return sum(candys);
    }
    private void setLeft(int[] candys, int[] ratings, int index){
        int candyNum = 1;
        index--;
        while (index>=0){
            if (ratings[index]>ratings[index+1]){
                candyNum++;
                if (candyNum>candys[index]){
                    candys[index] = candyNum;
                }
            }
            else{
                break;
            }
            index--;
        }
    }
    private void setRight(int[] candys, int[] ratings, int index){
        int candyNum = 1;
        index++;
        while (index<candys.length){
            if (ratings[index]>ratings[index-1]){
                candyNum++;
                candys[index] = candyNum;
            }
            else {
                break;
            }
            index++;
        }
    }
    private List<Integer> getMinIndex(int[] ratings){
        List<Integer> list = new ArrayList<>();
        int len = ratings.length;

        if (ratings[0]<=ratings[1]) list.add(0);
        for (int i=1;i<len-1;i++){
            if (ratings[i]<=ratings[i-1] && ratings[i]<=ratings[i+1]){
                list.add(i);
            }
        }
        if (ratings[len-2]>=ratings[len-1]) list.add(len-1);

        return list;
    }
    private int sum(int[] nums){
        int s = 0;
        for (int i:nums){
            s+=i;
        }
        return s;
    }

    public static void main(String[] args) {
        int[] ratings = new int[]{1,2,2};
        System.out.println(new Candy().candy(ratings));
    }
}
