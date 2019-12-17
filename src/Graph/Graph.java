package Graph;

import java.util.LinkedList;

public class Graph {
    private final int V;  //顶点数
    private int E;        //边数
    private LinkedList<Integer>[] adj;
    public Graph(int V){
        this.V=V;
        this.E=0;
        //下面初始化需注意
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v=0;v<V;v++){
            adj[v]=new LinkedList<>();
        }
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
