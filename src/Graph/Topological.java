package Graph;

/*
* 拓扑排序
* 即，在有向无环图均有靠前的顶点指向靠后的顶点
* 基于深度优先搜索的顶点排序的逆后续即是拓扑排序的结果
* 时间复杂度和V+E成正比
* */
public class Topological {
    private Iterable<Integer> reservePost;  //逆后续
    public Topological(DiGraph G){
        DirectedCycle dc = new DirectedCycle(G);
        if(!dc.hasLoop()){
            DepthFirstOrder dfo = new DepthFirstOrder(G);
            this.reservePost = dfo.repost();
        }
    }
    public boolean isDAG(){
        return reservePost!=null;
    }
    public Iterable<Integer> order(){
        return reservePost;
    }
}
