package Zettel01Bela;

import java.util.Comparator;
import java.util.Random;

public class SortTools {
    public static int[] createSequenceInt(int n) {
        int[] ascending = new int[n];
        for (int i = 0; i < n; i++) {
            ascending[i] = i + 1;
        }
        return ascending;
    }

    public static int[] createSequenceDec(int n) {
        int[] descending = new int[n];
        for (int i = 1; i < n+ 1; i++) {
            descending[i - 1] = n + 1 - i ;
        }
        return descending;
    }

    public static int[] createSequenceRand(int n) {
        int[] randomSeq = new int[n];
        for (int i = 0; i < n; i++) {
            Random rd = new Random();
            randomSeq[i] = rd.nextInt(n) + 1;
        }
        return randomSeq;
    }

    public static int[] createSequenceAlt(int n) {
        int[] altSeq = new int[n];
        for (int i = 0; i < n; i++) {
            if ((i + 1) % 2 != 0) {
                altSeq[i] = 1;
            } else {
                altSeq[i] = 2;
            }
        }
        return altSeq;
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

    public static <T extends Comparable> void insertionSortGen(T[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && a[j].compareTo(a[j - 1]) < 0) {
                T tmp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = tmp;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int testSize = 10;
        long[] times=new long[testSize*5];
        int[][] testData = { createSequenceDec(100), createSequenceDec(1000),createSequenceDec(10000), createSequenceDec(100000), createSequenceDec(200000)};

        for (int j = 0; j < testSize; j++) {
            for (int i = 0; i < testData.length; i++) {
                int[] temp = testData[i].clone();
                long start = System.nanoTime();
                insertionSort(testData[i]);
                times[i*testSize+j]=(System.nanoTime()-start);
                testData[i]=temp;
            }
        }
        for (int i = 0; i < testData.length; i++) {
            System.out.print("Test "+i+": ");
            double avg=0;
            for (int j = 0; j < testSize; j++) {
                System.out.printf("%5f ",times[i*testSize+j]*Math.pow(10,-9));
                avg+=times[i*testSize+j]*Math.pow(10,-9);
            }
            System.out.println();
            System.out.printf("\tAverage: %5f\n",avg/testSize);
        }
    }
}

