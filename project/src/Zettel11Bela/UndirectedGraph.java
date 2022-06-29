package Zettel11Bela;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class UndirectedGraph {
    List<Integer> nodes;
    HashMap<Integer,List<Integer>> AL;

    public UndirectedGraph(){
        nodes= new ArrayList<>();
        AL= new HashMap<>();
    }
    public UndirectedGraph(Integer i){
        nodes= new ArrayList<>();
        AL= new HashMap<>();
        for (int j =1;j<=i;j++){
            nodes.add(j);
        }
    }

    public void addVertex(Integer i){
        if (nodes.contains(i)){
            return;
        } else {
            nodes.add(i);
        }
    }

    public void addEdge(Integer i, Integer j) {
        if (!AL.containsKey(i)){
            AL.put(i,new ArrayList<>());
            AL.get(i).add(j);
        } else {
            AL.get(i).add(j);
        }
        if (!AL.containsKey(j)){
            AL.put(j,new ArrayList<>());
            AL.get(j).add(i);
        } else {
            AL.get(j).add(i);
        }
    }

    public void deleteEdge(Integer i, Integer j) {
        AL.get(i).remove(j);
        AL.get(j).remove(i);
    }

    public static UndirectedGraph[] export() throws Exception {
        BufferedReader zachary = new BufferedReader(new FileReader("project/src/Zettel11Bela/out.ucidata-zachary.sec"));
        BufferedReader twitter = new BufferedReader(new FileReader("project/src/Zettel11Bela/soc-twitter-follows.txt"));

        FindMax findMaxZachary = new FindMax();
        FindMax findMaxTwitter = new FindMax();

        zachary.lines().forEach(findMaxZachary);
        twitter.lines().forEach(findMaxTwitter);


        UndirectedGraph zacharyG = new UndirectedGraph(findMaxZachary.max);
        UndirectedGraph twitterG = new UndirectedGraph(findMaxTwitter.max);

        zachary = new BufferedReader(new FileReader("project/src/Zettel11Bela/out.ucidata-zachary.sec"));
        twitter = new BufferedReader(new FileReader("project/src/Zettel11Bela/soc-twitter-follows.txt"));


        while (zachary.ready()){
            String[] s2= zachary.readLine().split(" ");
            int[] i2={Integer.parseInt(s2[0]), Integer.parseInt(s2[1])};
            zacharyG.addEdge(i2[0],i2[1]);
        }

        while (twitter.ready()){
            String[] s2= twitter.readLine().split(" ");
            int[] i2={Integer.parseInt(s2[0]), Integer.parseInt(s2[1])};
            twitterG.addEdge(i2[0],i2[1]);
        }
        return new UndirectedGraph[]{zacharyG/*,twitterG*/};
    }


    public static void main(String[] args) throws Exception {
        BufferedReader zachary = new BufferedReader(new FileReader("project/src/Zettel11Bela/out.ucidata-zachary.sec"));
        BufferedReader twitter = new BufferedReader(new FileReader("project/src/Zettel11Bela/soc-twitter-follows.txt"));

        FindMax findMaxZachary = new FindMax();
        FindMax findMaxTwitter = new FindMax();

        zachary.lines().forEach(findMaxZachary);
        twitter.lines().forEach(findMaxTwitter);


        UndirectedGraph zacharyG = new UndirectedGraph(findMaxZachary.max);
        UndirectedGraph twitterG = new UndirectedGraph(findMaxTwitter.max);

        zachary = new BufferedReader(new FileReader("project/src/Zettel11Bela/out.ucidata-zachary.sec"));
        twitter = new BufferedReader(new FileReader("project/src/Zettel11Bela/soc-twitter-follows.txt"));


        while (zachary.ready()){
            String[] s2= zachary.readLine().split(" ");
            int[] i2={Integer.parseInt(s2[0]), Integer.parseInt(s2[1])};
            zacharyG.addEdge(i2[0],i2[1]);
        }

        while (twitter.ready()){
            String[] s2= twitter.readLine().split(" ");
            int[] i2={Integer.parseInt(s2[0]), Integer.parseInt(s2[1])};
            twitterG.addEdge(i2[0],i2[1]);
        }
    }
}

class FindMax implements Consumer<String>{
    int max=0;

    @Override
    public void accept(String s) {
        Arrays.stream(s.split(" ")).map(s1->Integer.parseInt(s1)).forEach(s1->{if (s1>max){max=s1;}});
    }
}
