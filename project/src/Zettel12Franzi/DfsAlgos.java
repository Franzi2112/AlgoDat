package Zettel12Franzi;

import Zettel11Bela.UndirectedGraph;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class DfsAlgos {
    private DirectedGraph graph;                 //graph
    private HashMap<Integer, Color> color;         //Färbung: Weiß=nicht besucht, grau=discoverd(added to queue), schwarz=visited(all neighbouts discovered)
    private Queue<Integer> queue;                  //queue of nodes to be visited
    private HashMap<Integer, Integer> discoverytime;    //time of discovery
    private HashMap<Integer, Integer> finishtime;
    private boolean isDone;


    public void Dfs(DirectedGraph graph, LinkedList<Integer> list, int size){
        this.graph = graph;
        color = new HashMap<>();
        queue = new LinkedList<>();
        discoverytime = new HashMap<>();
        finishtime = new HashMap<>();
        isDone = false;

        for (int node: graph.Nodes){
            color.put(node, Color.WHITE);
            size++;
        }
        int t = 0;

        for(int node : graph.Nodes){
            if (color.get(node) == Color.WHITE){
                DfsVisit(graph, node, t, list);
            }
        }
    }

    public void DfsVisit(DirectedGraph graph, int node, int t, LinkedList<Integer> list){
        color.put(node, Color.GRAY);
        t++;
        discoverytime.put(node, t);
        for (Integer neighbours : graph.AL.get(node)){
            if(color.get(neighbours) ==  Color.WHITE){
                DfsVisit(graph, neighbours, t , list);
            }
        }
        color.put(node, Color.BLACK);
        list.addFirst(node);
        t++;
        finishtime.put(node, t);
    }

    public LinkedList<Integer> topSort(DirectedGraph g) {
        LinkedList<Integer> L = new LinkedList<Integer>();
        int size = 0;
        Dfs(g, L, size);
        if (L.size() == size){
            return L;
        }
        return null;
    }

    /*public LinkedList<Integer> detectCycle(DirectedGraph g){

    }*/
}
