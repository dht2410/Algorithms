package HUAWEI2020;
import java.util.*;

public class FindCycles {

    static List<Integer> trace;
    static Set<Integer> searched=new HashSet<>();
    static Set<List<Integer>> allCircles = new HashSet<>();

    public static void main(String[] args) {
        int n=7;
        int[][] e={ {0,1,1,0,0,0,0}, {0,0,0,1,0,0,0},
                {0,0,0,0,0,1,0}, {0,0,0,0,1,0,0},
                {0,0,1,0,0,0,0}, {0,0,0,0,1,0,1},
                {1,0,1,0,0,0,0}};
        for(int i=0;i<n;i++){
            if(searched.contains(i))
                continue;
            trace =new ArrayList<>();
            findCycle(i,e);
        }

        for(List<Integer> list:allCircles)
            System.out.println("circle: "+list);
    }

    static void findCycle(int v, int[][]e){
        int j=trace.indexOf(v);
        if(j!=-1) {
            List<Integer> circle=new ArrayList<>();
            while(j<trace.size()) {
                circle.add(trace.get(j));
                j++;
            }
            Collections.sort(circle);
            allCircles.add(circle);
            return;
        }

        trace.add(v);
        for(int i=0;i<e.length;i++) {
            if(e[v][i]==1){
                searched.add(i);
                findCycle(i,e);
            }
        }
        trace.remove(trace.size()-1);
    }

}
