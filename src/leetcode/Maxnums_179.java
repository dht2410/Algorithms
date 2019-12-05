package leetcode;
/*
Leetcode 第179题 排序
给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
示例 1:
输入: [10,2]
输出: 210

思路：
重新进行数字大小的定义，分别依次从最高位进行比较，类型转换成字符串比较
记住！！！排序时候应该是从大到小还是从小到大
快速排序传进去应该让i=lo, j =hi+1, 然后++i,--j
注意所有元素都是0的情况
 */
public class Maxnums_179 {
    public static String largestNumber(int[] nums) {
        int N = nums.length;
        quicksort(nums,0,N-1);
        StringBuffer sb = new StringBuffer();
        for (int i =0;i<N;i++)
            sb.append(nums[i]);
        String s = sb.toString();
        return (s.substring(0,1).equals("0"))?"0":s;
    }
    public static void quicksort(int[] nums, int lo, int hi){
        if (lo>=hi) return;
        int i = lo;
        int j = hi+1;
        int n = nums[lo];
        while(true){
            while(comp(nums[++i],n)==1){
                if (i==hi)
                    break;
            }
            while(comp(nums[--j],n)==0) {
                if(j==lo){
                    break;
                }
            }
            if (i>=j) break;
            exch(nums,i,j);
        }
        exch(nums,lo,j);
        quicksort(nums,lo,j-1);
        quicksort(nums,j+1,hi);
    }
    public static int comp(int m, int n){
        if (m==n) return 1;
        Integer mm = new Integer(m);
        Integer nn = new Integer(n);
        String a = mm.toString()+nn.toString();
        String b = nn.toString()+mm.toString();
        while(a.length()!=0 && b.length()!=0){
            int high_a = (int) a.charAt(0);
            int high_b = (int) b.charAt(0);
            if(high_a>high_b) return 1;
            else if(high_a<high_b) return 0;
            else{
                a =a.substring(1,a.length());
                b =b.substring(1,b.length());
            }
        }
        return 1;
    }
    public static void exch(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void main(String[] args){
        int[] a = {10,2};
        System.out.println(largestNumber(a));
    }
}
