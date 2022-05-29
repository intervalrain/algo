package src.Topic.TreesAndGraphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Build Order
 * 
 * You are given a list of projects and a list of dependencies (which is a list of pairs of projects, 
 * where the second project is dependent on the first project.)
 * All of a project's dependencies must be built before the project is.
 * Find a build order that will allow the projects to be built.
 * If there is no valid build order, return an error.
 */

public class MyBuildOrder {
    @SuppressWarnings("unchecked")
    public String[] findBuildOrder(String[] projects, String[][] dependencies){
        int n = projects.length;
        int[] parentNo = new int[n];
        String[] res = new String[n];
        ArrayList<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        ArrayList<String>[] children = new ArrayList[n];
        int cnt = 0;
        for (String[] d : dependencies){
            String first = d[0];
            String second = d[1];
            int parent, child;
            if (!map.containsKey(first)){
                parent = cnt;
                list.add(first);
                map.put(first, parent);
                children[parent] = new ArrayList<>();
                cnt++;
            } else {
                parent = map.get(first);
            }
            if (!map.containsKey(second)){
                child = cnt;
                list.add(second);
                map.put(second, child);
                children[child] = new ArrayList<>();
                cnt++;
            } else {
                child = map.get(second);
            }

            children[parent].add(list.get(child));
            parentNo[child]++;
        }
        for (String project : projects){
            if (map.containsKey(project)) continue;
            int curr = cnt++;
            list.add(project);
            map.put(project, curr);
            children[curr] = new ArrayList<>();
        }
        traverse(list, map, children, parentNo, res, 0);

        return res;
    }
    private void traverse(ArrayList<String> list, Map<String, Integer> map, ArrayList<String>[] children, int[] parentNo, String[] res, int idx){
        int n = list.size();
        if (idx == n) return;
        Deque<String> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            if (parentNo[i] == 0){
                String word = list.get(i);
                res[idx++] = word;
                parentNo[i]--;
                dq.offer(word);
            }
        }
        while (!dq.isEmpty()){
            String word = dq.poll();
            int ix = map.get(word);
            for (String child : children[ix]){
                int childx = map.get(child);
                parentNo[childx]--;
            }
        }
        traverse(list, map, children, parentNo, res, idx);
    }
    
    public static void main(String[] args){
        MyBuildOrder clz = new MyBuildOrder();

        String[] projects = new String[]{"a", "b", "c", "d", "e", "f"};
        String[][] dependencies = new String[][]{{"a","d"},{"f","b"},{"b","d"},{"f","a"},{"d","c"}};
        // expected: f->e->a->b->d->c
        String[] result = clz.findBuildOrder(projects, dependencies); 
        StringBuilder sb = new StringBuilder();
        for (String e : result){
            sb.append(e);
            sb.append("->");
        }
        System.out.println(sb.toString().substring(0, sb.length()-2));
    }
}
