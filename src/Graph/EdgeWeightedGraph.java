package Graph;

import java.util.LinkedList;
/*
* 有权无向图，认为稀疏，依然用邻接表来表示
* Edge类为图的边
* */
public class EdgeWeightedGraph {
    private final int V;  //顶点总数
    private int E;        //边的总数
    private LinkedList<Edge>[] adj;
    public EdgeWeightedGraph(int V){
        this.V=V;
        adj=(LinkedList<Edge>[]) new LinkedList[V];
        for(int i=0;i<V;i++)
            adj[i] = new LinkedList<>();
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    public Iterable<Edge> adj(int v){
        return adj[v];
    }
    public Iterable<Edge> Edges(){
        LinkedList<Edge> edges = new LinkedList<>();
        for(int i=0;i<V;i++){
            for(Edge e:adj[i])
                if (e.other(i)>i) edges.add(e);
        }
        return edges;
    }
}
class Edge implements Comparable<Edge>{
    private final int v;
    private final int w;
    private final double weight;
    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public double weight(){
        return weight;
    }
    public int either(){
        return v;
    }
    public int other(int vertex){
        if(vertex==v) return w;
        else if(vertex ==w) return v;
        else throw new RuntimeException("Inconsistant Edge");
    }
    @Override
    public int compareTo(Edge that) {
        if (this.weight<that.weight) return -1;
        else if(this.weight>that.weight) return 1;
        else return 0;
    }
}
