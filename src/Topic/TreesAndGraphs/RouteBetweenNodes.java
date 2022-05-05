package src.Topic.TreesAndGraphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Route between Nodes: Given a directed graph and two nodes(s and e),
 *  design algorithm to find out whether there is a route from s to e.
 */

public class RouteBetweenNodes {

    static class Graph{
        static final int NUMS_OF_NODES = 26;
        Node[] nodes;
        Node[] getNodes(){
            return nodes;
        }
        Graph(){
            nodes = new Node[NUMS_OF_NODES];
        }
        Graph(String[] strs){
            this();
            for (String s : strs) add(s);
        }
        Graph(String s){
            this();
            add(s);
        }
        Node newNode(char c){
            Node node = nodes[c-'a'];
            node = new Node(c);
            node.children = new ArrayList<>();
            node.state = State.Unvisited;
            return node;
        }
        void add(String s){
            for (int i = 1; i < s.length(); i++){
                if (s.charAt(i-1) == s.charAt(i)) continue;
                if (nodes[s.charAt(i-1)-'a'] == null) nodes[s.charAt(i-1)-'a'] = newNode(s.charAt(i-1));
                if (nodes[s.charAt(i)-'a'] == null) nodes[s.charAt(i)-'a'] = newNode(s.charAt(i));
                Node a = nodes[s.charAt(i-1)-'a'];
                Node b = nodes[s.charAt(i)-'a'];
                if (a == null) a = newNode(s.charAt(i-1));
                if (b == null) b = newNode(s.charAt(i));
                if (!a.children.contains(b)) a.children.add(b);
                if (!b.children.contains(a)) b.children.add(a);
            }
        }
        void reset(){
            for (Node node : nodes){
                if (node != null) node.state = State.Unvisited;
            }
        }
    }
    enum State { Unvisited, Visited, Visiting; }

    static class Node{
        State state;
        char name;
        List<Node> children;

        Node(char c){
            this.name = c;
        }

        List<Node> getAdjacentNodes(){
            return children;
        }

        @Override
        public String toString(){
            String s = "";
            if (state == State.Unvisited){
                s = "[x]";
            } else if (state == State.Visiting){
                s = "[.]";
            } else if (state == State.Visited){
                s = "[v]";
            }
            return "" + name + s;
        }
    }

    public static boolean search(Graph g, Node s, Node e){
        if (s == e) return true;
        Queue<Node> q = new ArrayDeque<Node>();
        s.state = State.Visiting;
        q.add(s);
        Node curr;
        while (!q.isEmpty()){
            curr = q.poll();
            if (curr != null){
                for (Node node : curr.getAdjacentNodes()){
                    if (node == e){
                        g.reset();
                        return true;
                    } else if (node.state == State.Unvisited){
                        node.state = State.Visiting;
                        q.add(node);
                    }
                }
            }
            curr.state = State.Visited;
        }
        g.reset();
        return false;
    }

    public static boolean search2(Graph g, Node s, Node e){
        boolean[] dp = new boolean[26];
        traverse(s, dp);
        return dp[e.name-'a'];
    }

    private static void traverse(Node node, boolean[] dp){
        int ix = node.name - 'a';
        if (dp[ix] == true) return;
        dp[ix] = true;
        for (Node child: node.children)
            traverse(child, dp);
    }

    int[] group = new int[26];
    boolean grouped = false;
    private void grouping(Graph g){
        grouped = true;
        int color = 1;
        for (int i = 0; i < 26; i++){
            if (g.nodes[i] == null){
                group[i] = -1;
            } else if (group[i] == 0){
                paint(g.nodes[i], color++);
            }
        }
    }
    private void paint(Node node, int color){
        if (group[node.name-'a'] == color) return;
        group[node.name-'a'] = color;
        for (Node child : node.children){
            paint(child, color);
        }
    }

    public boolean search3(Graph g, Node s, Node e){
        if (!grouped) grouping(g);
        return group[s.name-'a'] == group[e.name-'a'];
    }

    public static void main(String[] args){
        Graph g = new Graph(new String[]{"apple", "bird", "cat", "zoo", "pen", "ring"});
        Node a = g.nodes['a'-'a'];
        Node b = g.nodes['b'-'a'];
        Node z = g.nodes['z'-'a'];
        RouteBetweenNodes clz = new RouteBetweenNodes();
        System.out.println(clz.search3(g, a, b));
        System.out.println(clz.search3(g, a, z));
        System.out.println(clz.search3(g, b, z));
    }

}
