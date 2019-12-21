package Graph;

import Queue.Deque;

import java.util.Stack;
/*
* 有向图基于深度优先搜索的顶点排序
* pre是前序，即dfs()的调用顺序
* post是后序，即顶点遍历完成的顺序
* repost是逆后续
* */
public class DepthFirstOrder {
    private boolean[] marked;    //标记顶点i是否已被访问过
    private Deque<Integer> pre;
    private Deque<Integer> post;
    private Stack<Integer> repost;
    public DepthFirstOrder(DiGraph G){
        pre = new Deque<>();
        post = new Deque<>();
        repost = new Stack<>();
        marked = new boolean[G.V()];
        for(int s=0;s<G.V();s++){
            if(!marked[s])
                dfs(G,s);
        }
    }
    private void dfs(DiGraph G, int s){
        marked[s]=true;
        pre.pushleft(s);
        for(int v:G.adj(s)){
            if(!marked[v])
                dfs(G,v);
        }
        post.pushleft(s);
        repost.push(s);
    }
    public Iterable<Integer> pre(){
        return pre;
    }
    public Iterable<Integer> post(){
        return post;
    }
    public Iterable<Integer> repost(){
        return repost;
    }
}
