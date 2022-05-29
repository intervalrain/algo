package src.Topic.TreesAndGraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

public class BuildOrder2 {

    static class Project{
        public enum State {COMPLETE, PARTIAL, BLANK};

        private ArrayList<Project> children = new ArrayList<>();
        private String name;
        private State state = State.BLANK;

        public Project(String n){
            name = n;
        }
        public void addChildren(Project node){
            children.add(node);
        }
        public String getName(){
            return name;
        }
        public State getState(){
            return state;
        }
        public void setState(State st){
            state = st;
        }
        public ArrayList<Project> getChildren(){
            return children;
        }
        public void removeChildren(){
            children.clear();
        }
        @Override
        public String toString(){
            return getName() + ":" + getState();
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
            start.addChildren(end);
        }
        public ArrayList<Project> getNodes(){
            return nodes;
        }
    }

    Project[] findBuildOrder(String[] projects, String[][] dependencies){
        List<Project> order = buildOrder(projects, dependencies);
        int n = order.size();
        Project[] orderProject = new Project[n];
        for (int i = 0; i < n; i++){
            orderProject[i] = order.get(n-i-1);
        }
        return orderProject;
    }

    List<Project> buildOrder(String[] projects, String[][] dependencies){
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

    List<Project> orderProjects(ArrayList<Project> projects){
        List<Project> list = new ArrayList<>();
        for (int i = projects.size() - 1; i >= 0; i--){
            Project project = projects.get(i);
            if (project.getState() == Project.State.BLANK){
                dfs(project, list);
            }
        }
        return list;
    }

    void dfs(Project project, List<Project> list){
        if (project.getState() == Project.State.COMPLETE) return;

        if (project.getState() == Project.State.PARTIAL && project.getChildren().size() == 0){
            project.setState(Project.State.COMPLETE);
            list.add(project);
            return;
        }

        if (project.getState() == Project.State.BLANK){
            project.setState(Project.State.PARTIAL);
            ArrayList<Project> children = project.getChildren();
            if (children.size() > 0){
                for (Project child : children){
                    dfs(child, list);
                }
                project.removeChildren();   
            }
            dfs(project, list);
        }
    }

    public static void main(String[] args){
        BuildOrder2 clz = new BuildOrder2();

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

