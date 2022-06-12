package Zettel09Bela;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class HashmapForStrings {
    int size;//eigentlich m
    List<String>[] hashtable;

    public HashmapForStrings(int m) {
        this.size=m;
        this.hashtable= new ArrayList[m];

        for(int i=0; i<m;i++) {
            this.hashtable[i]= new ArrayList();
        }
    }

    public int hash(String string) {
        byte[] bytes= string.getBytes();
        int sum=0;
        int counter=0;
        for (byte bite:bytes) {
            sum+= Byte.toUnsignedInt(bite)*counter++;
        }
        return sum%this.size;
    }

    public void insert(String string) {
        int hash= hash(string);
        this.hashtable[hash].add(string);
    }

    public void delete(String string) {
        int hash =hash(string);
        while (this.hashtable[hash].remove(string)){}
    }

    public boolean search(String string) {
        int hash = hash(string);
        return this.hashtable[hash].contains(string);
    }

    public static void main(String[] args) throws Exception{
        int[] sizes = {1000,10000};

        for (int size: sizes) {
            HashmapForStrings map = new HashmapForStrings(1000);
            FileReader scrabble = new FileReader("project/src/Zettel09Bela/OfficialScrabbleWordListGerman.txt");
            FileReader friends = new FileReader("project/src/Zettel09Bela/AreMyFriendsCheating.txt");

            System.out.println("Tablesize: "+size);

            BufferedReader reader = new BufferedReader(scrabble);
            long currentTime= System.nanoTime();
            reader.lines().forEach((String str) ->map.insert(str));
            long time = System.nanoTime()-currentTime;
            System.out.println("\tInsertion Time: "+time*Math.pow(10,-9));


            ContainsCheck containsCheck = new ContainsCheck();

            reader = new BufferedReader(friends);
            int counter=0;
            currentTime= System.nanoTime();
            reader.lines().forEach((String s)->containsCheck.accept(s,map));
            time = System.nanoTime()-currentTime;
            System.out.println("\tSearch Time: "+time*Math.pow(10,-9));
            System.out.println("\tWords in Map: "+containsCheck.count);

        }
    }


}
class ContainsCheck implements BiConsumer<String, HashmapForStrings>{
    int count=0;
    @Override
    public void accept(String s,HashmapForStrings map) {
        if (map.search(s)){
            count++;
        }
    }
}