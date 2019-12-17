package Graph;
/*
深度优先搜索
 */
public class DepthFirstSearch {
    private boolean[] marked;    //标记顶点i是否已被访问过
    private int count;           //访问过多少个节点
    public DepthFirstSearch(Graph G,int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }
    private void dfs(Graph G, int s){
        marked[s]=true;
        count++;
        for(int v:G.adj(s)){
            if(!marked[v])
                dfs(G,v);
        }
    }
    public boolean marked(int s){
        return marked[s];
    }
    public int count(){
        return count;
    }
}
