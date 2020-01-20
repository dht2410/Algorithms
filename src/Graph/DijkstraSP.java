package Graph;

import java.util.LinkedList;
import java.util.Stack;

public class DijkstraSP {
    private int[] edgeTo;  //记录该点的父节点
    private double[] distTo;  //到该点的最短距离
    private LinkedList<Integer> pq;  //需要被检测的边，也就是需要被比较加入树的点
    private int s;  //起点
    public DijkstraSP(EdgeWeightedDigraph G, int s){
        this.s = s;
        edgeTo = new int[G.V()];
        distTo = new double[G.V()];
        for(int i=0;i<G.V();i++)
            distTo[i]=Double.POSITIVE_INFINITY;
        pq = new LinkedList<>();

        distTo[s]=0;
        pq.add(s);
        while(!pq.isEmpty()){
            int v = minDist(pq);
            pq.remove(new Integer(v));
            visit(G,v);
        }
    }
    private int minDist(LinkedList<Integer> pq){
        double min = Double.POSITIVE_INFINITY;
        int ret = -1;
        for(int i:pq){
            if(distTo[i]<min){
                ret = i;
                min = distTo[i];
            }
        }
        return ret;
    }
    private void visit(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e:G.adj(v)){
            int w = e.to();
            if(distTo[v]+e.weight()<distTo[w]){
                distTo[w]=distTo[v]+e.weight();
                edgeTo[w]=v;
                if(!pq.contains(w))
                    pq.add(w);
            }
        }
    }
    public double distTo(int n){
        return distTo[n];
    }
    public Iterable<Integer> edgeTo(int n){
        Stack<Integer> path = new Stack<>();
        for(int x=n;x!=s;x=edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }
    public static void main(String[] args){
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(4);
        G.addEdge(new DirectedEdge(0,1,1));
        G.addEdge(new DirectedEdge(1,2,2));
        G.addEdge(new DirectedEdge(0,2,1.5));
        G.addEdge(new DirectedEdge(2,3,1));
        G.addEdge(new DirectedEdge(1,3,1.2));
        G.addEdge(new DirectedEdge(3,0,1));
        DijkstraSP dijkstraSP = new DijkstraSP(G,0);
        System.out.println(dijkstraSP.distTo(2));
        for(int v:dijkstraSP.edgeTo(2))
            System.out.println(v);
    }
}
