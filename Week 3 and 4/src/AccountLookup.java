import java.util.*;

public class AccountLookup {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    // 🔹 Linear Search - First Occurrence
    public static int linearFirst(String[] arr, String target) {
        linearComparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    // 🔹 Linear Search - Last Occurrence
    public static int linearLast(String[] arr, String target) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) {
                index = i;
            }
        }
        return index;
    }

    // 🔹 Binary Search - Any One Match
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        binaryComparisons = 0;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0)
                return mid;
            else if (cmp < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    // 🔹 Count Occurrences using Binary Search
    public static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    // 🔹 First occurrence (Binary)
    public static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // move left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔹 Last occurrence (Binary)
    public static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // move right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // 🔹 Linear Search
        int firstLinear = linearFirst(logs, "accB");
        int lastLinear = linearLast(logs, "accB");

        System.out.println("Linear First accB: index " + firstLinear);
        System.out.println("Linear Last accB: index " + lastLinear);
        System.out.println("Linear Comparisons: " + linearComparisons);

        // 🔹 Sort for Binary Search
        Arrays.sort(logs);

        System.out.println("\nSorted Logs: " + Arrays.toString(logs));

        // 🔹 Binary Search
        int binaryIndex = binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("Binary accB: index " + binaryIndex);
        System.out.println("Binary Comparisons: " + binaryComparisons);
        System.out.println("Count of accB: " + count);
    }
}
