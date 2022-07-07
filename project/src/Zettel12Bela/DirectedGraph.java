package Zettel12Bela;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class DirectedGraph {
    List<Integer> nodes;
    HashMap<Integer, List<Integer>> AL;

    public DirectedGraph() {
        nodes = new ArrayList<>();
        AL = new HashMap<>();
    }

    public DirectedGraph(Integer i) {
        nodes = new ArrayList<>();
        AL = new HashMap<>();
        for (int j = 1; j <= i; j++) {
            nodes.add(j);
        }
    }

    public void addVertex(Integer i) {
        if (nodes.contains(i)) {
            return;
        } else {
            nodes.add(i);
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
        AL.get(i).removeAll(Collections.singleton(j));
    }

    public static DirectedGraph[] export() throws Exception {
        BufferedReader[] readers = new BufferedReader[5];
        readers[0] = new BufferedReader(new FileReader("project/src/Zettel12Bela/outbnet.sec"));
        readers[1] = new BufferedReader(new FileReader("project/src/Zettel12Bela/outmaayan-figeys.sec"));
        readers[2] = new BufferedReader(new FileReader("project/src/Zettel12Bela/outmoreno_dense_comm.sec"));
        readers[3] = new BufferedReader(new FileReader("project/src/Zettel12Bela/outmoreno_taro_taro.sec"));
        readers[4] = new BufferedReader(new FileReader("project/src/Zettel12Bela/outsimple.sec"));

        FindMax[] findMax = new FindMax[5];
        findMax[0] = new FindMax();
        findMax[1] = new FindMax();
        findMax[2] = new FindMax();
        findMax[3] = new FindMax();
        findMax[4] = new FindMax();

        for (int i = 0; i < 5; i++) {
            readers[i].lines().forEach(findMax[i]);
        }
        DirectedGraph[] dg = new DirectedGraph[5];


        for (int i = 0; i < 5; i++) {
            dg[i] = new DirectedGraph(findMax[i].max);
        }

        readers[0] = new BufferedReader(new FileReader("project/src/Zettel12Bela/outbnet.sec"));
        readers[1] = new BufferedReader(new FileReader("project/src/Zettel12Bela/outmaayan-figeys.sec"));
        readers[2] = new BufferedReader(new FileReader("project/src/Zettel12Bela/outmoreno_dense_comm.sec"));
        readers[3] = new BufferedReader(new FileReader("project/src/Zettel12Bela/outmoreno_taro_taro.sec"));
        readers[4] = new BufferedReader(new FileReader("project/src/Zettel12Bela/outsimple.sec"));

        for (int i = 0; i < 5; i++) {
            while (readers[i].ready()) {
                String[] line = readers[i].readLine().split(" ");
                dg[i].addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            }
        }

        return dg;
    }
}

    class FindMax implements Consumer<String> {
        int max = 0;

        @Override
        public void accept(String s) {
            Arrays.stream(s.split(" ")).map(s1 -> Integer.parseInt(s1)).forEach(s1 -> {
                if (s1 > max) {
                    max = s1;
                }
            });
        }

    }
