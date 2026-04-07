import java.util.*;

public class RiskThresholdLookup {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    // 🔹 Linear Search (unsorted)
    public static int linearSearch(int[] arr, int target) {
        linearComparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // 🔹 Binary Search - Insertion Point (lower_bound)
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        binaryComparisons = 0;

        while (low < high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }
        return low; // insertion point
    }

    // 🔹 Floor (largest ≤ target)
    public static int floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return arr[mid];

            if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔹 Ceiling (smallest ≥ target)
    public static int ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return arr[mid];

            if (arr[mid] > target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};

        // 🔹 Linear Search
        int linearResult = linearSearch(risks, 30);
        System.out.println("Linear Search (30): " + linearResult);
        System.out.println("Linear Comparisons: " + linearComparisons);

        // 🔹 Binary Lower Bound (Insertion Point)
        int insertionIndex = lowerBound(risks, 30);
        System.out.println("\nInsertion Index for 30: " + insertionIndex);

        // 🔹 Floor & Ceiling
        int floorValue = floor(risks, 30);
        int ceilingValue = ceiling(risks, 30);

        System.out.println("Floor(30): " + floorValue);
        System.out.println("Ceiling(30): " + ceilingValue);
        System.out.println("Binary Comparisons (approx): " + binaryComparisons);
    }
}
