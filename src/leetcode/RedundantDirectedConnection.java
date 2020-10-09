package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class RedundantDirectedConnection {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int dotNum = getDotNum(edges);
        List<Integer>[] graphOut = new ArrayList[dotNum+1];
        List<Integer>[] graphIn = new ArrayList[dotNum+1];
        for (int i=1;i<dotNum+1;i++){
            graphOut[i] = new ArrayList<>();
            graphIn[i] = new ArrayList<>();
        }
        int doubleInDot = -1;
        for (int i=0;i<edges.length;i++){
            int from = edges[i][0];
            int to = edges[i][1];
            graphOut[from].add(to);
            graphIn[to].add(from);
            if (graphIn[to].size()>1){
                doubleInDot = to;
            }
        }
        if (doubleInDot==-1) {
            for (int i = edges.length - 1; i >= 0; i--) {
                if (delete2(graphOut,graphIn,edges[i][0],edges[i][1])){
                    return edges[i];
                }
            }
        }
        int fromFirst = graphIn[doubleInDot].get(0);
        int fromSecond = graphIn[doubleInDot].get(1);
        if (delete(graphOut,graphIn,fromSecond,doubleInDot)) return new int[]{fromSecond,doubleInDot};
        return new int[]{fromFirst,doubleInDot};
    }

    private boolean delete2(List<Integer>[] graphOut, List<Integer>[] graphIn, int fromSecond, int doubleInDot) {
        int index = graphOut[fromSecond].indexOf(doubleInDot);
        graphOut[fromSecond].remove(index);
        boolean[] marked = new boolean[graphOut.length-1];
        dfs(graphOut,doubleInDot,marked);
        graphOut[fromSecond].add(doubleInDot);
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) return false;
        }
        return true;
    }

    private int getDotNum(int[][] edges) {
        HashSet<Integer> set= new HashSet<>();
        for (int i = 0; i < edges.length; i++) {
            set.add(edges[i][0]);
            set.add(edges[i][1]);
        }
        return set.size();
    }

    private boolean delete(List<Integer>[] graphOut, List<Integer>[] graphIn, int fromSecond, int doubleInDot) {
        int index = graphOut[fromSecond].indexOf(doubleInDot);
        graphOut[fromSecond].remove(index);
        int root  = 1;
        while(graphIn[root].size()>0){
            root++;
        }
        boolean[] marked = new boolean[graphOut.length-1];
        dfs(graphOut,root,marked);
        graphOut[fromSecond].add(doubleInDot);
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) return false;
        }
        return true;
    }

    private void dfs(List<Integer>[] graphOut, int root, boolean[] marked) {
        if (marked[root-1]) return;
        marked[root-1] = true;
        for (int to:graphOut[root]){
            dfs(graphOut,to,marked);
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                new int[]{1,2},
                new int[]{2,3},
                new int[]{3,4},
                new int[]{4,1},
                new int[]{1,5},
        };
        System.out.println(new RedundantDirectedConnection().findRedundantDirectedConnection(edges)[0]);
        System.out.println(new RedundantDirectedConnection().findRedundantDirectedConnection(edges)[1]);
    }

}
