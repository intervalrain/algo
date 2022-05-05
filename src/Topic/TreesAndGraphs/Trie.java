package src.Topic.TreesAndGraphs;


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Trie {
    
    static final int DEFAULT_ENTRY_NUM = 26;
    TrieNode root;
    Set<String> dp = new HashSet<>();
    
    public class TrieNode{
        TrieNode[] entry;
        char c;
        boolean end;

        public TrieNode(char c){
            this.entry = new TrieNode[DEFAULT_ENTRY_NUM];
            this.c = c;
            this.end = false;
        }
    }

    public Trie(List<String> wordDict){
        root = new TrieNode('-');
        for (String s : wordDict){
            add(s);
        }
    }

    public void add(String s){
        TrieNode curr = root;
        for (char c : s.toCharArray()){
            int ix = c - 'a';
            if (curr.entry[ix] == null) curr.entry[ix] = new TrieNode(c);
            curr = curr.entry[ix];
        }
        curr.end = true;
    }

    @Override
    public String toString(){
        if (root == null) return "";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < DEFAULT_ENTRY_NUM; i++){
            if (root.entry[i] != null) traverse(root.entry[i], "", list);
        }
        return Arrays.toString(list.toArray());
    }

    private void traverse(TrieNode node, String s,List<String> list){
        if (node.end){
            list.add(s + node.c);
        }
        for (int i = 0; i < DEFAULT_ENTRY_NUM; i++){
            if (node.entry[i] != null) traverse(node.entry[i], s + node.c, list);
        }
    }

    
    public boolean contains(String s){
        if (dp.contains(s)) return false;
        TrieNode curr = root;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (curr.end){
                if (contains(s.substring(i))) return true;
            }
            if (curr.entry[c - 'a'] != null){
                curr = curr.entry[c - 'a'];
            } else {
                dp.add(s);
                return false;
            }
        }
        return curr.end;
    }

    public static boolean wordBreak(String s, List<String> wordDict){
        Trie trie = new Trie(wordDict);
        return trie.contains(s);
    }

    public static void main(String[] args){
        String s = "aaaaaaa";
        List<String> wordDict = Arrays.asList("aa", "aaaa");
        System.out.println(wordBreak(s, wordDict));
    }
}