package Zettel02Bela;

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

    public static void bubbleSort(int[] a) {
        for (int i=a.length-1;i>0;i--) {
            for (int j=0;j<i;j++) {
                if (a[j]>a[j+1]) {
                    int tmp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=tmp;
                }
            }
        }
    }

    public static void bubbleSortNew(int[]a) {
        for (int i=a.length-1;i>0;i--) {
            for (int j=0;j<i;j+=9) {
                //sort[j->j+10]
                for (int k=j+1;k<Math.min(j+10,a.length-1);k++) {
                    int l = k;
                    while (l > j && a[l] < a[l - 1]) {
                        int tmp = a[l];
                        a[l] = a[l - 1];
                        a[l - 1] = tmp;
                        l--;
                    }
                }
            }
        }
    }

    public static void bubbleSortGen(Comparable[] a) {
        for (int i=a.length-1;i>0;i--) {
            for (int j=0;j<i;j++) {
                if (a[j].compareTo(a[j+1])>0) {
                    Comparable tmp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=tmp;
                }
            }
        }
    }

    public static void main(String[] args) {



        int testSize = 10;
        long[] times=new long[3];
        int[][] testData = { createSequenceDec(100), createSequenceDec(1000),createSequenceDec(10000), createSequenceDec(100000)};


        //insertionSort timing
        System.out.println("InsertionSort");
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testData.length; j++) {
                int[] testtemp = testData[j].clone();
                long start = System.nanoTime();
                insertionSort(testtemp);
                times[0]=times[0]+(System.nanoTime()-start);
            }
        }
        //bubbleSort timing
        System.out.println("BubbleSort");
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testData.length; j++) {
                int[] testtemp = testData[j].clone();
                long start = System.nanoTime();
                bubbleSort(testtemp);
                times[1]=times[1]+(System.nanoTime()-start);
            }
        }
        //bubbleSortNew timing
        System.out.println("BubbleSortNew");
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testData.length; j++) {
                int[] testtemp = testData[j].clone();
                long start = System.nanoTime();
                bubbleSortNew(testtemp);
                times[2]=times[2]+(System.nanoTime()-start);
            }
        }
        //calc mean
        for (int i = 0;i<times.length;i++) {
            times[i]=  times[i]/(testSize*testData.length);
        }

        //print results
        System.out.printf("insertionSort: %5f\nbubbleSort: %5f\nbubbleSortNew: %5f\n",times[0]/Math.pow(10,9),times[1]/Math.pow(10,9),times[2]/Math.pow(10,9));
        /*
        insertionSort: 1,027081
        bubbleSort: 0,923869
        bubbleSortNew: 3,729730 */
    }
}