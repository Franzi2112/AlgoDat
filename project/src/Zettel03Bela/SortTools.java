package Zettel03Bela;

import java.util.Arrays;
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

    public static void mergeSort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    private static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(a, left, middle);
            mergeSort(a, middle + 1, right);
            merge(a, left, middle, right);
        }
    }

    private static void merge(int[] a, int left, int middle, int right) {
        //initalise external arrays
        int[] L = new int[middle - left + 2];
        int[] R = new int[right - middle+1];
        for (int i = 0; i < L.length-1; i++) {
            L[i] = a[left + i];
        }
        L[L.length-1] = Integer.MAX_VALUE;
        for (int i = 0; i < R.length-1; i++) {
            R[i] = a[middle + 1 + i];
        }
        R[R.length-1] = Integer.MAX_VALUE;
        //pointers
        int i = 0;
        int j = 0;
        int k = left;

        while (k <= right) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }
    }

    public static void mergeSortNew(int[] a) {
        mergeSortNew(a, 0, a.length - 1);
    }

    private static void mergeSortNew(int[] a, int left, int right) {
        if (left < right) {
            int middleleft = (right-left)/3+left;
            int middleright = 2*(right-left)/3+left;
            mergeSortNew(a, left, middleleft);
            mergeSortNew(a, middleleft+1, middleright);
            mergeSortNew(a, middleright+1, right);
            mergeNew(a, left, middleleft, middleright, right);
        }
    }

    private static void mergeNew(int[] a, int left, int middleleft, int middleright, int right) {
        int[] L = new int[middleleft - left + 2];
        int[] M = new int[middleright - middleleft+1];
        int[] R = new int[right - middleright+1];
        for (int i = 0; i < L.length-1; i++) {
            L[i] = a[left + i];
        }
        L[L.length-1] = Integer.MAX_VALUE;
        for (int i = 0; i < M.length-1; i++) {
            M[i] = a[middleleft + 1 + i];
        }
        M[M.length-1] = Integer.MAX_VALUE;
        for (int i = 0; i < R.length-1; i++) {
            R[i] = a[middleright + 1 + i];
        }
        R[R.length-1] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int l = 0;
        int k = left;
        while (k <= right) {
            if (L[i] <= M[j] && L[i] <= R[l]) {
                a[k] = L[i];
                i++;
            }
            else if (M[j] <= L[i] && M[j] <= R[l]) {
                a[k] = M[j];
                j++;
            }
            else {
                a[k] = R[l];
                l++;
            }
            k++;
        }
    }

    public static void mergeSortGen(Comparable[] a) {
        mergeSortGen(a, 0, a.length - 1);
    }


    //cast array to Comparable first!!!!
    private static void mergeSortGen(Comparable[] a, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSortGen(a, left, middle);
            mergeSortGen(a, middle + 1, right);
            mergeGen(a, left, middle, right);
        }
    }

    private static void mergeGen(Comparable[] a, int left, int middle, int right) {
        Comparable[] L = new Comparable[middle - left + 2];
        Comparable[] R = new Comparable[right - middle+1];
        for (int i = 0; i < L.length-1; i++) {
            L[i] = a[left + i];
        }
        L[L.length-1] = Character.MAX_VALUE;
        for (int i = 0; i < R.length-1; i++) {
            R[i] = a[middle + 1 + i];
        }
        R[R.length-1] = Character.MAX_VALUE;
        int i = 0;
        int j = 0;
        int k = left;
        while (k <= right) {
            if (L[i].compareTo(R[j]) < 0) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }
    }


    public static void main(String[] args) {

        Comparable[] arr = {'t','a','z','b', 'a'};
        mergeSortGen(arr);



        System.out.println(Arrays.toString(arr));
       /* int testSize = 10;
        //1. insertionSort
        //2. mergeSort
        //3. mergeSortNew
        long[] times=new long[6];
        int[][] testDataInc = { createSequenceInt(100), createSequenceInt(1000),createSequenceInt(10000), createSequenceInt(100000), createSequenceInt(200000)};
        int[][] testDataDec = { createSequenceDec(100), createSequenceDec(1000),createSequenceDec(10000), createSequenceDec(100000), createSequenceDec(200000)};



        //insertionSort timing
        System.out.println("InsertionSort Inc");
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testDataInc.length; j++) {
                int[] testtemp = testDataInc[j].clone();
                long start = System.nanoTime();
                insertionSort(testtemp);
                times[0]=times[0]+(System.nanoTime()-start);
            }
        }
        System.out.println("InsertionSort Dec");
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testDataDec.length; j++) {
                int[] testtemp = testDataDec[j].clone();
                long start = System.nanoTime();
                insertionSort(testtemp);
                times[1]=times[1]+(System.nanoTime()-start);
            }
        }
        //mergeSort timing
        System.out.println("MergeSort Inc");
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testDataInc.length; j++) {
                int[] testtemp = testDataInc[j].clone();
                long start = System.nanoTime();
                mergeSort(testtemp);
                times[2]=times[2]+(System.nanoTime()-start);
            }
        }
        System.out.println("MergeSort Dec");
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testDataDec.length; j++) {
                int[] testtemp = testDataDec[j].clone();
                long start = System.nanoTime();
                mergeSort(testtemp);
                times[3]=times[3]+(System.nanoTime()-start);
            }
        }
        //mergeSortNew timing
        System.out.println("MergeSortNew Inc");
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testDataInc.length; j++) {
                int[] testtemp = testDataInc[j].clone();
                long start = System.nanoTime();
                mergeSortNew(testtemp);
                times[4]=times[4]+(System.nanoTime()-start);
            }
        }
        System.out.println("MergeSortNew Dec");
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < testDataDec.length; j++) {
                int[] testtemp = testDataDec[j].clone();
                long start = System.nanoTime();
                mergeSortNew(testtemp);
                times[5]=times[5]+(System.nanoTime()-start);
            }
        }

        //calc mean
        for (int i = 0;i<times.length;i+=2) {
            times[i]=  times[i]/(testSize*testDataInc.length);
        }
        for (int i = 1;i<times.length;i+=2) {
            times[i]=  times[i]/(testSize*testDataDec.length);
        }

        //print results
        System.out.printf("\nresults:\t\tInc\t\t\tDec\ninsertionSort:\t%3f\t%3f\nmergeSort:\t\t%3f\t%3f\nmergeSortNew:\t%3f\t%3f\n",times[0]/Math.pow(10,9),times[1]/Math.pow(10,9),times[2]/Math.pow(10,9),times[3]/Math.pow(10,9),times[4]/Math.pow(10,9),times[5]/Math.pow(10,9));
        /*
        insertionSort: 0,000168	3,305641
        mergeSort:     0,005090	0,003377
        mergeSortNew:  0,003287	0,002787 */
    }
}