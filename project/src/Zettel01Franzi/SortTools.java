package Zettel01Franzi;

import Zettel04Bela.SearchTools;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SortTools {

    @FunctionalInterface
    public interface TriConsumer<T, U, V> {
        public void accept(T t, U u, V v);

        public default Zettel06Bela.SortTools.TriConsumer<T, U, V> andThen(Zettel06Bela.SortTools.TriConsumer<? super T, ? super U, ? super V> after) {
            Objects.requireNonNull(after);
            return (a, b, c) -> {
                accept(a, b, c);
                after.accept(a, b, c);
            };
        }
    }

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
    public static void quickSort(int[] A, int l, int r){
        if( r > l){
            int q = partition(A, l, r);
            quickSort(A, l, q-1);
            quickSort(A, q +1, r);
        }
    }

    public static int partition(int[] A, int l, int r){
        int pivot = A[l];
        int i = l + 1  ; //beide Zeiger starten beim gleichen Index
        for( int j = l + 1 ; j <= r; j++) { //gehen bis zum Index des letzten Elementes
            if (A[j] <= pivot) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++; //hochsetzten des Zeiges wo die Elemente hingetauscht werden können
            }
        }
        int temp = A[i-1];
        A[i-1] = A[l];
        A[l] = temp;
        return i-1;

    }

    public static void quickSortRandom(int[] A, int l, int r){
        if( r > l){
            int q = partitionRandom(A, l, r);
            quickSortRandom(A, l, q-1);
            quickSortRandom(A, q +1, r);
        }
    }

    public static int partitionRandom(int[] A, int l, int r){
        int randInt = new Random().nextInt(l,r+1);
        int temp = A[randInt];
        A[randInt] = A[l];
        A[l] = temp;
        return partition(A, l, r);
    }

    public static void quickSortNewRandom(int[] A, int l, int r){
        if( r > l){
            int q = partitionNewRandom(A, l, r);
                quickSortNewRandom(A, l, q-1);
                quickSortNewRandom(A, q + 1, r);
            }
    }


    public static int partitionNewRandom( int[] A, int l, int r){
        int[] randInts = new int[3];
        randInts[0] = new Random().nextInt(l,r+1);
        randInts[1] = new Random().nextInt(l,r+1);
        randInts[2] = new Random().nextInt(l,r+1);
        Arrays.sort(randInts);
        int pivot = A[randInts[1]];
        int temp = A[pivot];
        A[pivot] = A[l];
        A[l] = temp;
        return partition(A, l, r);

    }


    public static void quickSortTriRandom(int[] A, int l, int r){
        if (l < r) {
            if (r - l == 1) {
                if (A[l] > A[r]) {
                    int temp = A[l];
                    A[l] = A[r];
                    A[r] = temp;
                }
                return;
            }
            int[] pivots = partitionTriRandom(A,l,r);
            quickSortTriRandom(A, l, pivots[0] - 1);
            quickSortTriRandom(A, pivots[0] + 1, pivots[1] - 1);
            quickSortTriRandom(A, pivots[1] + 1, r);
        }
    }
    public static int[] partitionTriRandom(int[] A, int l, int r){
        int pivot1 = new Random().nextInt(l,r+1);
        int pivot2 = new Random().nextInt(l,r+1);
        while(pivot1 == pivot2){
            pivot2 = new Random().nextInt(l, r+1);
        }

        if (A[pivot1] > A[pivot2]) {
            int temp = pivot1;
            pivot1 = pivot2;
            pivot2 = temp;
        }



        if(A[pivot1] == l){
            int temp = A[l+1];
            A[l+1] = A[pivot2];
            A[pivot2] = temp;
        } else {
            int temp1 = A[l];
            A[l] = A[pivot1];
            A[pivot1] = temp1;
            int temp2 = A[l+1];
            A[l+1] = A[pivot2];
            A[pivot2] = temp2; //dann steht das kleinere Pivot Element am Anfang vom Array und das zweite an zweiter Stelle
        }

        int position1 = partition(A,l+1, r);
        int position2 = partition(A, l, position1 - 1);
        return new int[]{position2, position1};


    }

    public static void quickSortTriNewRandom(int[] A, int l, int r){
        if (l < r) {
            if (r - l == 1) {
                if (A[l] > A[r]) {
                    int temp = A[l];
                    A[l] = A[r];
                    A[r] = temp;
                }
                return;
            }
            int[] pivots = partitionTriNewRandom(A,l,r);
            quickSortTriNewRandom(A, l, pivots[0] - 1);
            quickSortTriNewRandom(A, pivots[0] + 1, pivots[1] - 1);
            quickSortTriNewRandom(A, pivots[1] + 1, r);
        }
    }

    public static int[] partitionTriNewRandom(int[] A, int l, int r){
        int[] pivots = new int[5];
        pivots[0] = new Random().nextInt(l,r+1);
        pivots[1] = new Random().nextInt(l,r+1);
        pivots[2] = new Random().nextInt(l,r+1);
        pivots[3] = new Random().nextInt(l,r+1);
        pivots[4] = new Random().nextInt(l,r+1);

        int pivot1 = pivots[1];
        int pivot2 = pivots[3];

        while(pivot1 == pivot2){
            pivot2 = new Random().nextInt(l, r+1);
        }

        if (A[pivot1] > A[pivot2]) {
            int temp = pivot1;
            pivot1 = pivot2;
            pivot2 = temp;
        }



        if(A[pivot1] == l){
            int temp = A[l+1];
            A[l+1] = A[pivot2];
            A[pivot2] = temp;
        } else {
            int temp1 = A[l];
            A[l] = A[pivot1];
            A[pivot1] = temp1;
            int temp2 = A[l+1];
            A[l+1] = A[pivot2];
            A[pivot2] = temp2; //dann steht das kleinere Pivot Element am Anfang vom Array und das zweite an zweiter Stelle
        }

        int position1 = partition(A,l+1, r);
        int position2 = partition(A, l, position1 - 1);
        return new int[]{position2, position1};

    }

    public static void main(String[] args) {

        int[] test = createSequenceInc(50);
        quickSortTriRandom(test, 0, 49);
/*
        int repeats = 10;
        List<TriConsumer<int[], Integer, Integer>> sorts = List.of(SortTools::quickSort, SortTools::quickSortRandom, SortTools::quickSortNewRandom, SortTools::quickSortTriRandom, SortTools::quickSortTriNewRandom);
        List<Integer> arraySizes = List.of(100, 1000, 10000, 100000, 200000);
        List<Function<Integer, int[]>> sequenceCreators = List.of(SortTools::createSequenceInc, SortTools::createSequenceDec, SortTools::createSequenceRand);
        long[][][][] times = new long[repeats][sorts.size()][arraySizes.size()][sequenceCreators.size()];

        //Messung
        for (int i = 0; i < repeats; i++) {
            System.out.println("repeat: " + i);
            for (int j = 0; j < sorts.size(); j++) {
                for (int k = 0; k < arraySizes.size(); k++) {
                    for (int l = 0; l < sequenceCreators.size(); l++) {
                        long currentTime = System.nanoTime();
                        sorts.get(j).accept(sequenceCreators.get(l).apply(arraySizes.get(k)), 0, arraySizes.get(k) - 1);
                        times[i][j][k][l] = System.nanoTime() - currentTime;
                    }
                }
            }
        }

        //evaluation
        String[] sortNames = {"quickSort", "quickSortRandom", "quickSortNewRandom", "quickSortTriRandom", "quickSortTriNewRandom"};
        String[] arraySizeNames = {"100", "1000", "10000", "100000", "200000"};
        String[] sequenceNames = {"increasing", "decreasing", "random"};

        for (int l = 0; l < sequenceCreators.size(); l++) {
            System.out.println("\n" + sequenceNames[l]);
            for (int j = 0; j < sorts.size(); j++) {
                System.out.println("\t" + sortNames[j]);
                for (int k = 0; k < arraySizes.size(); k++) {
                    System.out.print("\t\t" + arraySizeNames[k] + ":");
                    long sum = 0;
                    for (int i = 0; i < repeats; i++) {
                        sum += times[i][j][k][l];
                    }
                    System.out.println("\t\t" + sum / repeats * Math.pow(10, -9));
                }
            }
        }*/
    }




}



// insertionSort: 0,850781
//bubbleSort: 1,773829
//bubbleSortNew: 43,308113*/