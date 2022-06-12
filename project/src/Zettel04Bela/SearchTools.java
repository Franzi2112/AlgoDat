package Zettel04Bela;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SearchTools {

    public static int[] createSequenceInc(int n) {
        int[] ascending = new int[n];
        for (int i = 0; i < n; i++) {
            ascending[i] = i + 1;
        }
        return ascending;
    }

    public static int linSearch(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == x) {
                return i;
            }
        }
        return -1;
    }
    public static int binSearch(int[] A, int x) {
        int low = 0;
        int high = A.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (A[mid] == x) {
                return mid;
            }
            if (A[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    public static int binSearchNew(int[] A, int x) {
        int low = 0;
        int high = A.length - 1;
        while (low <= high) {
            int leftMid = (low + high) / 3;
            int rightMid = (high-low) * 2 / 3+low;
            //check hits
            if (A[leftMid] == x) {
                return leftMid;
            }
            if (A[rightMid] == x) {
                return rightMid;
            }
            //first bracket
            if (A[leftMid] > x) {
                high = leftMid - 1;
            //second bracket
            } else if (A[rightMid] > x) {
                high = rightMid - 1;
                low = leftMid + 1;
            //third bracket
            } else {
                low = rightMid + 1;
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
        int repeats= 500;
        List<BiFunction<int[], Integer, Integer>> searches = List.of( SearchTools::linSearch,SearchTools::binSearch, SearchTools::binSearchNew);
        List<Integer> arrays= List.of(100000,1000000,100000000,685154321);
        List<Function<Integer,Integer>> targetSupplier = List.of(x->new Random().nextInt(x)+1,x->-10);
        long[][][] times = new long[repeats][searches.size()][arrays.size()];

        //Messung
        for (int i = 0; i < repeats; i++) {
            System.out.println("repeat: "+i);
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
        String[] supplier= {"Random","-10"};
        int indexSupplier=0;
        String[] search= {"Linear","Binary","BinaryNew"};
        int indexSearch=0;

        for (int l = 0; l < targetSupplier.size(); l++) {
            System.out.println("Target: "+supplier[indexSupplier++]);
            for (int j = 0; j < searches.size(); j++) {
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
        }


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