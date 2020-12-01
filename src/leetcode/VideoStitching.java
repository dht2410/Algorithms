package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class VideoStitching {
    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0]){
                    return o2[1]-o1[1];
                }
                return o1[0]-o2[0];
            }
        });
        int currEnd = 0;
        int[] endArray = new int[clips.length];
        int size = 0;
        for (int index =0;index<clips.length;index++) {
            if (clips[index][0] > currEnd) {
                return -1;
            }
            if (clips[index][1] > currEnd) {
                size = putEnd(endArray,size,clips[index]);
                currEnd = clips[index][1];
                if (currEnd>=T){
                    return size;
                }
            }
        }
        return -1;
    }

    private int putEnd(int[] endArray, int size, int[] clip) {
        if (size==0){
            endArray[0] = clip[1];
            return 1;
        }
        int index = 0;
        while (endArray[index]<clip[0]){
            index++;
        }
        endArray[++index] = clip[1];
        return index+1;
    }

    public static void main(String[] args) {
        int[][] clips = new int[][]{
                new int[]{0,2},
                new int[]{4,6},
                new int[]{8,10},
                new int[]{1,9},
                new int[]{1,5},
                new int[]{5,9}
        };
        int T = 10;
        System.out.println(new VideoStitching().videoStitching(clips,T));
    }
}
