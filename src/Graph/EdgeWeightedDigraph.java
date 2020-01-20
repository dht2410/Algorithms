package Graph;

import java.util.LinkedList;
/*
* 加权有向图数据类型EdgeWeightedDigraph
* 有向边DirectedEdge
* */
public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private LinkedList<DirectedEdge>[] adj;
    public EdgeWeightedDigraph(int V){
        this.V=V;
        adj = (LinkedList<DirectedEdge>[])new LinkedList[V];
        for(int i=0;i<V;i++){
            adj[i]=new LinkedList<DirectedEdge>();
        }
    }
    public int V(){return V;}
    public int E(){return E;}
    public void addEdge(DirectedEdge e){
        adj[e.from()].add(e);
        E++;
    }
    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }
    public Iterable<DirectedEdge> edges(){
        LinkedList<DirectedEdge> edges = new LinkedList<>();
        for (int i=0;i<V;i++){
            for(DirectedEdge e:adj[i])
                edges.add(e);
        }
        return edges;
    }
}

class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;
    public DirectedEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public int from(){
        return v;
    }
    public int to(){
        return w;
    }
    public double weight(){
        return weight;
    }
}
