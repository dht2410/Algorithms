package Graph;

import java.util.Stack;
/*
* 检测有无有向环*/
public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;  //记录该点是否在检测路上
    private int[] edgeTo;
    private Stack<Integer> loop;
    public DirectedCycle(DiGraph G){
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for(int s=0;s<G.V();s++){
            if(!marked[s]){
                dfs(G,s);
            }
        }
    }
    private void dfs(DiGraph G, int s){
        marked[s]=true;
        onStack[s]=true;
        for(int v:G.adj(s)){
            if (hasLoop())
                return;
            else if(!marked[v]){
                edgeTo[v]=s;
                dfs(G,v);
            }
            else if(onStack[v]){
                loop = new Stack<>();
                for(int x=s;x!=v;x=edgeTo[x]){
                    loop.push(x);
                }
                loop.push(v);
            }
        }
        onStack[s]=false;
    }
    public boolean hasLoop(){
        return loop!=null;
    }
    public Iterable<Integer> loop(){
        return loop;
    }
    public static void main(String[] args){
        DiGraph dig = new DiGraph(5);
        dig.addEdge(0,1);
        dig.addEdge(1,2);
        dig.addEdge(2,3);
        dig.addEdge(3,4);
        dig.addEdge(4,0);
        DirectedCycle dc = new DirectedCycle(dig);
        System.out.println(dc.loop());
    }
}
