package Zettel11Franzi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class UndirectedGraph {
    List<Integer> Nodes;
    HashMap<Integer, List<Integer>> AL;

    public UndirectedGraph(){
        Nodes = new ArrayList<>();
        AL = new HashMap<>();
    }

    public UndirectedGraph(Integer n){
        Nodes = new ArrayList<>();
        AL = new HashMap<>();
        for(int i = 0; i < n; i++){
            Nodes.add(i+1);
        }
    }

    public void addVertex(Integer i){
        if( Nodes.contains(i)){
            return;
        } else {
            Nodes.add(i);
        }
    }

    public void addEdge(Integer i, Integer j){
        if (!AL.containsKey(i)){
            AL.put(i,new ArrayList<>());
            AL.get(i).add(j);
        } else {
            AL.get(i).add(j);
        }
        if (!AL.containsKey(j)){
            AL.put(j, new ArrayList<>());
            AL.get(j).add(i);
        } else {
            AL.get(j).add(i);
        }
    }

    public void deleteEdge(Integer i, Integer j){
        AL.get(i).remove(j);
        AL.get(j).remove(i);
    }

    public static UndirectedGraph[] export() throws Exception {
        BufferedReader zachary = new BufferedReader(new FileReader( "project/src/Zettel11Franzi/out.ucidata-zachary.sec"));
        BufferedReader twitter = new BufferedReader(new FileReader("project/src/Zettel11Franzi/soc-twitter-follows.txt"));

        FindMax maxZachary = new FindMax();
        FindMax maxTwitter = new FindMax();

        zachary.lines().forEach(maxZachary);
        twitter.lines().forEach(maxTwitter);

        UndirectedGraph zacharyGraph = new UndirectedGraph(maxZachary.max);
        UndirectedGraph twitterGraph = new UndirectedGraph(maxTwitter.max);

        while (zachary.ready()){
            String[] s2= zachary.readLine().split(" ");
            int[] i2={Integer.parseInt(s2[0]), Integer.parseInt(s2[1])};
            zacharyGraph.addEdge(i2[0],i2[1]);
        }

        while (twitter.ready()){
            String[] s2= twitter.readLine().split(" ");
            int[] i2={Integer.parseInt(s2[0]), Integer.parseInt(s2[1])};
            twitterGraph.addEdge(i2[0],i2[1]);
        }
        return new UndirectedGraph[]{zacharyGraph, twitterGraph};
    }
}

class FindMax implements Consumer<String> {
    int max = 0;

    @Override
    public void accept(String s) {
        Arrays.stream(s.split(" ")).map(s1->Integer.parseInt(s1)).forEach(s1->{if (s1>max){max=s1;}});
    }

}



