package Graph;
import Heap.HeapbyArray;
import Queue.Deque;

import java.util.PriorityQueue;

/*
* 最下生成树的Prim算法的延迟实现
* 基于贪心算法
* */
public class LazyPrimMST {
    private boolean[] marked;  //记录是否加入生成树
    private Deque<Edge> mst;   //生成树的边
    private PriorityQueue<Edge> pq;   //横切边（包括失效的边），利用一个优先队列实现

    public LazyPrimMST(EdgeWeightedGraph G){
        marked = new boolean[G.V()];
        mst = new Deque<>();
        pq = new PriorityQueue<Edge>();
        visit(G,0);
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if (marked[v]&&marked[w])
                continue;
            mst.pushleft(e);
            if (!marked[v]) visit(G,v);
            if (!marked[w]) visit(G,w);
        }
    }
    private void visit(EdgeWeightedGraph G, int v){
        marked[v]=true;
        for(Edge e:G.adj(v)){
            int w = e.other(v);
            if(!marked[w]) pq.offer(e);
        }
    }
    public Deque<Edge> edges(){
        return mst;
    }
    public double weights(){
        int sum = 0;
        for (Edge e:mst){
            sum+=e.weight();
        }
        return sum;
    }
    public static void main(String[] args){
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(3);
        ewg.addEdge(new Edge(0,1,0.1));
        ewg.addEdge(new Edge(2,1,0.2));
        ewg.addEdge(new Edge(0,2,0.3));
        LazyPrimMST prim = new LazyPrimMST(ewg);
        while(!prim.edges().isEmpty())
            System.out.println(prim.edges().popLeft().weight());
    }
}
