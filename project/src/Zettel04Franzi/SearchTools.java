package Zettel04Franzi;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SearchTools {
    public static int linSearch(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == x) {
                return i;
            }
        }
        return -1;
    }


    public static int binSearch(int[] A, int x, int l, int r) {
        if(l > r){
            return -1;
        }
        int a = (l + r) / 2;
        if (x == A[a]) {
            return a;
        } else if (x < A[a]) {
            return binSearch(A, x, l, a - 1);
        } else {
            return binSearch(A, x, a + 1, r);
        }
    }

    public static int binSearchNew(int[] A, int x, int l, int r) {
        if(l > r){
            return -1;
        }
        int al = (r - l) / 3 + l;
        int ar = al + (r - l) / 3;
        if (x == A[al]) {
            return al;
        } else if (x == A[ar]) {
            return ar;
        } else if (x < A[ar]) {
            if (x < A[al]) {
                return binSearchNew(A, x, l, al - 1);
            } else {
                return binSearchNew(A, x, al + 1, ar - 1);
            }
        } else {
            return binSearchNew(A, x, ar + 1, r);
        }

    }
    public static void main(String[] args) {
        int repeats = 500;
        List<BiFunction<int[], Integer, Integer>> searches = List.of(Zettel04Bela.SearchTools::linSearch, Zettel04Bela.SearchTools::binSearch, Zettel04Bela.SearchTools::binSearchNew);
        List<Integer> arrays = List.of(100000, 1000000, 100000000, 685154321);
        List<Function<Integer, Integer>> targetSupplier = List.of(x -> new Random().nextInt(x) + 1, x -> -10);
        long[][][] times = new long[repeats][searches.size()][arrays.size()];

        //Messung
        for (int i = 0; i < repeats; i++) {
            System.out.println("repeat: " + i);
            for (int j = 0; j < searches.size(); j++) {
                for (int k = 0; k < arrays.size(); k++) {
                    for (int l = 0; l < targetSupplier.size(); l++) {
                        int[] temp = Zettel01Franzi.SortTools.createSequenceInc(arrays.get(k));
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
		Array: 100000	: 5.6518E-5
		Array: 1000000	: 8.79524E-4
		Array: 100000000	: 0.07293152400000001
		Array: 685154321	: 0.503368291
	Search: Binary
		Array: 100000	: 2.951E-6
		Array: 1000000	: 1.2398E-5
		Array: 100000000	: 1.2041E-5
		Array: 685154321	: 3.1455E-5
	Search: BinaryNew
		Array: 100000	: 2.119E-6
		Array: 1000000	: 1.2153E-5
		Array: 100000000	: 1.2347E-5
		Array: 685154321	: 3.2049000000000004E-5
Target: -10
	Search: Linear
		Array: 100000	: 5.6518E-5
		Array: 1000000	: 8.79524E-4
		Array: 100000000	: 0.07293152400000001
		Array: 685154321	: 0.503368291
	Search: Binary
		Array: 100000	: 2.951E-6
		Array: 1000000	: 1.2398E-5
		Array: 100000000	: 1.2041E-5
		Array: 685154321	: 3.1455E-5
	Search: BinaryNew
		Array: 100000	: 2.119E-6
		Array: 1000000	: 1.2153E-5
		Array: 100000000	: 1.2347E-5
		Array: 685154321	: 3.2049000000000004E-5


*/

