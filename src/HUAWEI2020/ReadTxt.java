package HUAWEI2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ReadTxt{
    public static HashMap<Integer, LinkedList<Integer>> getMap(String filePath){
//      String filePath = "E://华为软挑2020/test_data.txt";
//		String filePath = "smalldata.txt";
//		String filePath = "bigdata.txt";

//		初始化读写文件的对象
        FileReader fileReader = null;
        BufferedReader bufferReader = null;

//		用record记录每一行的字符串
        String record;
//		提取出字符串中的账号ID对，保存为int array
        int[] recordPair = new int[2];
//		用List保存所有转账对，后续的数据全部对List处，不再看文件啦
        List<int[]> recordList = new LinkedList<int[]>();
//		用于存储转账列和被转账列的数据集合
        HashSet<Integer> pairIn = new HashSet<Integer>();
        HashSet<Integer> pairOut = new HashSet<Integer>();
//		用于迭代操作
        HashSet<Integer> pairInBak = new HashSet<Integer>();
        HashSet<Integer> pairOutBak = new HashSet<Integer>();
//		构造数据结构用于合并进度出度分别为0的相邻点，分而治之
//        ConcurrentHashMap<Integer, HashMap<Integer, LinkedList<Integer>>> dotEdges = new ConcurrentHashMap<Integer, HashMap<Integer,LinkedList<Integer>>>();
//      adj
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();

        try {
//			文件读取类的初始化
            fileReader = new FileReader(filePath);
            bufferReader = new BufferedReader(fileReader);
            record = bufferReader.readLine();
//			循环读取文本中的记录，写入列表和集合，列表保存原始数据，集合分别统计转出账户和转入账户
            int i = 0;
            while(record != null) {
                for(i = 0; i < 2; i++) {
                    recordPair[i] = Integer.parseInt(record.split(",")[i]);

                }
                recordList.add(new int[] {recordPair[0],recordPair[1]});
                pairIn.add(recordPair[0]);
                pairOut.add(recordPair[1]);
                record = bufferReader.readLine();
            }

            System.out.println("边的数量为"+recordList.size());
//			System.out.println(pairIn.size());
//			System.out.println(pairOut.size());
            pairIn.retainAll(pairOut);
            System.out.println("点集合的数量"+pairIn.size());


//			删除进度或出度为0的点，这些点没有用
            int lastNumOfEdge = 280000;//记录上一轮删除还剩多少条边
            int numOfEdge;//记录这轮删完还剩多少条边，如果和上次相同，则不再进行删除
            i = 1;//记录删除 进度或出度为0的点 前后，总边数的变化，不变则说明已经删完。先初始化为一个非0的数
            int j = 0;//用来记录删除记录进行了多少次。

//			生成迭代器，进行迭代删边。
            Iterator<int[]> iterList;
            while(i!=0){
                iterList = recordList.iterator();
                int[] inOut;
                while(iterList.hasNext()) {
                    inOut = iterList.next();
                    if (!(pairIn.contains(inOut[0]) && pairIn.contains(inOut[1]))) {
                        iterList.remove();
                    }else {
                        pairInBak.add(inOut[0]);
                        pairOutBak.add(inOut[1]);
                    }
                }
                pairInBak.retainAll(pairOutBak);
                numOfEdge = recordList.size();
                //System.out.println("删了"+(j+1)+"遍之后，边的数量"+numOfEdge);
                //System.out.println("删了"+(j+1)+"遍之后，剩余点集合"+pairInBak.size());

                pairIn = pairInBak;
                pairInBak = new HashSet<Integer>();
                pairOutBak =new HashSet<Integer>();
                i = lastNumOfEdge -numOfEdge;
                lastNumOfEdge = numOfEdge;
                j++;
            }




//			——————————————————————————————————————————————————————————————————————————

//			此时的recordList中保存了删除后的转账记录，共有269个记录，共涉及到257个账户，
//			recordList的结构：List<int[]>，其中int[]为长度为2的数组。

//			下文若要使用，直接引用该变量即可

//			——————————————————————————————————————————————————————————————————————————





//			构建dotEdge映射
//            HashMap<Integer, LinkedList<Integer>> Zero = new HashMap<Integer, LinkedList<Integer>>();
//////			HashMap<Integer, LinkedList<Integer>> gottenMap;
////            HashMap<Integer, LinkedList<Integer>> temp;
            LinkedList<Integer> Zero = new LinkedList<>();
            LinkedList<Integer> temp = new LinkedList<>();

            int[] inOut;
            iterList = recordList.iterator();
            while(iterList.hasNext()) {
                inOut = iterList.next();
                if (map.containsKey(inOut[0])){
                    map.get(inOut[0]).add(inOut[1]);
                }
                else{
                    LinkedList<Integer> list = new LinkedList<>();
                    list.add(inOut[1]);
                    map.put(inOut[0],list);
                }
            }
            System.out.println("点数："+map.size());


//			————————————————————————————————————————————————————————————————————————————

//			此处在删减图的基础上构建了dotEdge结构（类似于adj）：
//			ConcurrentHashMap<Integer, HashMap<Integer, LinkedList<Integer>>>
//			并发map<源点，可到达点的map<可到达的点，列表（用于保存没有分支的一系列中间点）>>

//			以下代码用于显示dotEdge的数据：key为源点，destination为该点可到达的点。

//			————————————————————————————————————————————————————————————————————————————


//            System.out.println("dotedge's size is :" + dotEdges.size());
//            int key;
//            int subKey;
//            Iterator<Integer> iter = dotEdges.keySet().iterator();
//            Iterator<Integer> subIter;
//            HashMap<Integer, LinkedList<Integer>> en;
//            while(iter.hasNext()) {
//                key = iter.next();
//                en = dotEdges.get(key);
//                //System.out.println("the key:"+key);
//                subIter = en.keySet().iterator();
//                while(subIter.hasNext()) {
//                    subKey = subIter.next();
//                    //System.out.println(",the destination is:"+subKey);
//                }
//            }






        }
        catch(Exception ex) {
            System.out.println("lllllll");
            ex.printStackTrace();
        }
        finally {
            try {
                bufferReader.close();
            }
            catch(Exception ex) {
                ex.printStackTrace();
                System.out.println("dshafd");
            }
        }
        return map;
    }
    public static void main(String[] args){
        long startTime =  System.currentTimeMillis();
        HashMap<Integer,LinkedList<Integer>> map = getMap("E://华为软挑2020/test_data.txt");
        long endTime =  System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}
