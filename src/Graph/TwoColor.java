package Graph;
/*
* 二色问题，说明图中节点能否相邻的都不同色
* 深度优先搜索，往下找，找到如果一个点被标记了，比较它和
* 进入它的点的颜色*/
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;
    public TwoColor(Graph G){
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s=0;s<G.V();s++){
            if(!marked[s]){
                dfs(G,s);
            }
        }
    }
    private void dfs(Graph G, int s){
        marked[s]=true;
        for(int v:G.adj(s)){
            if (!marked[v]){
                color[v]=!color[s];
                dfs(G,v);
            }
            else if(color[v]==color[s])
                isTwoColorable=false;
        }
    }
    public boolean isBipartite(){
        return isTwoColorable;
    }
    public static void main(String[] args){
        Graph G = new Graph(5);
        G.addEdge(0,1);
        G.addEdge(2,0);
        G.addEdge(1,2);
        TwoColor tc = new TwoColor(G);
        System.out.println(tc.isBipartite());
    }
}
