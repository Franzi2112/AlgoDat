package Zettel01Franzi;

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
        for (int i = n - 1; i > 0; i--) {
            descending[i] = i + 1;
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

    public static void main(String[] args) {

        int[][] testObjects = {createSequenceDec(100), createSequenceDec(1000),
                createSequenceDec(10000), createSequenceDec(100000), createSequenceDec(200000)};

        String result = " ";

        for (int i = 0; i < testObjects.length ; i++) {
            result = result + "\n Die Testlaufzeiten für Objekt " + i + " : ";
            for (int j = 0; j < 10; j++) {
                long timeStart = System.nanoTime();
                insertionSort(testObjects[i]);
                long timeEnd = System.nanoTime();
                long time = timeEnd - timeStart;
                result = result  + time +  ", ";
            }
        }
        System.out.println(result);

        }


}

// Die Testlaufzeiten für Objekt 0 : 10800, 4900, 4200, 4000, 4000, 4000, 4100, 4000, 4000, 3900,
// Die Testlaufzeiten für Objekt 1 : 42100, 40100, 40300, 40400, 40300, 40400, 40600, 40300, 40500, 40300,
// Die Testlaufzeiten für Objekt 2 : 394700, 404300, 387400, 394900, 415200, 279100, 91100, 98100, 83000, 83300,
// Die Testlaufzeiten für Objekt 3 : 844900, 857500, 824600, 817500, 834000, 559100, 183400, 154100, 156900, 163300,
// Die Testlaufzeiten für Objekt 4 : 222500, 212800, 205600, 195500, 215900, 197100, 199700, 197500, 195200, 204500,

