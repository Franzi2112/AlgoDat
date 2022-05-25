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
        int randInt = new Random().nextInt(l,r+1);
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
        randInts[0] = new Random().nextInt(l,r+1);
        randInts[1] = new Random().nextInt(l,r+1);
        randInts[2] = new Random().nextInt(l,r+1);
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
            if (r-l==1) {
                if (A[l]>A[r]) {
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

    private static int[] partitionTriRandom(int[] A, int l, int r) {
        //create 2 random pivots
        int pivot2;
        int pivot1 = pivot2 = new Random().nextInt(l, r+1);
        while (pivot2 == pivot1) {
            pivot2 = new Random().nextInt(l, r+1);
        }
        //sort pivots
        if (A[pivot1] < A[pivot2]) {
            int temp = pivot1;
            pivot1 = pivot2;
            pivot2 = temp;
        }


        //swap pivots to the front
        int temp1,temp2;

        if (pivot2==l){
            temp1 = A[pivot1];
            A[pivot1] = A[l];
            A[l] = temp1;

            temp2 = A[pivot1];
            A[pivot1] = A[l+1];
            A[l+1] = temp2;
        } else {

            temp1 = A[pivot1];
            temp2 = A[pivot2];
            A[pivot1] = A[l];
            A[pivot2] = A[l + 1];
            A[l] = temp1;
            A[l + 1] = temp2;
        }


        //partition 1 (pivot2, the smaller one, at A[l+1])
        int mark1 = partition(A, l+1, r);

        //swap in pivot1
        if (mark1-1==l) {
            temp1 = A[l];
            A[l] = A[mark1];
            A[mark1] = temp1;
        }
        else {
            temp1 = A[mark1];
            temp2 = A[mark1-1];
            A[mark1] = A[l];
            A[l] = temp2;
            A[mark1-1] = temp1;
        }

        //partition 2 (pivot1, the larger one, at A[mark1])
        int mark2 = partition(A, mark1, r);

        return new int[]{mark1-1, mark2};
    }

    public static void quickSortTriNewRandom(int[] A, int l, int r) {
        if (l < r) {
            if (r-l==1) {
                if (A[l]>A[r]) {
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

    private static int[] partitionTriNewRandom(int[] A, int l, int r) {
        //create 2 random pivots
        int[] pivots = new int[5];
        int pivot1=1;
        int pivot2=1;
        while(pivot1 == pivot2) {
            for (int i = 0; i < 5; i++) {
                pivots[i] = new Random().nextInt(l, r+1);
            }
            Arrays.sort(pivots);
            pivot1 = pivots[1];
            pivot2 = pivots[3];
        }

        //sort pivots
        if (A[pivot1] < A[pivot2]) {
            int temp = pivot1;
            pivot1 = pivot2;
            pivot2 = temp;
        }


        //swap pivots to the front
        int temp1,temp2;

        if (pivot2==l){
            temp1 = A[pivot1];
            A[pivot1] = A[l];
            A[l] = temp1;

            temp2 = A[pivot1];
            A[pivot1] = A[l+1];
            A[l+1] = temp2;
        } else {

            temp1 = A[pivot1];
            temp2 = A[pivot2];
            A[pivot1] = A[l];
            A[pivot2] = A[l + 1];
            A[l] = temp1;
            A[l + 1] = temp2;
        }


        //partition 1 (pivot2, the smaller one, at A[l+1])
        int mark1 = partition(A, l+1, r);

        //swap in pivot1
        if (mark1-1==l) {
            temp1 = A[l];
            A[l] = A[mark1];
            A[mark1] = temp1;
        }
        else {
            temp1 = A[mark1];
            temp2 = A[mark1-1];
            A[mark1] = A[l];
            A[l] = temp2;
            A[mark1-1] = temp1;
        }

        //partition 2 (pivot1, the larger one, at A[mark1])
        int mark2 = partition(A, mark1, r);

        return new int[]{mark1-1, mark2};
    }


    public static void main(String[] args) {

        int repeats= 10;
        List<TriConsumer<int[],Integer,Integer>> sorts = List.of(SortTools::quickSort, SortTools::quickSortRandom, SortTools::quickSortNewRandom,SortTools::quickSortTriRandom, SortTools::quickSortTriNewRandom);
        List<Integer> arraySizes= List.of(100,1000,10000,100000,200000);
        List<Function<Integer,int[]>> sequenceCreators = List.of(SortTools::createSequenceInc, SortTools::createSequenceDec, SortTools::createSequenceRand);
        long[][][][] times = new long[repeats][sorts.size()][arraySizes.size()][sequenceCreators.size()];

        //Messung
        for (int i = 0; i < repeats; i++) {
            System.out.println("repeat: "+i);
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

        for (int l=0;l<sequenceCreators.size();l++){
            System.out.println("\n"+sequenceNames[l]);
            for (int j=0;j<sorts.size();j++){
                System.out.println("\t"+sortNames[j]);
                for (int k=0;k<arraySizes.size();k++){
                    System.out.print("\t\t"+arraySizeNames[k]+":");
                    long sum = 0;
                    for (int i=0;i<repeats;i++){
                        sum += times[i][j][k][l];
                    }
                    System.out.println("\t\t"+sum/repeats*Math.pow(10,-9));
                }
            }
        }



    }
}
/*
increasing
	quickSort
		100:		1.596E-5
		1000:		7.0098E-4
		10000:		0.024483920000000003
		100000:		2.4337660100000003
		200000:		9.47106009
	quickSortRandom
		100:		2.8230000000000002E-5
		1000:		1.2880000000000001E-4
		10000:		7.4388E-4
		100000:		0.00763035
		200000:		0.01359305
	quickSortNewRandom
		100:		6.0060000000000004E-5
		1000:		2.0853E-4
		10000:		0.00134326
		100000:		0.01296485
		200000:		0.025967120000000003
	quickSortTriRandom
		100:		1.861E-5
		1000:		9.864E-5
		10000:		7.6866E-4
		100000:		0.0074722700000000005
		200000:		0.015399870000000001
	quickSortTriNewRandom
		100:		2.474E-5
		1000:		1.4857000000000002E-4
		10000:		0.0012626800000000002
		100000:		0.01317747
		200000:		0.025518950000000002

decreasing
	quickSort
		100:		2.682E-5
		1000:		5.854300000000001E-4
		10000:		0.031237720000000004
		100000:		3.0894138
		200000:		11.90747026
	quickSortRandom
		100:		1.485E-5
		1000:		1.0919E-4
		10000:		7.427400000000001E-4
		100000:		0.007357590000000001
		200000:		0.014570930000000001
	quickSortNewRandom
		100:		2.519E-5
		1000:		1.6662E-4
		10000:		0.0017220500000000001
		100000:		0.014154080000000001
		200000:		0.02932993
	quickSortTriRandom
		100:		9.56E-6
		1000:		8.699E-5
		10000:		8.086E-4
		100000:		0.00801658
		200000:		0.01674331
	quickSortTriNewRandom
		100:		1.719E-5
		1000:		1.4986E-4
		10000:		0.0015688100000000001
		100000:		0.01482989
		200000:		0.02735032

random
	quickSort
		100:		4.838E-5
		1000:		9.969E-5
		10000:		9.3627E-4
		100000:		0.00904765
		200000:		0.01803139
	quickSortRandom
		100:		1.357E-5
		1000:		1.1502E-4
		10000:		0.00140827
		100000:		0.01244273
		200000:		0.024784880000000002
	quickSortNewRandom
		100:		3.156E-5
		1000:		1.8001E-4
		10000:		0.00200056
		100000:		0.01953904
		200000:		0.040227120000000005
	quickSortTriRandom
		100:		1.1370000000000001E-5
		1000:		1.0479E-4
		10000:		0.0010945800000000002
		100000:		0.01272811
		200000:		0.02618699
	quickSortTriNewRandom
		100:		1.8220000000000002E-5
		1000:		1.7014000000000002E-4
		10000:		0.00185913
		100000:		0.01899969
		200000:		0.03836863

 */