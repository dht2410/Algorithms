package Graph;

/*
* 计算强连通分量
* 以反向图的逆后续顺序来遍历*/
public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;
    public KosarajuSCC(DiGraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder dfo = new DepthFirstOrder(G.reserve());
        //以反向图的逆后续顺序来，这里面大有智慧，细品
        for(int s:dfo.repost()){
            if(!marked[s]){
                dfs(G,s);
                count++;
            }
        }
    }
    private void dfs(DiGraph G, int s){
        marked[s] = true;
        id[s]=count;
        for (int v:G.adj(s)){
            if(!marked[v])
                dfs(G,v);
        }
    }
    public boolean stronglyConnected(int s, int v){
        return id[s]==id[v];
    }
    public int getCount(){
        return count;
    }
}
