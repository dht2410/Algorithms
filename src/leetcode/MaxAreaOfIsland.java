package leetcode;

import java.util.LinkedList;

public class MaxAreaOfIsland {
    public static int maxAreaOfIsland(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        LinkedList<Integer> queue = new LinkedList<>();
        int curr=0;
        int max=0;
        for (int i=0;i<M;i++){
            for (int j=0;j<N;j++){
                if(grid[i][j]==1){
                    queue.add(i);
                    queue.add(j);
                    grid[i][j]++;
                    curr++;
                    while(!queue.isEmpty()){
                        int x = queue.remove(0);
                        int y = queue.remove(0);
                        for(int w=-1;w<=1;w+=2){
                            if (x+w<0||x+w==M) continue;
                            if (grid[x+w][y]==1){
                                queue.add(x+w);
                                queue.add(y);
                                grid[x+w][y]++;
                                curr++;

                            }

                        }
                        for(int v=-1;v<=1;v+=2){
                            if(y+v<0||y+v==N) continue;
                            if(grid[x][y+v]==1){
                                queue.add(x);
                                queue.add(y+v);
                                grid[x][y+v]++;
                                curr++;
                            }


                        }
                    }
                    if(curr>max) max=curr;
                    curr=0;
                }
            }
        }
        return max;
    }
    public static void main(String[] args){
        int[][] grid = new int[5][1];
        grid[0][0]=1;
        grid[1][0]=1;
        System.out.println(MaxAreaOfIsland.maxAreaOfIsland(grid));
    }
}
