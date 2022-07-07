package Zettel12Bela;

import java.util.LinkedList;

public class DfsAlogs {


    //find any topological order of the graph, else return null
    public LinkedList<Integer>[] topSort(DirectedGraph g) {
        LinkedList<Integer>[] res = new LinkedList[g.nodes.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = new LinkedList<>();
        }
        boolean[] visited = new boolean[g.nodes.size()];
        for (int i = 0; i < g.nodes.size(); i++) {
            if (!visited[i]) {
                dfs(g, i, visited, res);
            }
        }
        return res;
    }

    private void dfs(DirectedGraph g, int i, boolean[] visited, LinkedList<Integer>[] res) {
        visited[i] = true;
        for (int j = 0; j < g.AL.get(i).size(); j++) {
            if (!visited[g.AL.get(i).get(j)]) {
                dfs(g, g.AL.get(i).get(j), visited, res);
            }
        }
        res[i].addFirst(i);
    }


    //detect if the graph has a cycle and return it, else return null
    public LinkedList<Integer>[] detectCycle(DirectedGraph g) {
        LinkedList<Integer>[] res = new LinkedList[g.nodes.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = new LinkedList<>();
        }
        boolean[] visited = new boolean[g.nodes.size()];
        for (int i = 0; i < g.nodes.size(); i++) {
            if (!visited[i]) {
                if (dfsDetectCycle(g, i, visited, res)) {
                    return res;
                }
            }
        }
        return null;
    }

    private boolean dfsDetectCycle(DirectedGraph g, int i, boolean[] visited, LinkedList<Integer>[] res) {
        visited[i] = true;
        for (int j = 0; j < g.AL.get(i).size(); j++) {
            if (visited[g.AL.get(i).get(j)]) {
                res[i].addFirst(i);
                res[g.AL.get(i).get(j)].addFirst(g.AL.get(i).get(j));
                return true;
            } else {
                if (dfsDetectCycle(g, g.AL.get(i).get(j), visited, res)) {
                    res[i].addFirst(i);
                    res[g.AL.get(i).get(j)].addFirst(g.AL.get(i).get(j));
                    return true;
                }
            }
        }
        return false;
    }
}
