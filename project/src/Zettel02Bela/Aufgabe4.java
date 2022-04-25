package Zettel02Bela;

public class Aufgabe4 {

    public int[] DownUpMerge(int[] a) {
        int[] b = new int[a.length];
        int i = 0;
        int j = a.length - 1;
        int k = a.length - 1;
        while (i <= j) {
            if (a[i] <= a[j]) {
                b[k--] = a[j];
                i++;
            } else {
                b[k--] = a[i];
                j--;
            };
        }
        return b;
    }
}
