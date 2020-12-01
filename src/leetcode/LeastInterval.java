package leetcode;

import java.util.Arrays;

/**
 * ["A","A","A","A","A","A","B","C","D","E","F","G"]
 2
 */
public class LeastInterval {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c:tasks){
            freq[c-'A']++;
        }
        Arrays.sort(freq);
        return getTime(freq,n);
    }

    private int getTime(int[] freq, int n) {
        int time = 0;
        while (freq[25]>0){
            int round = n+1;
            int begin = freq.length-1;
            while (begin>=0 && freq[begin]>0 && round>0) {
                freq[begin]--;
                begin--;
                round--;
                time++;
            }
            if (round>0 && freq[25]>0){
                time+=round;
            } else if (round==0){
                Arrays.sort(freq);
            }
        }
        return time;
    }

    public int leastIntervalWithIdleSlot(char[] tasks, int n){
        int[] freq = new int[26];
        for (char c:tasks){
            freq[c-'A']++;
        }
        Arrays.sort(freq);
        int idleSlot = (freq[25]-1)*n;
        for (int i=24;i>=0;i--){
            idleSlot-=Math.min(freq[i],freq[25]-1);
        }
        return idleSlot>0?idleSlot+tasks.length:tasks.length;
    }

    public static void main(String[] args) {
        char[] tasks = new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'};
        int n = 2;
        System.out.println(new LeastInterval().leastIntervalWithIdleSlot(tasks,n));
    }
}
