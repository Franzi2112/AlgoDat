package Zettel12Franzi;

import Zettel11Franzi.UndirectedGraph;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class DirectedGraph {
    List<Integer> Nodes;
    HashMap<Integer, List<Integer>> AL;

    public DirectedGraph() {
        Nodes = new ArrayList<>();
        AL = new HashMap<>();
    }

    public DirectedGraph(Integer n) {
        Nodes = new ArrayList<>();
        AL = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Nodes.add(i + 1);
        }
    }

    public void addVertex(Integer i) {
        if (Nodes.contains(i)) {
            return;
        } else {
            Nodes.add(i);
        }
    }

    public void addEdge(Integer i, Integer j) {
        if (!AL.containsKey(i)) {
            AL.put(i, new ArrayList<>());
            AL.get(i).add(j);
        } else {
            AL.get(i).add(j);
        }
    }

    public void deleteEdge(Integer i, Integer j) {
        AL.get(i).remove(j);
    }

    public static DirectedGraph[] fileToGraph(String filename) throws Exception {
        DirectedGraph Graph = new DirectedGraph();
        List<String> listOfStrings = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(new FileReader(filename));
        String line = bf.readLine();
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }
        bf.close();
        String[] array = listOfStrings.toArray(new String[0]);
        for (String str : array) {
            int[] alr = Arrays.stream(str.split(" ")).flatMapToInt(x -> IntStream.of(Integer.parseInt(x))).toArray();
            Graph.addEdge(alr[0], alr[1]);
        }
        return new DirectedGraph[]{Graph};
    }


    public static void main(String[] args) throws Exception {
        DirectedGraph[] outbnet = fileToGraph("project/src/Zettel12Franzi/outbnet.sec");
        DirectedGraph[] outmaayan = fileToGraph("project/src/Zettel12Franzi/outmaayan-figeys.sec");
        DirectedGraph[] outmorenoDense = fileToGraph("project/src/Zettel12Franzi/outmoreno_dense_comm.sec");
        DirectedGraph[] outmorenoTaro = fileToGraph("project/src/Zettel12Franzi/outmoreno_taro_taro.sec");
        DirectedGraph[] outsimple = fileToGraph("project/src/Zettel12Franzi/outsimple.sec");

    }
}



