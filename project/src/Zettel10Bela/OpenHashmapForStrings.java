package Zettel10Bela;

import Zettel09Bela.HashmapForStrings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class OpenHashmapForStrings {
    Deleted deleted= new Deleted();
    int size; //=m
    Object[] hashtable;
    BiFunction<String, Integer, Integer> selectedHashFunction;

    public OpenHashmapForStrings(int m, String mode) {
        this.size = m;
        this.hashtable = new String[m];
        if (mode =="linear"){
            selectedHashFunction=this::linearHash;
        } else if (mode=="quadratic") {
            selectedHashFunction=this::quadraticHash;
        } else if (mode=="double") {
            selectedHashFunction=this::doubleHash;
        } else {
            throw new IllegalArgumentException("not a valid Hashtype");
        }
    }

    private int stringToNumber(String string) {
        int sum=1;
        for (char c : string.toCharArray()) {
            sum *=c;
        }
        return Math.abs(sum);
    }

    public int hashHelp(String string) {
        return stringToNumber(string)%this.size;
    }

    public int linearHash(String string, int i) {
        return (stringToNumber(string)+i)%this.size;
    }

    public int quadraticHash(String string, int i) {
        return (stringToNumber(string)+i/2+i*i/2)%this.size;
    }

    private int hash2(String string) {
        int hash= stringToNumber(string);
        while ((1+hash%(this.size-1))%5==0||(1+hash%(this.size-1))%2==0){hash++;}
        return Math.abs(1+hash%(this.size-1));
    }

    public int doubleHash(String string, int i) {
        return Math.abs((stringToNumber(string)+i*hash2(string)%this.size)%this.size);
    }

    public void insert(String string) {
        int i =0;
        while (i<size){
            int hash = selectedHashFunction.apply(string, i++);

            if (this.hashtable[hash]==null||this.hashtable[hash]==deleted){
                this.hashtable[hash]=string;
                return;
            }
            if (this.hashtable[hash].equals(string)){
                return;
            }
        }
        throw new OutOfMemoryError("hashtable is full");
    }

    public void delete(String string) {
        int i=0;
        while (i<size) {
            int hash = this.selectedHashFunction.apply(string, i++);
            if (this.hashtable[hash]==string){
                this.hashtable[hash] = deleted;
                return;
            }
        }
    }

    public boolean search(String string) {
        int i = 0;
        while (i<size) {
            int hash =selectedHashFunction.apply(string, i++);
            if (this.hashtable[hash]==null) {
                return false;
            }else if (this.hashtable[hash].equals(string)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception{
        int[] sizes = {200000, 1000000};
        String[] modes = {"linear", "quadratic", "double"};

        for (String mode: modes) {
            System.out.println("\nhashfunction: "+ mode);
            for (int size: sizes) {

                OpenHashmapForStrings map = new OpenHashmapForStrings(size,mode);
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
                System.out.println("\tErfolglose Suchen: "+containsCheck.count);

            }
        }
    }


}

class Deleted {}


class ContainsCheck implements BiConsumer<String, OpenHashmapForStrings> {
    int count=0;
    @Override
    public void accept(String s,OpenHashmapForStrings map) {
        if (!map.search(s)){
            count++;
        }
    }
}
/*

hashfunction: linear
Tablesize: 200000
	Insertion Time: 0.40906970000000004
	Search Time: 0.0488859
	Erfolglose Suchen: 1814
Tablesize: 1000000
	Insertion Time: 0.1227223
	Search Time: 0.0027151000000000002
	Erfolglose Suchen: 1814

hashfunction: quadratic
Tablesize: 200000
	Insertion Time: 0.44208010000000003
	Search Time: 0.026723100000000003
	Erfolglose Suchen: 1814
Tablesize: 1000000
	Insertion Time: 0.12181220000000001
	Search Time: 0.0027604
	Erfolglose Suchen: 1814

hashfunction: double
Tablesize: 200000
	Insertion Time: 0.2642795
	Search Time: 0.0090028
	Erfolglose Suchen: 1814
Tablesize: 1000000
	Insertion Time: 0.0891531
	Search Time: 0.002532
	Erfolglose Suchen: 1814
 */