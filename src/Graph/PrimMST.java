package Graph;

import java.util.HashMap;
/*
* prim算法的即时实现版本
* */
public class PrimMST {
    private Edge[] edgeTo;  //某节点距离树最近的边
    private double[] distTo;  //某节点距离树最近的距离
    private boolean[] marked;  //是否加入树
    private HashMap<Integer,Double> pq;  //有效横切边
    public PrimMST(EdgeWeightedGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        for(int i=0;i<G.V();i++)
            distTo[i]=Double.MAX_VALUE;
        marked = new boolean[G.V()];
        pq = new HashMap<>();

        distTo[0]=0;
        pq.put(0,0.0);
        while(!pq.isEmpty()){
            int key = min(pq);
            pq.remove(key);
            visit(G,key);
        }
    }
    private int min(HashMap<Integer,Double> pq){
        int ret = -1;
        double mini = Double.MAX_VALUE;
        for(int key:pq.keySet()){
            if (pq.get(key)<mini)
            {
                mini = pq.get(key);
                ret = key;
            }
        }
        return ret;
    }
    private void visit(EdgeWeightedGraph G, int v){
        marked[v]=true;
        for( Edge e: G.adj(v)){
            int w = e.other(v);
            if(marked[w])
                continue;
            if(e.weight()<distTo[w]){
                distTo[w]=e.weight();
                edgeTo[w]=e;
                pq.put(w,e.weight());
            }
        }
    }
    public double weight(){
        double sum = 0;
        for (int i=0;i<distTo.length;i++)
            sum+=distTo[i];
        return sum;
    }
    public static void main(String[] args){
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(3);
        ewg.addEdge(new Edge(0,1,0.1));
        ewg.addEdge(new Edge(2,1,0.2));
        ewg.addEdge(new Edge(0,2,0.3));
        PrimMST prim = new PrimMST(ewg);
        System.out.println(prim.weight());
    }
}
