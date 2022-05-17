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


}



// insertionSort: 0,850781
//bubbleSort: 1,773829
//bubbleSortNew: 43,308113*/