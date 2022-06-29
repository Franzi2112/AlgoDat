package Zettel11Bela;

import java.awt.*;
import java.util.*;

public class BfsIterator {
    private UndirectedGraph graph;                 //graph
    private HashMap<Integer, Color> color;         //Färbung: Weiß=nicht besucht, grau=discoverd(added to queue), schwarz=visited(all neighbouts discovered)
    private Queue<Integer> queue;                  //queue of nodes to be visited
    private HashMap<Integer, Integer> distance;    //distance of node from source
    private int start;                             //source node
    private boolean isDone;                        //true if all (connected) nodes have been visited


    public BfsIterator(UndirectedGraph graph, int start) {
        this.graph = graph;
        this.start = start;
        color = new HashMap<>();
        queue = new LinkedList<>();
        distance = new HashMap<>();
        isDone = false;
        for (int node: graph.nodes){
            color.put(node, Color.WHITE);
            distance.put(node, -1);
        }
    }


    public Integer next() {
        //first node to be visited
        if (color.get(start) == Color.WHITE){
            color.put(start, Color.GRAY);
            distance.put(start, 0);
            queue.add(start);
        }
        if (queue.isEmpty()){
            isDone = true;
            return null;
        }
        Integer activeNode = queue.remove();
        //if already visited, return next
        if (color.get(activeNode)==Color.BLACK){
            return next();
        }
        //else add neighbours to queue and mark as discovered
        for (Integer neighbour: graph.AL.get(activeNode)){
            if (color.get(neighbour)==Color.WHITE){
                color.put(neighbour, Color.GRAY);
                distance.put(neighbour, distance.get(activeNode)+1);
                queue.add(neighbour);
            }
        }
        //mark as visited & return node
        color.put(activeNode, Color.BLACK);
        return activeNode;

    }

    public Integer dist(Integer node) {
        if (isDone) {
            return distance.get(node);
        } else if (distance.get(node) == -1) {
            return null;
        } else {
            return distance.get(node);
        }

    }


    public static void main(String[] args) throws Exception {
        UndirectedGraph[] graphs = UndirectedGraph.export();

        for (UndirectedGraph graph: graphs){
            BfsIterator iterator = new BfsIterator(graph, graph.nodes.get(0));
            while (!iterator.isDone){
                System.out.println(iterator.next());
            }
            for (int node: graph.nodes){
                System.out.println(node + ": " + iterator.dist(node));
            }
        }


    }
}
