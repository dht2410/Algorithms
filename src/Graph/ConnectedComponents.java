package Graph;

/*
连通图问题
计算连通图的个数
 */
public class ConnectedComponents {
    private boolean[] marked;  //记录某点有未被访问
    private int[] id;          //记录某点属于哪个联通子图
    private int count;         //记录子图个数
    public ConnectedComponents(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s=0;s<G.V();s++){
            if(!marked[s]){
                marked[s]=true;
                count++;
                dfs(G,s);
            }
        }
    }
    private void dfs(Graph G, int s){
        id[s]=count;
        for (int v:G.adj(s)){
            if(!marked[v]){
                marked[v]=true;
                dfs(G,v);
            }
        }
    }
    public boolean connected(int s, int v){
        return id[s]==id[v];
    }
    public int count(){
        return count;
    }
    public static void main(String[] args){
        Graph G = new Graph(5);
        G.addEdge(0,1);
        G.addEdge(2,3);
        G.addEdge(1,2);
        ConnectedComponents cc = new ConnectedComponents(G);
        System.out.println(cc.count());
    }
}
