package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

/**
 * 深度克隆图的dfs做法
 * 用一个HashMap来存储访问过的
 * 返回值都是Node
 */
public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    HashMap<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node==null){
            return null;
        }
        if (visited.containsKey(node)){
            return visited.get(node);
        }
        int val = node.val;
        Node cloneNode = new Node(val,new ArrayList<>());
        visited.put(node,cloneNode);
        for (Node neighbor : node.neighbors){
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }
}
