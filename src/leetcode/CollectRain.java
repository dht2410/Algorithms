package leetcode;

import java.util.Stack;

/**
 * 接雨水
 * 利用栈来实现
 * 从左到右遍历数组，如果栈顶比此数大，则入栈；如果比它小，则说明有雨水可接，出栈，此数和现在栈顶组成两端
 * 入栈的都是index
 */
public class CollectRain {
    public int trap(int[] height) {
        Stack<Integer> stk = new Stack<>();
        int ans = 0;
        stk.push(0);
        for (int index=1;index<height.length;index++){
            int newBar = height[index];
            while(!stk.isEmpty() && newBar>height[stk.peek()]){
                int lowIndex = stk.pop();
                if (!stk.isEmpty()){
                    ans+= (Math.min(newBar,height[stk.peek()])-height[lowIndex])*(index-stk.peek()-1);
                }
            }
            if (stk.isEmpty() || newBar<=height[stk.peek()]){
                stk.push(index);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        CollectRain cr = new CollectRain();
        System.out.println(cr.trap(new int[]{5,2,1,2,1,5}));
    }
}
