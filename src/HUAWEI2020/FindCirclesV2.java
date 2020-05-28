package HUAWEI2020;

import java.util.*;


public class FindCirclesV2 {
    public static void main(String[] args){
        //开始计时
        long startTime = System.currentTimeMillis();
        //广度优先搜索队列
        LinkedList<Node> nodes = new LinkedList<>();
        //根据test文件构建有向图
        String filePath = "E://华为软挑2020/HWcode2020-TestData-master/testData/test_data.txt";
        HashMap<Integer,LinkedList<Integer>> map = ReadTxt.getMap(filePath);
        DiGraph G = new DiGraph(map);
        //marked标记，针对不连通图
        int[] markedIndex = new int[map.size()];
        int indexOfNode = 0;
        for (int v:map.keySet()){
            markedIndex[indexOfNode++] = v;
        }
        boolean[] marked = new boolean[map.size()];

        //环的数量
        int circleNumber = 0;
        //记录边数分别为3-7的环的数量
        int[] testcircles = new int[7];

        /*
        * 以下是逻辑处理部分
        * */
        for (int i=0;i<map.size();i++){
            if(!marked[i]){
                //选取的第一个点
                Node thisNode = new Node(markedIndex[i],new ArrayList<>(),0);
                nodes.add(thisNode);
                //当队列非空，进行广度优先搜索操作
                while (!nodes.isEmpty()){
                    //首节点出队
                    Node removedNode = nodes.remove();
                    //下面三个分别是节点对象的三个字段
                    int removedThisNum = removedNode.thisNum;
                    ArrayList<Integer> removedFathers = removedNode.fathers;
                    int removedFatherNum = removedNode.fathersNum;
                    //检查有没有环
                    for (int v: G.adj(removedThisNum)){
                        int index = removedNode.contains(v);
                        //子节点v不存在于父节点，则构建节点对象并加入nodes数组，同时标记
                        if(index==-1){

                            ArrayList<Integer> newFathers = new ArrayList<>(removedFathers);
                            newFathers.add(removedThisNum);
                            //int newFatherNum = removedFatherNum == maxFathers ? maxFathers : removedFatherNum + 1;
                            nodes.add(new Node(v, newFathers, removedFatherNum+1));

                            marked[indexOf(v,markedIndex)] = true;

                        }
                        //子节点v存在于父节点并满足边长为3-7的条件
                        else if (removedFatherNum-index<=6&&removedFatherNum-index>=2){
                            //removedNode.getCycle(index);
                            circleNumber++;
                            testcircles[removedFatherNum-index]++;
                        }
                    }
                }
            }
        }
        System.out.println("成环数量:"+circleNumber);
//        for (int i=1;i<=6;i++){
//            System.out.println(i+1+":"+testcircles[i]);
//        }
        long endTime = System.currentTimeMillis();
        System.out.println("总用时:"+(endTime-startTime)+"ms");

    }

    //该方法用于标记，将markedIndex对应到marked数组
    private static int indexOf(int v, int[] markedIndex){
        int i = 0;
        while(markedIndex[i]!=v){
            i++;
        }
        return i;
    }

//    //获得祖辈节点数组，之前用的方法，现在没用了
//    private static int[] getNewFathers(int[] Fathers, int fathersNum, int newNodeNum,int size){
//        //这里有硬编码，待改
////        int[] newFathers = new int[6];
////        if (fathersNum==6){
////            for(int i=1;i<6;i++){
////                newFathers[i-1] = Fathers[i];
////            }
////            newFathers[5] = newNodeNum;
////            return newFathers;
////        }
////        else{
////            for(int i=0;i<fathersNum;i++){
////                newFathers[i]=Fathers[i];
////            }
////            newFathers[fathersNum] = newNodeNum;
////            return  newFathers;
////        }
//        int[] newFathers = new int[size];
//        for (int i=0;i<fathersNum;i++)
//            newFathers[i]=Fathers[i];
//        newFathers[fathersNum]=newNodeNum;
//        return newFathers;
//    }

}

//节点类
class Node{
    int thisNum;    //该节点对应的数
    ArrayList<Integer> fathers;    //该节点的父亲节点们
    int fathersNum;      //该节点的父亲节点数量
    public Node(int thisNum, ArrayList<Integer> fathers, int fathersNum){
        this.thisNum = thisNum;
        this.fathers = fathers;
        this.fathersNum = fathersNum;
    }
    //该节点的子节点是否存在于父亲节点中，存在则返回所在index，不存在则返回-1
    public int contains(int son){
        return fathers.indexOf(son);
    }

    //先尝试一下
//    public void getCycle(int index){
//        for (int i=index;i<fathersNum;i++){
//            System.out.print(fathers[i]);
//            System.out.print(" ");
//        }
//        System.out.print(thisNum);
//        System.out.println();
//    }
}

