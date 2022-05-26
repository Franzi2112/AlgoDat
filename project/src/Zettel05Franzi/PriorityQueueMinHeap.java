package Zettel05Franzi;

public class PriorityQueueMinHeap implements PriorityQueue<Integer> {
    private int[] a;
    private int heapsize;

    public void decreaseKey(int i, int key){
        if (key > a[i]){
            throw new IllegalArgumentException("New key is bigger than old key.");
        }
        a[i] = key;
        while(i > 1 && a[(i-1)/2] > a[i] ){
            int dad = a[(i-1/2)];
            a[i-1/2] = a[i];
            a[i] = dad;
            i = i-1/2;
        }
    }

    public void minHeapify(int i){
        int l = 2*i+1;
        int r = 2*i+2;
        int min;
        if(l < heapsize && a[l] < a[i]){
            min = l;
        } else {
            min = i;
        } if ( r < heapsize && a[r] < a[min]){
            min = r;
        } if (min != i){
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
            minHeapify(min);
        }
    }
    public void buildMinHeap(){
        for(int i = heapsize/2; i>=0; i--){
            minHeapify(i);
        }
    }

    public PriorityQueueMinHeap(int capacity){
        a = new int[capacity];
        heapsize = 0;
        //warum hier nicht wirklich die Lnge also wüsste gerade auch nicht wie ich die ausrechnen sollte aber kann man die nicht ablesen?
        // am Anfang null elemente
    }


    public void addElement(Integer elem){
        if(heapsize >= a.length){
            throw new IllegalArgumentException("The Queue is already full!");
        }
        a[heapsize] = Integer.MAX_VALUE;
        decreaseKey(heapsize, elem);
        heapsize++;

        
    }

    @Override
    public Integer getFirst() {
        return a[0];
    }

    @Override
    public void deleteFirst() {
        if (heapsize < 1){
            throw new IllegalArgumentException("There are no keys!");
        }
        a[0] = a[heapsize];
        heapsize--;
        minHeapify(0);

    }
}
