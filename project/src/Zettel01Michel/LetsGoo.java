package Zettel01Michel;
import java.util.Random;

public class LetsGoo {
    public static int[] createSequenceInc(int n) {
        int[] arrInc = new int[n];
        int i = 0;
        while (i < n) {
            arrInc[i] = i;
            i++;
        }
        return arrInc;
    }

    public static int[] createSequenceDec(int n){
        int[] arrDec = new int[n];
        int j = 0;
        while(n > 0){
            arrDec[j] = n;
            n--;
            j++;
        }
        return arrDec;
    }

   public static int[] createSequenceRand(int n){
        int[] arrRand = new int[n];
        int j = 0;
        Random randInt = new Random();
        while(n>0){
            arrRand[j] = randInt.nextInt(n);
            j++;
            n--;
        }
        return arrRand;
    }

    public static int[] createSequenceAlt(int n){
        int[] arrAlt = new int[n];
        int j = 0;
        while(n>0){
            if(n%2 == 0){
                arrAlt [j] = 1;
            } else {
                arrAlt[j] = 2;
            }
            n--;
            j++;
        }
        return arrAlt;
    }

    public static int[] insertionSort(int[] a){
        int[] sort = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < a.length; j++) {
                if(min > a[j]){
                    int b = min;
                    min = a[j];
                    a[j] = b;
                }
            }
            sort[i] = min;
        }
        return sort;
    }


    public static void main(String[] args) {
        int[] arrInc = createSequenceInc(10);
        for (int i = 0; i < arrInc.length; i++) {
            System.out.println(arrInc[i]);
        }

        int[] arrDec = createSequenceDec(10);
        for(int i = 0; i < arrDec.length; i++){
            System.out.println(arrDec[i]);
        }

        int[] arrRand = createSequenceRand(10);
        for (int i = 0; i < arrRand.length; i++) {
            System.out.println(arrRand[i]);
        }

        int[] arrAlt = createSequenceAlt(10);
        for (int i = 0; i < arrAlt.length; i++) {
            System.out.println(arrAlt[i]);
        }

        int[] arr = insertionSort(createSequenceRand(10));
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        int[] dec100 = createSequenceDec(100);
        long nanoTime100 = System.nanoTime();
        insertionSort(dec100);
        System.out.println(nanoTime100);

        int[] dec1000 = createSequenceDec(1000);
        long nanoTime1000 = System.nanoTime();
        insertionSort(dec1000);
        System.out.println(nanoTime1000);

        int[] dec10000 = createSequenceDec(10000);
        long nanoTime10000 = System.nanoTime();
        insertionSort(dec10000);
        System.out.println(nanoTime10000);

        int[] dec100000 = createSequenceDec(100000);
        long nanoTime100000 = System.nanoTime();
        insertionSort(dec100000);
        System.out.println(nanoTime100000);

        int[] dec200000 = createSequenceDec(200000);
        long nanoTime200000 = System.nanoTime();
        insertionSort(dec200000);
        System.out.println(nanoTime200000);

    }
}
