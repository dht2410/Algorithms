import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class MainV2 {
	
//	声明一个图结构
	static class graph{
		int[] dot;
		int[] color;
		int[] count;
		HashMap<Integer, Integer> map4constuctingOfgraph;
		ArrayList<Integer>[] destination;
		LinkedList<LinkedList<Integer>>[] chainSet;
		public graph(List<int[]> edge,Set<Integer> d) {
			System.out.println("size of edge:"+edge.size()+"\nsize of dot:"+d.size());
			dot = new int[d.size()];
			color = new int[d.size()];
			count = new int[d.size()];
			destination = new ArrayList[d.size()];
			chainSet = new LinkedList[d.size()];
			map4constuctingOfgraph = new HashMap<Integer, Integer>();
			int v;
			int[] inOut;
			int des;
			int cur = 0;
			Iterator<Integer> iterSet = d.iterator();
			while(iterSet.hasNext()) {
				v = iterSet.next();
				dot[cur] = v;
				map4constuctingOfgraph.put(v,cur);
				destination[cur] = new ArrayList<Integer>();
				cur++;
			}
			Iterator<int[]> iterList = edge.iterator();
			while(iterList.hasNext()) {
				inOut = iterList.next();
				cur = map4constuctingOfgraph.get(inOut[0]);
				des = map4constuctingOfgraph.get(inOut[1]);
				destination[cur].add(des);
			}
		}
	}

	public static void merge(int key, HashMap<Integer,LinkedList<Integer>> value, ConcurrentHashMap<Integer, HashMap<Integer, LinkedList<Integer>>> dotEdgeMap) {
		int ori,subOri;
		HashMap<Integer, LinkedList<Integer>> desOfDes;
		LinkedList<Integer> midDot,subMidDot;
		Iterator<Integer> iterDes = value.keySet().iterator();
		ori = iterDes.next();
		if(ori != key) {
			desOfDes = dotEdgeMap.get(ori);
			if(desOfDes!=null) {
				if(desOfDes.size()==1) {
					Iterator<Integer> iterDesOfDes = desOfDes.keySet().iterator();
					subOri = iterDesOfDes.next();
					midDot = value.getOrDefault(ori, new LinkedList<Integer>());
					midDot.add(ori);
					subMidDot = desOfDes.getOrDefault(subOri, new LinkedList<Integer>());
					midDot.addAll(subMidDot);

					value.clear();
					value.put(subOri,midDot);
					dotEdgeMap.remove(ori);
					merge(key, value, dotEdgeMap);
				}
			}
			
			
			
			
		}
		
	}
	
	public static void cycleInsert(LinkedList<Integer> cycle,LinkedList<LinkedList<Integer>> cycleList) {
		int minDot,cur;
//		String cycleString;
		LinkedList<Integer> tempChain = new LinkedList<Integer>();
		minDot = Collections.min(cycle);
		
//		一会还原
		
		Iterator<Integer> it = cycle.iterator();
		cur = it.next();
		while(cur != minDot) {
			it.remove();
			tempChain.add(cur);
			cur = it.next();
		}
		cycle.addAll(tempChain);
		
		
//		cycleString = Arrays.toString(cycle.toArray());
//		cycleString = cycleString.substring(1,cycleString.length()-1);
//		System.out.println(cycleString);
		cycleList.add(cycle);
	}

	public static void sort(LinkedList<LinkedList<Integer>> cycleList,LinkedList<String> cycleStrings){
		Comparator<LinkedList<Integer>> comp = new Comparator<LinkedList<Integer>>() {
			@Override
			public int compare(LinkedList<Integer> x,LinkedList<Integer> y) {
				int i,j;
				if((j = x.size()-y.size())!=0) {
					return j;
				}else{
					int size = x.size();
					
					for(i=0; i<size; i++) {
						if((j = x.get(i)-y.get(i))!=0) {
							return j;
						}
					}
					return 0;
				}
			}
		};
		String cyString;
		Collections.sort(cycleList, comp);
		for(LinkedList<Integer> item : cycleList) {
//			System.out.println(Arrays.toString(item.toArray()));
			cyString = Arrays.toString(item.toArray());
			cyString = cyString.substring(1,cyString.length()-1);
			cycleStrings.add(cyString);
			
		}
////		
	}
	
//	这里声明一些递归站可以共用的公共变量
	
	public static void dfs(graph g,int searchDot,int root,int dotExisting,int count,HashSet<Integer> father){
//		LinkedList<LinkedList<Integer>> chainSet = new LinkedList<LinkedList<Integer>>();
//		LinkedList<LinkedList<Integer>> subChainSet = new LinkedList<LinkedList<Integer>>();
		if(count>0) {
			LinkedList<Integer> chain = new LinkedList<Integer>();
			HashSet<Integer> me = new HashSet<Integer>();
			me.addAll(father);
			me.add(searchDot);
			LinkedList<Integer> chain4pass;
			g.color[searchDot] = 1;
			
			g.count[searchDot] = count;
//			System.out.println("第"+(8-count)+"层递归开始");
			ArrayList<Integer> destination = g.destination[searchDot];
			count--;
			for(int des : destination) {
//				System.out.println("前往子节点"+g.dot[des]+"");
				if(des<=dotExisting) {
					if(des == root) {
//						System.out.println("找到一个环，此时的根为"+g.dot[root]);
						if(g.chainSet[searchDot]==null) {
							g.chainSet[searchDot] = new LinkedList<LinkedList<Integer>>();
						}
						chain.add(0,g.dot[searchDot]);
						g.chainSet[searchDot].add(chain);
						continue;
//						chainSet.add(chain);
//						chain = new LinkedList<Integer>();
					}else {
						if(g.color[des] == 0) {
							dfs(g,des,root,dotExisting,count,me);
						}else if (me.contains(des)) {
							continue;
						}else if(count>g.count[des]){
							g.count[des] = count;
							if(g.chainSet[des] == null) {
								g.chainSet[des] = new LinkedList<LinkedList<Integer>>();
							}
							g.chainSet[des].clear();
							dfs(g,des,root,dotExisting,count,me);
						}
						if(g.chainSet[des] != null) {
//							System.out.println(""+g.dot[searchDot]+"开始接收子链,子链的条目有："+subChainSet.size());
							for(LinkedList<Integer> subChain:g.chainSet[des]) {
								if(count-subChain.size()>=0) {
									chain4pass = new LinkedList<Integer>();
									chain4pass.add(0,g.dot[searchDot]);
									chain4pass.addAll(subChain);
									if(g.chainSet[searchDot]==null) {
										g.chainSet[searchDot] = new LinkedList<LinkedList<Integer>>();
									}
									g.chainSet[searchDot].add(chain4pass);
//									chainSet.add(subChain);
								}
								
							}
						}
					}
				}
				
			}
		}
//		return chainSet;
	}
	
	
	public static void main(String[] args){
		
		
//		String filePath = "test_data.txt";
//		String filePath = "mate_data.txt";
		String filePath = "E://华为软挑2020/testdatabyme/cycle8.txt";
//		String filePath = "smalldata.txt";
//		String filePath = "bigdata.txt";
//		初始化写入文件名称及路径
		String fileNameString = "myresult.txt";
		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;
		
		
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
//		ConcurrentHashMap<Integer, HashMap<Integer, LinkedList<Integer>>> dotEdges = new ConcurrentHashMap<Integer, HashMap<Integer,LinkedList<Integer>>>();
//		以hashset保存父节点
//		HashSet<Integer> fatherSet = new HashSet<Integer>();

//		声明一个list集合，用来保存环的中间结果
		LinkedList<LinkedList<Integer>> cycleSet;
//		声明一个list结构用来保存最终的环
		LinkedList<LinkedList<Integer>> cycleList = new LinkedList<LinkedList<Integer>>();
		LinkedList<String> cycleStrings = new LinkedList<String>();
		
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
//				System.out.println("删了"+(j+1)+"遍之后，边的数量"+numOfEdge);
//				System.out.println("删了"+(j+1)+"遍之后，剩余点集合"+pairInBak.size());
				pairIn = pairInBak;
				pairInBak = new HashSet<Integer>();
				pairOutBak =new HashSet<Integer>();
				i = lastNumOfEdge -numOfEdge;
				lastNumOfEdge = numOfEdge;
				j++;
			}
			
			graph G = new graph(recordList, pairIn);
			System.out.println("graph is done");
			
//			int jj = 0;
//			for(ArrayList<Integer> ll : G.destination) {
//				System.out.print(""+G.dot[jj]+":");
//				for(int item:ll) {
//					System.out.print(""+G.dot[item]+",");
//				}
//				jj++;
//				System.out.println();	
//			}
			
			int k;
			int dotExisted = G.dot.length-1;
			for(k = G.dot.length-1;k>=0;k--) {
//				System.out.println("第"+(k+1)+"个元素已经开始");
//				System.out.println("搜索的子节点在前"+(dotExisted+1)+"个元素中");
				dfs(G,k,k,dotExisted,7,new HashSet<Integer>());
				cycleSet = G.chainSet[k];
				dotExisted--;
				if(cycleSet!=null) {
					for(LinkedList<Integer> cycle:cycleSet) {
						if(cycle.size()>2) {
							cycleInsert(cycle, cycleList);
						}
					}
				
					
				}
				G.color = new int[G.dot.length];
				G.chainSet = new LinkedList[G.dot.length];
			}
			System.out.println("cycleFind is done. and the size is:"+cycleList.size());
			sort(cycleList,cycleStrings);
//			for(String item :cycleStrings) {
//				System.out.println(item);
//			}
			
//			HashSet<String> test = new HashSet<String>();
//			Iterator<String> ir = cycleStrings.iterator();
//			String t;
//			while(ir.hasNext()) {
//				t = ir.next();
//				test.add(t);
//			}
//			System.out.println(test.size());
			
			try {
				fileWriter = new FileWriter(fileNameString);
				bufferWriter = new BufferedWriter(fileWriter);
				System.out.println("start2write");
				bufferWriter.write(String.valueOf(cycleStrings.size()));
				System.out.println(cycleStrings.size());
				bufferWriter.newLine();
				for(String item:cycleStrings) {
					bufferWriter.write(item);
					bufferWriter.newLine();
				}

				System.out.println("finished");
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					bufferWriter.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
//			________________________________________________________________
			
			
//			接下来进行无分支边的连接
			
//			_________________________________________________________________
			
////			构建dotEdge映射
//			HashMap<Integer, LinkedList<Integer>> Zero = new HashMap<Integer, LinkedList<Integer>>();
////			HashMap<Integer, LinkedList<Integer>> gottenMap;
//			HashMap<Integer, LinkedList<Integer>> temp;
////			
//			int[] inOut;
//			iterList = recordList.iterator();
//			while(iterList.hasNext()) {
//				inOut = iterList.next();
//				temp = dotEdges.getOrDefault(inOut[0], Zero);
//				temp.put(inOut[1],new LinkedList<Integer>());
//				dotEdges.put(inOut[0],temp);
//				Zero = new HashMap<Integer, LinkedList<Integer>>();
//			}
//			
//			System.out.println("dotedge's size is :" + dotEdges.size());
////			
			
//			Iterator<Integer> iterMap = dotEdges.keySet().iterator();
//			int origin;
//			HashMap<Integer, LinkedList<Integer>> destination;
//			while(iterMap.hasNext()) {
//				origin = iterMap.next();
//				destination = dotEdges.get(origin);
//				if(destination != null) {
//					if(destination.size() == 1) {
//						merge(origin, destination, dotEdges);
//					}
//				}
//			}
//			System.out.println("after link the dotEdge's size is :" + dotEdges.size());
//			
//			Iterator<Integer> iterDotEdge = dotEdges.keySet().iterator();
//			Iterator<Integer> desIterator;
//			int oriDot,desDot;
//			HashMap<Integer, LinkedList<Integer>> desMap;
//			LinkedList<Integer> cycle = new LinkedList<Integer>();
//			while(iterDotEdge.hasNext()) {
//				oriDot = iterDotEdge.next();
//				desMap = dotEdges.get(oriDot);
//				desIterator = desMap.keySet().iterator();
//				desDot = desIterator.next();
//				if(oriDot == desDot) {
//					if(desMap.get(desDot).size()<7 && desMap.get(desDot).size()>1) {
//						cycle.add(oriDot);
//						cycle.addAll(desMap.get(desDot));
//						cycleInsert(cycle, cycleList);
//						cycle.clear();
//					}
//					iterDotEdge.remove();//删除环
//				}
//			}
//			
//			
//			System.out.println("after cycle deleted the dotEdge's size is :" + dotEdges.size());
			
			
			
			
//			用于数据显示
//			int key;
//			int subKey;
//			Iterator<Integer> iter = dotEdges.keySet().iterator();
//			Iterator<Integer> subIter;
//			HashMap<Integer, LinkedList<Integer>> en;
//			while(iter.hasNext()) {
//				key = iter.next();
//				en = dotEdges.get(key);
//				System.out.println("the key:"+key);
//				subIter = en.keySet().iterator();
//				while(subIter.hasNext()) {
//					subKey = subIter.next();
//					System.out.println(",the destination is:"+subKey);
//				}
//			}
			
			
			
			
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
		
	}

}
