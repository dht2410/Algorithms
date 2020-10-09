package leetcode;

/**
 * LeetCode 85 最大矩形
 * 转换成84的柱状图中的最大矩形
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix){
        int ans = 0;
        for (int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                ans = Math.max(ans,getMaxArea(matrix,i,j));
            }
        }
        return ans;
    }

    private int getMaxArea(char[][] matrix, int x, int y) {
        int len = matrix.length;
        int[] heights = getBar(matrix,x,y);
        return new LargestRectangleArea().largestRectangleArea(heights);
    }

    private int[] getBar(char[][] matrix, int x, int y) {
        int len = matrix.length;
        int[] heights = new int[len-x];
        for (int i = 0; i < heights.length; i++) {
            int high = 0;
            while(y-high>=0 && matrix[x+i][y-high]=='1'){
                high++;
            }
            heights[i] = high;
        }
        return heights;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                new char[]{'1','0','1','0','0'},
                new char[]{'1','0','1','1','1'},
                new char[]{'1','1','1','0','1'},
                new char[]{'1','0','1','1','1'},

        };
        System.out.println(new MaximalRectangle().maximalRectangle(matrix));
    }

}
