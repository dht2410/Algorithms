package leetcode;

import java.util.*;

/**
 * LeetCode332  重新安排行程
 * 找到从起点出发，遍历整个图中所有点一次的路径。要求以字典序进行
 * 构造HashMap<String, PriorityQueue<String>> map，PriorityQueue而不少Set，是因为可能会有重复的
 * 深度优先搜索，遍历后就把边删除
 * 逆序插入，即遍历完成后才插入List，最后逆序List
 */
public class FindItinerary {
    HashMap<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> ret = new ArrayList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket:tickets){
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!map.containsKey(from)){
                PriorityQueue<String> set = new PriorityQueue<>();
                map.put(from,set);
            }
            map.get(from).add(to);
        }

        String to = "JFK";
        dfs(to);
        Collections.reverse(ret);
        return ret;
    }
    private void dfs(String str){
        while(map.containsKey(str) && map.get(str).size()>0){
            String to = map.get(str).poll();
            dfs(to);
        }
        ret.add(str);
    }
}
