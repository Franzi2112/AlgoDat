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
        int[] test100 = createSequenceDec(100);
        int[] test1000 = createSequenceDec(1000);
        int[] test10000 = createSequenceDec(10000);
        int[] test100000 = createSequenceDec(100000);
        int[] test200000 = createSequenceDec(200000);

        for (int i = 1; i <= 10; i++) {
            final long timeStart = System.nanoTime();
            insertionSort(test100);
            final long timeEnd = System.nanoTime();
            System.out.println("Sortierzeit für 100 Durchlauf " + i + " : " + (timeEnd - timeStart));

            final long timeS = System.nanoTime();
            insertionSort(test1000);
            final long timeE = System.nanoTime();
            System.out.println("Sortierzeit für 1000 Durchlauf " + i + " : " + (timeE - timeS));

            final long timeSt = System.nanoTime();
            insertionSort(test10000);
            final long timeEn = System.nanoTime();
            System.out.println("Sortierzeit für 10000 Durchlauf " + i + " : " + (timeEn - timeSt));

            final long timeStar = System.nanoTime();
            insertionSort(test100000);
            final long timeEnde = System.nanoTime();
            System.out.println("Sortierzeit für 100000 Durchlauf " + i + " : " + (timeEnde - timeStar));

            final long timeStarte = System.nanoTime();
            insertionSort(test200000);
            final long timeEnden = System.nanoTime();
            System.out.println("Sortierzeit für 200000 Durchlauf " + i + " : " + (timeEnden - timeStarte));


        }

    }
}

//Sortierzeit für 100 Durchlauf 1 : 11200
//Sortierzeit für 1000 Durchlauf 1 : 35000
//Sortierzeit für 10000 Durchlauf 1 : 365800
//Sortierzeit für 100000 Durchlauf 1 : 2622100
//Sortierzeit für 200000 Durchlauf 1 : 2283900
//Sortierzeit für 100 Durchlauf 2 : 1200
//Sortierzeit für 1000 Durchlauf 2 : 9300
//Sortierzeit für 10000 Durchlauf 2 : 90900
//Sortierzeit für 100000 Durchlauf 2 : 875200
//Sortierzeit für 200000 Durchlauf 2 : 1813300
//Sortierzeit für 100 Durchlauf 3 : 1400
//Sortierzeit für 1000 Durchlauf 3 : 9100
//Sortierzeit für 10000 Durchlauf 3 : 87100
//Sortierzeit für 100000 Durchlauf 3 : 928200
//Sortierzeit für 200000 Durchlauf 3 : 1759100
//Sortierzeit für 100 Durchlauf 4 : 400
//Sortierzeit für 1000 Durchlauf 4 : 900
//Sortierzeit für 10000 Durchlauf 4 : 6400
//Sortierzeit für 100000 Durchlauf 4 : 63600
//Sortierzeit für 200000 Durchlauf 4 : 110500
//Sortierzeit für 100 Durchlauf 5 : 400
//Sortierzeit für 1000 Durchlauf 5 : 700
//Sortierzeit für 10000 Durchlauf 5 : 5800
//Sortierzeit für 100000 Durchlauf 5 : 49700
//Sortierzeit für 200000 Durchlauf 5 : 109300
//Sortierzeit für 100 Durchlauf 6 : 200
//Sortierzeit für 1000 Durchlauf 6 : 900
//Sortierzeit für 10000 Durchlauf 6 : 5100
//Sortierzeit für 100000 Durchlauf 6 : 44500
//Sortierzeit für 200000 Durchlauf 6 : 115000
//Sortierzeit für 100 Durchlauf 7 : 600
//Sortierzeit für 1000 Durchlauf 7 : 500
//Sortierzeit für 10000 Durchlauf 7 : 6600
//Sortierzeit für 100000 Durchlauf 7 : 50700
//Sortierzeit für 200000 Durchlauf 7 : 106600
//Sortierzeit für 100 Durchlauf 8 : 900
//Sortierzeit für 1000 Durchlauf 8 : 800
//Sortierzeit für 10000 Durchlauf 8 : 6600
//Sortierzeit für 100000 Durchlauf 8 : 59400
//Sortierzeit für 200000 Durchlauf 8 : 105600
//Sortierzeit für 100 Durchlauf 9 : 400
//Sortierzeit für 1000 Durchlauf 9 : 700
//Sortierzeit für 10000 Durchlauf 9 : 6800
//Sortierzeit für 100000 Durchlauf 9 : 71000
//Sortierzeit für 200000 Durchlauf 9 : 138900
//Sortierzeit für 100 Durchlauf 10 : 400
//Sortierzeit für 1000 Durchlauf 10 : 1200
//Sortierzeit für 10000 Durchlauf 10 : 6800
//Sortierzeit für 100000 Durchlauf 10 : 59500
//Sortierzeit für 200000 Durchlauf 10 : 128500

