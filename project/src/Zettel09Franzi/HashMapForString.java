package Zettel09Franzi;

import java.util.ArrayList;
import java.util.List;

public class HashMapForString {
    int m;
    List<String>[] hashTable;

    public HashMapForString(int m) {
        this.m = m;
        this.hashTable= new ArrayList[m];

        for(int i=0; i<m;i++) {
            this.hashTable[i]= new ArrayList();
        }
    }

    public int hash(String string){
        int sum = 0;
        for(int i = 0; i < string.length(); i++){
            sum += string.charAt(i);
        }

        return sum%this.m;
    }

    public void insert(String string) {
        int hash= hash(string);
        this.hashTable[hash].add(string);
    }

    public void delete(String string) {
        int hash = hash(string);
        while (this.hashTable[hash].remove(string)){}
    }

    public boolean search(String string) {
        int hash = hash(string);
        return this.hashTable[hash].contains(string);
    }
}
