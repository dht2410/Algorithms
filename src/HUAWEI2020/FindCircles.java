package HUAWEI2020;

import Graph.DiGraph;

import java.util.LinkedList;

public class FindCircles {
    public static void main(String[] args){
        int V = 10;  //节点
        final int maxFathers = 6;  //最多往上数6辈
        LinkedList<Node> nodes = new LinkedList<>(); //队列
        //有向图
        DiGraph G = constructExample(V);
        boolean[] marked = new boolean[V];

        for (int i=0;i<V;i++){
            if(!marked[i]){
                Node thisNode = new Node(i,new int[maxFathers],0);
                nodes.add(thisNode);
                marked[i]=true;  //标记被访问过
                while (!nodes.isEmpty()){
                    Node removedNode = nodes.remove();
                    int removedThisNum = removedNode.thisNum;
                    int[] removedFathers = removedNode.fathers;
                    int removedFatherNum = removedNode.fathersNum;
                    //检查有没有环
                    for (int v:G.adj(removedFatherNum)){
                        int index = removedNode.contains(v);
                        if(index==-1){
                            if (!marked[v]) {
                                int[] newFathers = getNewFathers(removedFathers, removedFatherNum, removedThisNum);
                                int newFatherNum = removedFatherNum == maxFathers ? maxFathers : removedFatherNum + 1;
                                nodes.add(new Node(v, newFathers, newFatherNum));
                                marked[v] = true;
                            }
                        }
                        else {
                            removedNode.getCycle(index);
                        }
                    }
                }
            }
        }

    }
    //建一个有向图测试
    private static DiGraph constructExample(int V){
        DiGraph G = new DiGraph(V);
        for (int i=0;i<6;i++){
            G.addEdge(i,i+1);
        }
        G.addEdge(6,0);
        G.addEdge(6,4);
        G.addEdge(4,7);
        G.addEdge(7,8);
        G.addEdge(8,9);
        G.addEdge(9,6);
        return G;
    }

    //获得祖辈节点数组
    private static int[] getNewFathers(int[] Fathers, int fathersNum, int newNodeNum){
        //这里有硬编码，待改
        int[] newFathers = new int[6];
        if (fathersNum==6){
            for(int i=1;i<6;i++){
                newFathers[i-1] = Fathers[i];
            }
            newFathers[5] = newNodeNum;
            return newFathers;
        }
        else{
            for(int i=0;i<fathersNum;i++){
                newFathers[i]=Fathers[i];
            }
            newFathers[fathersNum] = newNodeNum;
            return  newFathers;
        }
    }
}
class Node{
    int thisNum;
    int[] fathers;
    int fathersNum;  //最大为6
    public Node(int thisNum, int[] fathers, int fathersNum){
        this.thisNum = thisNum;
        this.fathers = fathers;
        this.fathersNum = fathersNum;
    }
    public int contains(int son){
        //爹不算，爷爷开始才算
        for (int i=0;i<fathersNum-1;i++){
            if (fathers[i]==son)
                return i;
        }
        return -1;
    }
    //先尝试一下
    public void getCycle(int index){
        for (int i=index;i<fathersNum;i++){
            System.out.print(fathers[i]);
            System.out.print(" ");
        }
        System.out.print(thisNum);
        System.out.println();
    }
}