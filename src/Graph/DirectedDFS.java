package Graph;
/*
* 深度优先搜索（有向图）
* 应用场景：Java自动GC的标记
* */
public class DirectedDFS {
    private boolean[] marked;
    public DirectedDFS(DiGraph G, int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }
    public DirectedDFS(DiGraph G, Iterable<Integer> source){
        marked = new boolean[G.V()];
        for(int s:source){
            dfs(G,s);
        }
    }
    public void dfs(DiGraph G, int s){
        marked[s] = true;
        for(int v:G.adj(s)){
            if(!marked[v])
                dfs(G,v);
        }
    }
    public boolean marked(int v){
        return marked[v];
    }

}
