package Graph;
import Queue.Deque;
import java.util.Stack;
/*
广度优先搜索，找最短路径
时间复杂度：和所有顶点的度数之和成正比
 */
public class BreadthFirstPaths  {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;
    public BreadthFirstPaths(Graph G, int s){
        this.s =s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        BFP(G,s);
    }
    //FIFO,利用队列实现逐层遍历
    private void BFP(Graph G, int s){
        Deque<Integer> deque = new Deque<>();
        marked[s]=true;
        deque.pushleft(s);
        while(!deque.isEmpty()){
            int v = deque.popRight();
            for(int m : G.adj(v)){
                if(!marked[m]){
                    marked[m]=true;
                    edgeTo[m]=v;
                    deque.pushleft(m);
                }
            }
        }
    }
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v){
        if (!marked[v]) return null;
        Stack<Integer> stack = new Stack<>();
        for(int x=v;x!=s;x=edgeTo[x]){
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
}
