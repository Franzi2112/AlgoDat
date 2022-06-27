package Zettel10Franzi;

import java.util.function.BiFunction;

public class OpenHashMapForString {
    Deleted deleted = new Deleted();
    int m;
    Object[] hashtable;
    BiFunction<String, Integer, Integer> selectedHashFunction;

    public OpenHashMapForString(int m, String mode) {
        this.m = m;
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

    public int hashHelper(String string){
        return stringToNumber(string) % this.m;
    }

    public int linearHash(String string, int startIndex){
        return hashHelper(string) + startIndex % this.m;
    }

    public int quadraticHash(String string, int i){
        return (hashHelper(string) +i/2 + (i*i)/2) % this.m;
    }
    private int hash2(String string) {
        int hash= stringToNumber(string);
        while ((1+hash%(this.m-1))%5==0||(1+hash%(this.m-1))%2==0){hash++;}//wie kommt man auf mod 5 für zweier Potenz?
        return Math.abs(1+hash%(this.m-1));
    }

    public int doubleHash(String string, int i){
        return Math.abs((stringToNumber(string)+i*hash2(string)%this.m)%this.m);
    }

    public void hashInsert(String string){
        int i = 0;
        while (i < m){
            int j = selectedHashFunction.apply(string, i); //warum hast du hier i++?
            if(this.hashtable[j] == null || this.hashtable[j] == deleted){
                hashtable[j] = string;
                return;
            } if(this.hashtable[j].equals(string)) {
                return;
            }
        }
        throw new OutOfMemoryError("hashtable is full");
    }

    public boolean hashSearch(String string){
        int i = 0;
        while(i < m){
            int j = selectedHashFunction.apply(string, i);
            if(this.hashtable[j] == null){
                return false;
            } if(this.hashtable[j].equals(string)){
                return true;
            }
        }
        return false;
    }

    public void delete(String string){
        int i=0;
        while (i< m) {
            int hash = this.selectedHashFunction.apply(string, i++);
            if (this.hashtable[hash]==string){
                this.hashtable[hash] = deleted;
                return;
            }
        }
    }


}
class Deleted{}