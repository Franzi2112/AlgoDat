package Zettel12Franzi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectedGraph {
    List<Integer> Nodes;
    HashMap<Integer, List<Integer>> AL;

    public DirectedGraph(){
        Nodes = new ArrayList<>();
        AL = new HashMap<>();
    }

    public DirectedGraph(Integer n){
        Nodes = new ArrayList<>();
        AL = new HashMap<>();
        for(int i = 0; i < n; i++){
            Nodes.add(i+1);
        }
    }

    public void addVertex(Integer i){
        if(Nodes.contains(i)){
            return;
        } else {
            Nodes.add(i);
        }
    }

    public void addEdge(Integer i, Integer j){
        if (!AL.containsKey(i)){
            AL.put(i, new ArrayList<>());
            AL.get(i).add(j);
        } else {
            AL.get(i).add(j);
        }
    }

    public void deleteEdge(Integer i, Integer j){
        AL.get(i).remove(j);
    }

  //gibt keine Datein in dem Ordner lol


}
