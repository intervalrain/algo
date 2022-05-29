package src.Topic.TreesAndGraphs;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Build Order
 * 
 * You are given a list of projects and a list of dependencies (which is a list of pairs of projects, 
 * where the second project is dependent on the first project.)
 * All of a project's dependencies must be built before the project is.
 * Find a build order that will allow the projects to be built.
 * If there is no valid build order, return an error.
 */

public class BuildOrder {

    static class Project{
        private ArrayList<Project> children = new ArrayList<>();
        private HashMap<String, Project> map = new HashMap<>();
        private String name;
        private int depedencies = 0;

        public Project(String n){
            name = n;
        }

        public void addNeighbor(Project node){
            children.add(node);
            map.put(node.getName(), node);
            node.incrementDependencies();
        }
        public void incrementDependencies(){
            depedencies++;
        }
        public void decrementDependencies(){
            depedencies--;
        }
        public String getName(){
            return name;
        }
        public ArrayList<Project> getChildren(){
            return children;
        }
        public int getNumberDependencies(){
            return depedencies;
        }
        @Override
        public String toString(){
            return getName() + ":" + getNumberDependencies();
        }
    }

    static class Graph{
        private ArrayList<Project> nodes = new ArrayList<>();
        private HashMap<String, Project> map = new HashMap<>();

        public Project getOrCreateNode(String name){
            if (!map.containsKey(name)){
                Project node = new Project(name);
                nodes.add(node);
                map.put(name, node);
            }
            return map.get(name);
        }
        public void addEdge(String startNode, String endNode){
            Project start = getOrCreateNode(startNode);
            Project end = getOrCreateNode(endNode);
            start.addNeighbor(end);
        }
        public ArrayList<Project> getNodes(){
            return nodes;
        }
    }

    Project[] findBuildOrder(String[] projects, String[][] dependencies){
        Graph graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }

    Graph buildGraph(String[] projects, String[][] dependencies){
        Graph graph = new Graph();
        Set<String> set = new HashSet<>();
        for (String[] d : dependencies){
            String first = d[0];
            String second = d[1];
            graph.addEdge(first, second);
            set.add(first);
            set.add(second);
        }
        for (String p : projects){
            if (set.contains(p)) continue;
            graph.getOrCreateNode(p);
        }
        return graph;
    }

    Project[] orderProjects(ArrayList<Project> projects){
        Project[] order = new Project[projects.size()];
        int endOfList = addNondepedent(order, projects, 0);
        int toBeProcessed = 0;
        while (toBeProcessed < order.length-1){
            Project current = order[toBeProcessed];
            if (current == null){
                return null;
            }
            ArrayList<Project> children = current.getChildren();
            for (Project child : children){
                child.decrementDependencies();;
            }

            endOfList = addNondepedent(order, projects, endOfList);
            toBeProcessed++;
        }
        return order;
    }

    int addNondepedent(Project[] order, ArrayList<Project> projects, int offset){
        for (Project project : projects){
            if (project.getNumberDependencies() == 0){
                order[offset] = project;
                offset++;
            }
        }
        for (int i = 0; i < offset; i++){
            projects.remove(order[i]);
        }
        return offset;
    }

    public static void main(String[] args){
        BuildOrder clz = new BuildOrder();

        String[] projects = new String[]{"a", "b", "c", "d", "e", "f"};
        String[][] dependencies = new String[][]{{"a","d"},{"f","b"},{"b","d"},{"f","a"},{"d","c"}};
        // expected: f->e->a->b->d->c
        Project[] result = clz.findBuildOrder(projects, dependencies); 
        StringBuilder sb = new StringBuilder();
        for (Project e : result){
            sb.append(e.getName());
            sb.append("->");
        }
        System.out.println(sb.toString().substring(0, sb.length()-2));
    }
}

