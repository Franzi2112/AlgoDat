package Zettel01Bela;

import java.util.Arrays;

public class Aufgabe4 {

//a)
    public static boolean twoSum(int[] array, int sum) {
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            int target = sum - array[i];
            int left= i + 1;
            int right = array.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (array[mid] == target) {
                    return true;
                } else if (array[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

//b)
    //are there three numbers in the array that sum to the given value?
    public static boolean threeSum(int[] array, int sum) {
        for (int i = 0; i < array.length; i++) {
            if (twoSum(Arrays.copyOfRange(array,i+1,array.length), sum - array[i])) {
                return true;
            }
        }
        return false;
    }

}
