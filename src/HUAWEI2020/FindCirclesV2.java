package HUAWEI2020;

import java.util.*;


public class FindCirclesV2 {
    public static void main(String[] args){
        //int V = 10;  //节点
        long startTime = System.currentTimeMillis();
        final int maxFathers = 300;  //最多往上数6辈
        LinkedList<Node> nodes = new LinkedList<>(); //队列
        //有向图
        HashMap<Integer,LinkedList<Integer>> map = ReadTxt.getMap();
        DiGraph G = new DiGraph(map);
        //marked标记
        int size = map.size();
        int[] markedIndex = new int[map.size()];
        int indexOfNode = 0;
        for (int v:map.keySet()){
            markedIndex[indexOfNode++] = v;
        }
        boolean[] marked = new boolean[map.size()];

        //环的数量
        int circleNumber = 0;

        int[] testcircles = new int[7];

        for (int i=0;i<map.size();i++){
            if(!marked[i]){
                Node thisNode = new Node(markedIndex[i],new int[maxFathers],0);
                nodes.add(thisNode);
                //marked[i]=true;  //标记被访问过
                while (!nodes.isEmpty()){
                    Node removedNode = nodes.remove();
                    int removedThisNum = removedNode.thisNum;
                    int[] removedFathers = removedNode.fathers;
                    int removedFatherNum = removedNode.fathersNum;
                    //检查有没有环
                    for (int v: G.adj(removedThisNum)){
                        int index = removedNode.contains(v);
                        if(index==-1){

                            int[] newFathers = getNewFathers(removedFathers, removedFatherNum, removedThisNum,size);
                            //int newFatherNum = removedFatherNum == maxFathers ? maxFathers : removedFatherNum + 1;
                            nodes.add(new Node(v, newFathers, removedFatherNum+1));

                            marked[indexOf(v,markedIndex)] = true;

                        }
                        else if (removedFatherNum-index<=6&&removedFatherNum-index>=2){
                            removedNode.getCycle(index);
                            circleNumber++;
                            //testcircles[removedFatherNum-index]++;
                        }
                    }
                }
            }
        }
        System.out.println("circleNumber:"+circleNumber);
//        for (int i=2;i<=6;i++){
//            System.out.println(i+1+":"+testcircles[i]);
//        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);

    }

    private static int indexOf(int v, int[] markedIndex){
        int i = 0;
        while(markedIndex[i]!=v){
            i++;
        }
        return i;
    }
    //建一个有向图测试
//    private static DiGraph constructExample(HashMap<Integer,LinkedList<Integer>>){
//        DiGraph G = new DiGraph(V);
//        for (int i=0;i<6;i++){
//            G.addEdge(i,i+1);
//        }
//        G.addEdge(6,0);
//        G.addEdge(6,4);
//        G.addEdge(4,7);
//        G.addEdge(7,8);
//        G.addEdge(8,9);
//        G.addEdge(9,6);
//        G.addEdge(6,3);
//        return G;
//    }

    //获得祖辈节点数组
    private static int[] getNewFathers(int[] Fathers, int fathersNum, int newNodeNum,int size){
        //这里有硬编码，待改
//        int[] newFathers = new int[6];
//        if (fathersNum==6){
//            for(int i=1;i<6;i++){
//                newFathers[i-1] = Fathers[i];
//            }
//            newFathers[5] = newNodeNum;
//            return newFathers;
//        }
//        else{
//            for(int i=0;i<fathersNum;i++){
//                newFathers[i]=Fathers[i];
//            }
//            newFathers[fathersNum] = newNodeNum;
//            return  newFathers;
//        }
        int[] newFathers = new int[size];
        for (int i=0;i<fathersNum;i++)
            newFathers[i]=Fathers[i];
        newFathers[fathersNum]=newNodeNum;
        return newFathers;
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

