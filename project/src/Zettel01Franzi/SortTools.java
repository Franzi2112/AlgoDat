package Zettel01Franzi;

import Zettel04Bela.SearchTools;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SortTools {

    public static int[] createSequenceInc(int n) {
        int[] ascending = new int[n];
        for (int i = 0; i < n; i++) {
            ascending[i] = i + 1;
        }
        return ascending;
    }

    public static int[] createSequenceDec(int n) {
        int[] descending = new int[n];
        for (int i = 1; i < n + 1; i++) {
            descending[i - 1] = n + 1 - i;
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
        for (int i = 1; i < a.length - 1; i++) {
            int s = a[i]; //Speicher den Wert des Elementes was einsortiert werden muss
            int j = i; //Speicher den Indize des einzusortierenden Elementes
            while (j > 0 && a[j - 1] > s) { //überprüfe solange die vorhergehenden elemente bis das zu vergleichende nicht mehr größer ist als das vorherige
                a[j] = a[j - 1]; //dabei verschiebe ich die Elemente die größer sind immer um einen Platz nach vorne
                j = j - 1; //verkleinere den Indize des zu vergleichenden Elementes
            }
            a[j] = s; //setze dann an die Stelle,wo das letzte Elements war was noch größer war als das zu vergleichende, dann meinen vergleichswert
        }

    }

    public static <T extends Comparable<T>> void insertionSortGen(T[] a) {
        for (int i = 1; i < a.length - 1; i++) {
            T s = a[i]; //Speicher den Wert des Elementes was einsortiert werden muss
            int j = i; //Speicher den Indize des einzusortierenden Elementes
            while (j > 0 && a[j - 1].compareTo(s) < 0) { //überprüfe solange die vorhergehenden elemente bis das zu vergleichende nicht mehr größer ist als das vorherige
                a[j] = a[j - 1]; //dabei verschiebe ich die Elemente die größer sind immer um einen Platz nach vorne
                j = j - 1; //verkleinere den Indize des zu vergleichenden Elementes
            }
            a[j] = s; //setze dann an die Stelle,wo das letzte Elements war was noch größer war als das zu vergleichende, dann meinen vergleichswert
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int n = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = n;
                }
            }
        }
    }

    public static void bubbleSortNew(int[] arr) {
        for (int i = arr.length - 1; i > 9; i--) {
            for (int j = 0; j < i - 10; j++) {
                for (int k = j; k < j + 10; k++) {
                    int s = arr[k]; //Speicher den Wert des Elementes was einsortiert werden muss
                    int l = k; //Speicher den Indize des einzusortierenden Elementes
                    while (l > 0 && arr[l - 1] > s) { //überprüfe solange die vorhergehenden elemente bis das zu vergleichende nicht mehr größer ist als das vorherige
                        arr[l] = arr[l - 1]; //dabei verschiebe ich die Elemente die größer sind immer um einen Platz nach vorne
                        l = l - 1; //verkleinere den Indize des zu vergleichenden Elementes
                    }
                    arr[l] = s;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void bubbleSortGen(T[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T n = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = n;
                }
            }
        }
    }

    public static void merge(int[] arr, int left, int middle, int right) {
        //initialisieren linker und rechter Arrays
        int lengthLeft = middle - left + 1;
        int lengthRight = right - middle;
        int[] leftHalf = new int[lengthLeft + 1];
        int[] rightHalf = new int[lengthRight + 1];

        for (int i = 0; i < lengthLeft; i++) {
            leftHalf[i] = arr[middle + i - 1];
        }

        leftHalf[leftHalf.length - 1] = Integer.MAX_VALUE;

        for (int j = 0; j < lengthRight; j++) {
            rightHalf[j] = arr[middle + j];
        }

        rightHalf[rightHalf.length - 1] = Integer.MAX_VALUE;

        int pointerLeft = 0;
        int pointerRight = 0;

        for (int k = left; k < right; k++) {
            if (leftHalf[pointerLeft] <= rightHalf[pointerRight]) {
                arr[k] = leftHalf[pointerLeft];
                pointerLeft = pointerLeft + 1;
            } else {
                arr[k] = rightHalf[pointerRight];
                pointerRight = pointerRight + 1;
            }
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }

    public static void mergeNew(int[] arr, int left, int middleLeft, int middelRight, int right) {
        //initialisieren linker und rechter Arrays
        int lengthFirst = middleLeft - left + 1;
        int lengthMidlle = middelRight - middleLeft;
        int lengthLast = right - middelRight;
        int[] First = new int[lengthFirst + 1];
        int[] Midlle = new int[lengthMidlle + 1];
        int[] Last = new int[lengthLast + 1];

        for (int i = 0; i < lengthFirst; i++) {
            First[i] = arr[middleLeft + i - 1];
        }

        First[First.length - 1] = Integer.MAX_VALUE;

        for (int j = 0; j < lengthMidlle; j++) {
            Midlle[j] = arr[middleLeft + j];
        }

        Midlle[Midlle.length - 1] = Integer.MAX_VALUE;

        for (int k = 0; k < lengthLast; k++) {
            Last[k] = arr[middelRight + k];
        }

        Last[Last.length - 1] = Integer.MAX_VALUE;

        int pointerLeft = 0;
        int pointerMiddle = 0;
        int pointerRight = 0;

        for (int k = left; k < right; k++) {
            if (First[pointerLeft] <= Midlle[pointerMiddle] && First[pointerLeft] <= Last[pointerRight]) {
                arr[k] = First[pointerLeft];
                pointerLeft = pointerLeft + 1;
            }
            if (Midlle[pointerMiddle] <= First[pointerLeft] && Midlle[pointerMiddle] <= Last[pointerRight]) {
                arr[k] = Midlle[pointerMiddle];
                pointerMiddle = pointerMiddle + 1;
            } else {
                arr[k] = Last[pointerRight];
                pointerRight = pointerRight + 1;
            }
        }
    }


    public static void mergeSortNew(int[] arr, int left, int right) {
        if (left < right) {
            int middleLeft = (right - left) / 3 + left;//warum nicht (right + left)/3
            int middleRight = 2 * middleLeft;
            mergeSortNew(arr, left, middleLeft);
            mergeSortNew(arr, middleLeft, middleRight);
            mergeSortNew(arr, middleRight, right);
            mergeNew(arr, left, middleLeft, middleRight, right);
        }
    }


    /*
        public static <T extends Comparable<T>> void mergeGen(T[] arr, int left, int middle, int right){
            //initialisieren linker und rechter Arrays
            int lengthLeft = middle - left + 1;
            int lengthRight = right - middle;
            T[] leftHalf = new Comparable[lengthLeft + 1];
            T[] rightHalf = new Comparable[lengthRight +1];

            for(int i = 0; i < lengthLeft; i++){
                leftHalf[i] = arr[middle + i - 1];
            }

            leftHalf[leftHalf.length - 1] = null;

            for(int j = 0; j < lengthRight; j++){
                rightHalf[j] = arr[middle + j];
            }

            rightHalf[rightHalf.length - 1] = null;

            int pointerLeft = 0;
            int pointerRight = 0;

            for(int k = left; k < right; k++){
                if(leftHalf[pointerLeft] <= rightHalf[pointerRight]){
                    arr[k] = leftHalf[pointerLeft];
                    pointerLeft = pointerLeft + 1;
                } else {
                    arr[k] = rightHalf[pointerRight];
                    pointerRight = pointerRight + 1;
                }
            }
        }
        }
    */
    public static int linSearch(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static int binSearch(int[] A, int x, int l, int r) {

        int a = (l + r) / 2;
        if (x == A[a]) {
            return a;
        } else if (x < A[a]) {
            binSearch(A, x, l, a - 1);
        } else {
            binSearch(A, x, a + 1, r);
        }
        return -1;
    }

    public static int binSearchNew(int[] A, int x, int l, int r) {

        int al = (r - l) / 3 + l;
        int ar = al * 2;
        if (x == A[al]) {
            return al;
        } else if (x == A[ar]) {
            return ar;
        } else if (x < A[ar]) {
            if (x < A[al]) {
                binSearchNew(A, x, l, al - 1);
            } else {
                binSearchNew(A, x, al + 1, ar - 1);
            }
        } else {
            binSearchNew(A, x, ar + 1, r);
        }

        return -1;
    }


    public static void main(String[] args) {
        int repeats = 500;
        List<BiFunction<int[], Integer, Integer>> searches = List.of(SearchTools::linSearch, SearchTools::binSearch, SearchTools::binSearchNew);
        List<Integer> arrays = List.of(100000, 1000000, 100000000, 685154321);
        List<Function<Integer, Integer>> targetSupplier = List.of(x -> new Random().nextInt(x) + 1, x -> -10);
        long[][][] times = new long[repeats][searches.size()][arrays.size()];

        //Messung
        for (int i = 0; i < repeats; i++) {
            System.out.println("repeat: " + i);
            for (int j = 0; j < searches.size(); j++) {
                for (int k = 0; k < arrays.size(); k++) {
                    for (int l = 0; l < targetSupplier.size(); l++) {
                        int[] temp = createSequenceInc(arrays.get(k));
                        long currentTime = System.nanoTime();
                        searches.get(j).apply(temp, targetSupplier.get(l).apply(temp.length));
                        times[i][j][k] = System.nanoTime() - currentTime;
                    }
                }
            }
        }

        //evaluation
        String[] supplier = {"Random", "-10"};
        int indexSupplier = 0;
        String[] search = {"Linear", "Binary", "BinaryNew"};
        int indexSearch = 0;

        for (int l = 0; l < targetSupplier.size(); l++) {
            System.out.println("Target: " + supplier[indexSupplier++]);
            for (int j = 0; j < searches.size(); j++) {
                System.out.println("\tSearch: " + search[indexSearch++ % 3]);
                for (int k = 0; k < arrays.size(); k++) {
                    System.out.print("\t\tArray: " + arrays.get(k) + "\t: ");
                    long sum = 0;
                    for (int i = 0; i < repeats; i++) {
                        sum += times[i][j][k];
                    }
                    System.out.println(sum / repeats * Math.pow(10, -9));
                }
            }
        }
    }
}
/*
Target: Random
        Search: Linear
        Array: 100000	: 2.1769E-5
        Array: 1000000	: 8.427370000000001E-4
        Array: 100000000	: 0.031563363000000004
        Array: 685154321	: 0.23126057000000003
        Search: Binary
        Array: 100000	: 1.333E-6
        Array: 1000000	: 8.931000000000001E-6
        Array: 100000000	: 8.272E-6
        Array: 685154321	: 2.1783E-5
        Search: BinaryNew
        Array: 100000	: 9.240000000000001E-7
        Array: 1000000	: 9.556000000000001E-6
        Array: 100000000	: 8.144E-6
        Array: 685154321	: 2.1715E-5
        Target: -10
        Search: Linear
        Array: 100000	: 2.1769E-5
        Array: 1000000	: 8.427370000000001E-4
        Array: 100000000	: 0.031563363000000004
        Array: 685154321	: 0.23126057000000003
        Search: Binary
        Array: 100000	: 1.333E-6
        Array: 1000000	: 8.931000000000001E-6
        Array: 100000000	: 8.272E-6
        Array: 685154321	: 2.1783E-5
        Search: BinaryNew
        Array: 100000	: 9.240000000000001E-7
        Array: 1000000	: 9.556000000000001E-6
        Array: 100000000	: 8.144E-6
        Array: 685154321	: 2.1715E-5

*/



// insertionSort: 0,850781
//bubbleSort: 1,773829
//bubbleSortNew: 43,308113*/