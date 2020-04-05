package Graph;

import java.util.LinkedList;

public class DiGraph {
    private final int V;  //节点数
    private int E;  //边数
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
}
