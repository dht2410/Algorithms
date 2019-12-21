package Graph;

import java.util.LinkedList;

public class DiGraph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;
    public DiGraph(int V){
        this.V=V;
        this.E=0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for(int i=0;i<V;i++){
            adj[i]=new LinkedList<>();
        }
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
    public int V(){
        return V;
    }
}
