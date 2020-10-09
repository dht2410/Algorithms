package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode 56 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 首先按区间的左边界排序，然后一个个往后加，如果没有重合，则直接加在列表后；如果有重合，则修改列表最后一个元素
 */
public class Merge {
    public int[][] merge(int[][] intervals) {
        if (intervals.length==0) {
            return intervals;
        }
        Arrays.sort(intervals, (int[] o1, int[] o2)-> {
                return o1[0]-o2[0];
            }
        );
        ArrayList<int[]> rangeList = new ArrayList<>();
        rangeList.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] oldRange = rangeList.get(rangeList.size()-1);
            int[] newRange = intervals[i];
            if (newRange[0]>oldRange[0]){
                rangeList.add(newRange);
            }
            else if (newRange[0]<=oldRange[0] && newRange[1]>=oldRange[1]){
                oldRange[1] = newRange[1];
            }
        }
        int[][] ans = new int[rangeList.size()][2];
        for (int i = 0; i < rangeList.size(); i++) {
            ans[i] = rangeList.get(i);
        }
        return ans;
    }
}
