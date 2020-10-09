package leetcode;

import java.util.LinkedList;

/**
 * LeetCode 84 柱状图中最大的矩形
 * 利用单调栈，从左到右得到小于第i个元素的最近的index
 *
 */
public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int[] leftMin = getLeftMin(heights,new LinkedList<>());
        int[] rightMin = getRightMin(heights,new LinkedList<>());
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            ans = Math.max(ans,heights[i]*(rightMin[i]-leftMin[i]-1));
        }
        return ans;
    }

    private int[] getRightMin(int[] heights, LinkedList<Integer> stk) {
        stk.push(heights.length);
        int[] rightMin = new int[heights.length];
        for (int i = heights.length - 1; i >= 0; i--) {
            int num = heights[i];
            while(stk.peek()<heights.length && heights[stk.peek()]>=num){
                stk.pop();
            }
            rightMin[i] = stk.peek();
            stk.push(i);
        }
        return rightMin;
    }

    private int[] getLeftMin(int[] heights, LinkedList<Integer> stk) {
        stk.push(-1);
        int[] leftMin = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            int num = heights[i];
            while (stk.peek()>-1 && heights[stk.peek()]>=num){
                stk.pop();
            }
            leftMin[i] = stk.peek();
            stk.push(i);
        }
        return leftMin;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2,1,2};
        System.out.println(new LargestRectangleArea().largestRectangleArea(heights));
    }

}
