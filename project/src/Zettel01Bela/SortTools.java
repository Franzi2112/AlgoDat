package Zettel01Bela;

public class SortTools {
    public static int[] createSequenceInc(int n) {
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = i;
        }
        return seq;
    }

    public static int[] createSequenceDec(int n) {
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = n - i - 1;
        }
        return seq;
    }

    public static int[] createSequenceRand(int n){
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = (int) (Math.random() * n);
        }
        return seq;
    }

    public static int[] createSequenceAlt(int n){
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = (i+1) % 2;
        }
        return seq;
    }

    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && a[j] < a[j - 1]) {
                int tmp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = tmp;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int testSize = 10;
        int[] times=new int[testSize*5];
        int[][] testData = { createSequenceDec(100), createSequenceDec(1000),createSequenceDec(10000), createSequenceDec(100000), createSequenceDec(200000)};

        for (int i = 0; i < testData.length; i++) {
            int[] temp = testData[i].clone();
            for (int j = 0; j < testSize; j++) {
                long start = System.nanoTime();
                insertionSort(testData[i]);
                times[i*testSize+j]=(int)(System.nanoTime()-start);
                testData[i]=temp;
            }
        }
        for (int i = 0; i < testData.length; i++) {
            System.out.print("Test "+i+": ");
            for (int j = 0; j < testSize; j++) {
                System.out.printf("%5f ",times[i*testSize+j]*Math.pow(10,-9));
            }
            System.out.println();

        }
    }

}
