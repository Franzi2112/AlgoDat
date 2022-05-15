package Zettel05Bela;

public class PriorityQueueMinHeap implements PriorityQueue<Integer> {
    private int[] a;
    private int heapsize;


    public void decreaseKey(int i, int key){
        if(key>a[i]){
            throw new IllegalArgumentException("New key is larger than current key");
        }
        a[i]=key;
        while(i>0 && a[(i-1)/2]>a[i]){
            int temp=a[i];
            a[i]=a[(i-1)/2];
            a[(i-1)/2]=temp;
            i=(i-1)/2;
        }
    }



    private void minHeapify(int i) {
        int l=2*i+1;
        int r=2*i+2;
        int smallest;
        if(l<heapsize && a[l]<a[i]){
            smallest=l;
        } else {
            smallest=i;
        }
        if(r<heapsize && a[r]<a[smallest]){
            smallest=r;
        }
        if(smallest!=i){
            int temp=a[i];
            a[i]=a[smallest];
            a[smallest]=temp;
            minHeapify(smallest);
        }
    }

    private void buildMinHeap() {
        for(int i=heapsize/2;i>=0;i--){
            minHeapify(i);
        }
    }

    public PriorityQueueMinHeap(int capacity) {
        a=new int[capacity];
        heapsize=0;
    }

    @Override
    public void addElement(Integer elem) {
        if(heapsize>=a.length){
            throw new IllegalStateException("PriorityQueue is full");
        }
        a[heapsize]=Integer.MAX_VALUE;
        decreaseKey(heapsize,elem);
        heapsize++;
    }

    @Override
    public Integer getFirst() {
        return a[0];
    }

    @Override
    public void deleteFirst() {
        a[0]=a[heapsize-1];
        heapsize--;
        minHeapify(0);
    }
}
