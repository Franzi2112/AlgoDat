package Zettel05Bela;

import java.util.ArrayList;

public class PriorityQueueMinHeap<T extends Comparable<T>> implements PriorityQueue<T> {
    private ArrayList<T> a = new ArrayList<>();


    public void decreaseKey(int i, T key){
        if(key.compareTo(a.get(i))>0){
            throw new IllegalArgumentException("New key is larger than current key");
        }
        a.set(i,key);
        while(i>0 && a.get((i-1)/2).compareTo(a.get(i))>0){
            T temp=a.get(i);
            a.set(i,a.get((i-1)/2));
            a.set((i-1)/2,temp);
            i=(i-1)/2;
        }
    }



    private void minHeapify(int i) {
        int l=2*i+1;
        int r=2*i+2;
        int smallest;
        if(l<a.size() && a.get(l).compareTo(a.get(i))<0){
            smallest=l;
        } else {
            smallest=i;
        }
        if(r<a.size() && a.get(r).compareTo(a.get(smallest))<0){
            smallest=r;
        }
        if(smallest!=i){
            T temp=a.get(i);
            a.set(i,a.get(smallest));
            a.set(smallest,temp);
            minHeapify(smallest);
        }
    }

    private void buildMinHeap() {
        for(int i=a.size()/2;i>=0;i--){
            minHeapify(i);
        }
    }

    @Override
    public void addElement(T elem) {
        a.add(elem);
        decreaseKey(a.size()-1,elem);
    }

    @Override
    public T getFirst() {
        return a.get(0);
    }

    @Override
    public void deleteFirst() {
        a.set(0,a.get(a.size()-1));
        minHeapify(0);
    }

    public static void main(String[] args) {
        PriorityQueueMinHeap<Integer> pq=new PriorityQueueMinHeap<>();
        pq.addElement(5);
        pq.addElement(3);
        pq.addElement(1);
        pq.addElement(4);
        pq.addElement(2);
        System.out.println(pq.getFirst());
        pq.deleteFirst();
        System.out.println(pq.getFirst());
        pq.deleteFirst();
        System.out.println(pq.getFirst());
        pq.deleteFirst();
        System.out.println(pq.getFirst());
        pq.deleteFirst();
        System.out.println(pq.getFirst());
        pq.deleteFirst();
        System.out.println(pq.getFirst());
    }
}
