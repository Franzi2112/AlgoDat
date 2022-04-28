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
    /*results:
    Test 0: 0,000150 0,000004 0,000004 0,000004 0,000004 0,000004 0,000004 0,000004 0,000005 0,000004
	    Average: 0,000019
    Test 1: 0,003340 0,000299 0,000308 0,000308 0,000308 0,000308 0,000299 0,000307 0,000308 0,000308
	    Average: 0,000609
    Test 2: 0,036265 0,029634 0,030261 0,030034 0,029404 0,030132 0,029416 0,030239 0,030275 0,030188
	    Average: 0,030585
    Test 3: 3,404859 2,993242 3,004208 3,008037 3,010638 3,014281 3,183810 3,013835 3,015538 3,003034
	    Average: 3,065148
    Test 4: 12,373809 12,175191 12,194604 12,190444 12,207006 12,439503 12,281419 12,241610 12,197787 12,202462
	    Average: 12,250383
     */

}
