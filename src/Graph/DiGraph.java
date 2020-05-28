package Graph;

import java.util.LinkedList;

public class DiGraph {
    private final int V;  //节点数
    private int E;  //边数
    private LinkedList<Integer>[] adj;

    private int[] dfn;
    private int[] low;
    private int[] stack;
    private int[] scc;
    private boolean[] marked;
    private boolean[] stk;
    private int index = 0;
    private int top = -1;
    private int sccnum = 0;

    public DiGraph(int V){
        this.V=V;
        this.E=0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for(int i=0;i<V;i++){
            adj[i]=new LinkedList<>();
        }
        dfn = new int[V];
        low = new int[V];
        stack = new int[V];
        scc = new int[V];
        marked = new boolean[V];
        stk = new boolean[V];
    }

    public void addEdge(int s, int v){
        adj[s].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    public DiGraph reserve(){
        DiGraph res = new DiGraph(V);
        for(int i =0;i<V;i++){
            for (int v:adj[i]){
                res.addEdge(v,i);
            }
        }
        return res;
    }
    public int[] getReserveIn(){
        int[] counts = new int[V];
        for(int i =0;i<V;i++){
            for (int v:adj[i]){
                counts[v]++;
            }
        }
        return counts;
    }
    public int V(){
        return V;
    }

    private void tarjan(){
        for (int i=0;i<V;i++){
            if (!marked[i])
                tarjan(i);
        }
    }

    private void tarjan(int v){
        //之前被访问，存在于其他强连通分量
        if(dfn[v]!=0) return;
        marked[v] = true;
        dfn[v] = low[v] = ++index;
        stack[++top] = v;
        stk[v] = true;
        for(int w:adj(v)){
            if (dfn[w]==0){
                tarjan(w);
                low[v] = Math.min(low[v],low[w]);
            }
            else if (stk[w]){
                low[v] = Math.min(low[v],dfn[w]);
            }
        }
        if(dfn[v]==low[v]){
            sccnum++;
            for(;;){
                int x = stack[top--];
                scc[x] = sccnum;
                stk[x] = false;
                if(x==v) break;
            }
        }
    }

    private int getSccnum(){
        tarjan();
        return sccnum;
    }

    public static void main(String[] args){
        DiGraph G = new DiGraph(4);
        G.addEdge(0,1);
        G.addEdge(1,2);
        G.addEdge(2,3);
        G.addEdge(3,0);
        G.addEdge(3,1);
        System.out.println(G.getSccnum());
    }
}
