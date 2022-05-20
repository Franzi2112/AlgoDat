package Zettel06Bela;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class SortTools {
    //https://github.com/ddickstein/Java-Library/blob/master/java8/function/TriConsumer.java
    @FunctionalInterface
    public interface TriConsumer<T, U, V> {
        public void accept(T t, U u, V v);

        public default TriConsumer<T, U, V> andThen(TriConsumer<? super T, ? super U, ? super V> after) {
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
        for (int i = 0; i < n; i++) {
            descending[i] = n - i;
        }
        return descending;
    }

    public static int[] createSequenceRand(int n) {
        int[] random = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            random[i] = r.nextInt(1,n+1);
        }
        return random;
    }

    public static void quickSort(int[] A, int l, int r) {
        if (l < r) {
            int p = partition(A, l, r);
            quickSort(A, l, p - 1);
            quickSort(A, p + 1, r);
        }
    }

    private static int partition(int[] A, int l, int r) {
        int pivot = A[l];
        int i = l+1;
        for (int j = l+1; j <= r; j++) {
            if (A[j] <= pivot) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
            }
        }
        int temp = A[l];
        A[l] = A[i-1];
        A[i-1] = temp;
        return i-1;
    }

    public static void quickSortRandom(int[] A, int l, int r) {
        if (l < r) {
            int pivot = partitionRandom(A,l,r);
            quickSortRandom(A, l, pivot - 1);
            quickSortRandom(A, pivot + 1, r);
        }
    }

    private static int partitionRandom(int[] A, int l, int r) {
        int randInt = new Random().nextInt(l,r);
        int pivot = A[randInt];
        A[randInt] = A[l];
        A[l] = pivot;
        int i = l+1;
        for (int j = l+1; j <= r; j++) {
            if (A[j] <= pivot) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
            }
        }
        int temp = A[l];
        A[l] = A[i-1];
        A[i-1] = temp;
        return i-1;
    }

    public static void quickSortNewRandom(int[] A, int l, int r) {
        if (l < r) {
            int pivot = partitionNewRandom(A,l,r);
            quickSortNewRandom(A, l, pivot - 1);
            quickSortNewRandom(A, pivot + 1, r);
        }
    }

    private static int partitionNewRandom(int[] A, int l, int r) {
        int[] randInts = new int[3];
        randInts[0] = new Random().nextInt(l,r);
        randInts[1] = new Random().nextInt(l,r);
        randInts[2] = new Random().nextInt(l,r);
        Arrays.sort(randInts);
        int pivot = A[randInts[1]];
        A[randInts[1]] = A[l];
        A[l] = pivot;
        int i = l+1;
        for (int j = l+1; j <= r; j++) {
            if (A[j] <= pivot) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
            }
        }
        int temp = A[l];
        A[l] = A[i-1];
        A[i-1] = temp;
        return i-1;
    }

    public static void quickSortTriRandom(int[] A, int l, int r) {
        if (l < r) {
            if (r-l<=1) {
            if (A[l]>A[r]) {
                int temp = A[l];
                A[l] = A[r];
                return;
            }
        }
            int[] pivots = partitionTriRandom(A,l,r);
            quickSortTriRandom(A, l, pivots[0] - 1);
            quickSortTriRandom(A, pivots[0] + 1, pivots[1] - 1);
            quickSortTriRandom(A, pivots[1] + 1, r);
        }
    }

    private static int[] partitionTriRandom(int[] A, int l, int r) {
        //create 2 pivots
        int pivot2;
        int pivot1 = pivot2 = new Random().nextInt(l, r);
        while (pivot2 == pivot1) {
            pivot2 = new Random().nextInt(l, r);
        }
        //sort pivots
        if (A[pivot1] > A[pivot2]) {
            int temp = pivot1;
            pivot1 = pivot2;
            pivot2 = temp;
        }
        //swap pivots to the front
        int temp1 = A[pivot1];
        int temp2 = A[pivot2];
        A[pivot1] = A[l];
        A[pivot2] = A[l + 1];
        A[l] = temp1;
        A[l + 1] = temp2;

        //partition
        int i = l+2;
        for (int j = l+2; j <= r; j++) {
            if (A[j] <= A[l]) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
            }
        }
        temp1 = A[l];
        temp2 = A[l+1];
        A[l] = A[i-2];
        A[l+1] = A[i-1];
        A[i-2] = temp1;
        A[i-1] = temp2;
        //mark second pivot
        int mark = i-1;

        for (int j = i+1; j <= r; j++) {
            if (A[j] <= A[mark]) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
            }
        }
        temp1 = A[mark];
        A[mark] = A[i-1];
        A[i-1] = temp1;
        return new int[]{mark-1, i-1};
    }

    public static void quickSortTriNewRandom(int[] A, int l, int r) {
        if (l < r) {
            if (r-l<=1) {
            if (A[l]>A[r]) {
                int temp = A[l];
                A[l] = A[r];
                return;
            }
        }
            int[] pivots = partitionTriNewRandom(A,l,r);
            quickSortTriNewRandom(A, l, pivots[0] - 1);
            quickSortTriNewRandom(A, pivots[0] + 1, pivots[1] - 1);
            quickSortTriNewRandom(A, pivots[1] + 1, r);
        }
    }

    private static int[] partitionTriNewRandom(int[] A, int l, int r) {
        //create 2 random pivots
        int[] pivots = new int[5];
        int pivot1=1;
        int pivot2=1;
        while(pivot1 == pivot2) {
            for (int i = 0; i < 5; i++) {
                pivots[i] = new Random().nextInt(l, r);
            }
            Arrays.sort(pivots);
            pivot1 = pivots[1];
            pivot2 = pivots[3];
        }
        //sort pivots
        if (A[pivot1] > A[pivot2]) {
            int temp = pivot1;
            pivot1 = pivot2;
            pivot2 = temp;
        }
        //swap pivots to the front
        int temp1 = A[pivot1];
        int temp2 = A[pivot2];
        A[pivot1] = A[l];
        A[pivot2] = A[l + 1];
        A[l] = temp1;
        A[l + 1] = temp2;

        //partition
        int i = l+2;
        for (int j = l+2; j <= r; j++) {
            if (A[j] <= A[l]) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
            }
        }
        temp1 = A[l];
        temp2 = A[l+1];
        A[l] = A[i-2];
        A[l+1] = A[i-1];
        A[i-2] = temp1;
        A[i-1] = temp2;
        //mark second pivot
        int mark = i-1;

        for (int j = i+1; j <= r; j++) {
            if (A[j] <= A[l]) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
            }
        }
        temp1 = A[mark];
        A[mark] = A[i-1];
        A[i-1] = temp1;
        return new int[]{mark-1, i-1};
    }

    public static void main(String[] args) {
        int[] arr = {1,2,8,3,7,456,7,2,423,6747,8,5,25,4,79,47};
        List<TriConsumer<int[],Integer,Integer>> sorts = List.of(SortTools::quickSortTriRandom, SortTools::quickSortTriNewRandom);
        for (TriConsumer<int[],Integer,Integer> sort : sorts) {
            int[] arrCopy = Arrays.copyOf(arr, arr.length);
            sort.accept(arrCopy, 0, arrCopy.length-1);
            System.out.println(Arrays.toString(arrCopy));
        }



        /*
        int repeats= 10;
        List<Function<Integer,int[]>> sequenceCreators = List.of(SortTools::createSequenceInc, SortTools::createSequenceDec, SortTools::createSequenceRand);
        List<TriConsumer<int[],Integer,Integer>> sorts = List.of(SortTools::quickSort, SortTools::quickSortRandom, SortTools::quickSortNewRandom,SortTools::quickSortTriRandom, SortTools::quickSortTriNewRandom);
        List<Integer> arrays= List.of(100,1000,10000,100000,200000);
        long[][][][] times = new long[repeats][sequenceCreators.size()][sorts.size()][arrays.size()];

        //Messung
        for (int i = 0; i < repeats; i++) {
            System.out.println("repeat: "+i);
            for (int j = 0; j < sorts.size(); j++) {
                for (int k = 0; k < arrays.size(); k++) {
                    for (int l = 0; l < targetSupplier.size(); l++) {
                        int[] temp = createSequenceInc(arrays.get(k));
                        long currentTime = System.nanoTime();
                        sorts.get(j).accept(temp, targetSupplier.get(l).apply(temp.length));
                        times[i][j][k] = System.nanoTime() - currentTime;
                    }
                }
            }
        }

        //evaluation
        String[] supplier= {"Random","-10"};
        int indexSupplier=0;
        String[] search= {"Linear","Binary","BinaryNew"};
        int indexSearch=0;

        for (int l = 0; l < targetSupplier.size(); l++) {
            System.out.println("Target: "+supplier[indexSupplier++]);
            for (int j = 0; j < sorts.size(); j++) {
                System.out.println("\tSearch: "+search[indexSearch++%3]);
                for (int k = 0; k < arrays.size(); k++) {
                    System.out.print("\t\tArray: "+arrays.get(k)+"\t: ");
                    long sum = 0;
                    for (int i = 0; i < repeats; i++) {
                        sum += times[i][j][k];
                    }
                    System.out.println(sum/repeats*Math.pow(10, -9));
                }
            }
        }*/


    }
}
/*
Target: Random
	Search: Linear
		Array: 100000	: 2.6397E-5
		Array: 1000000	: 7.23166E-4
		Array: 100000000	: 0.028914579000000003
		Array: 685154321	: 0.198568984
	Search: Binary
		Array: 100000	: 1.5300000000000002E-6
		Array: 1000000	: 8.63E-6
		Array: 100000000	: 7.528000000000001E-6
		Array: 685154321	: 1.9303E-5
	Search: BinaryNew
		Array: 100000	: 1.063E-6
		Array: 1000000	: 8.539E-6
		Array: 100000000	: 9.181E-6
		Array: 685154321	: 1.9203000000000002E-5
Target: -10
	Search: Linear
		Array: 100000	: 2.6397E-5
		Array: 1000000	: 7.23166E-4
		Array: 100000000	: 0.028914579000000003
		Array: 685154321	: 0.198568984
	Search: Binary
		Array: 100000	: 1.5300000000000002E-6
		Array: 1000000	: 8.63E-6
		Array: 100000000	: 7.528000000000001E-6
		Array: 685154321	: 1.9303E-5
	Search: BinaryNew
		Array: 100000	: 1.063E-6
		Array: 1000000	: 8.539E-6
		Array: 100000000	: 9.181E-6
		Array: 685154321	: 1.9203000000000002E-5
 */