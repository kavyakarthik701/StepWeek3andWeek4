import java.util.*;

/**
 * =========================================================================
 * CLASS - Transaction
 * =========================================================================
 * * Description:
 * Represents a banking transaction with:
 * - ID
 * - Fee amount
 * - Timestamp
 *
 * * @version 1.0
 */
class Transaction {

    private String id;
    private double fee;
    private String timestamp;

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public double getFee() {
        return fee;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return id + ": " + fee + "@" + timestamp;
    }
}

/**
 * =========================================================================
 * CLASS - TransactionSorter
 * =========================================================================
 * * Description:
 * Provides sorting algorithms for transactions:
 * - Bubble Sort (by fee)
 * - Insertion Sort (by fee + timestamp)
 *
 * * Ensures:
 * - Stable sorting
 * - Outlier detection
 *
 * * @version 1.0
 */
class TransactionSorter {

    /**
     * Bubble Sort (Stable) - Sort by fee (ascending)
     *
     * @param list Transaction list
     */
    public void bubbleSortByFee(List<Transaction> list) {

        int n = list.size();
        int passes = 0;
        int swaps = 0;

        /*
         * Bubble Sort with early termination optimization
         */
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {

                if (list.get(j).getFee() > list.get(j + 1).getFee()) {

                    // Swap (stable because only >, not >=)
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                    swaps++;
                    swapped = true;
                }
            }

            /*
             * Stop early if already sorted
             */
            if (!swapped) break;
        }

        System.out.println("\nBubble Sort Result (by Fee):");
        printList(list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    /**
     * Insertion Sort (Stable) - Sort by fee, then timestamp
     *
     * @param list Transaction list
     */
    public void insertionSortByFeeAndTimestamp(List<Transaction> list) {

        for (int i = 1; i < list.size(); i++) {

            Transaction key = list.get(i);
            int j = i - 1;

            /*
             * Shift elements that are greater than key
             */
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort Result (by Fee + Timestamp):");
        printList(list);
    }

    /**
     * Comparison logic:
     * - First by fee
     * - Then by timestamp
     */
    private int compare(Transaction t1, Transaction t2) {

        if (t1.getFee() != t2.getFee()) {
            return Double.compare(t1.getFee(), t2.getFee());
        }

        return t1.getTimestamp().compareTo(t2.getTimestamp());
    }

    /**
     * Detect high-fee outliers (> 50)
     */
    public void detectOutliers(List<Transaction> list) {

        System.out.println("\nHigh-fee Outliers (>50):");

        boolean found = false;

        for (Transaction t : list) {
            if (t.getFee() > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    /**
     * Utility method to print list
     */
    private void printList(List<Transaction> list) {
        for (Transaction t : list) {
            System.out.println(t);
        }
    }
}

/**
 * =========================================================================
 * MAIN CLASS - TransactionAuditApp
 * =========================================================================
 * * Description:
 * Simulates transaction sorting for audit compliance.
 *
 * * Flow:
 * 1. Create transaction list
 * 2. Apply Bubble Sort (small batch)
 * 3. Apply Insertion Sort (medium batch)
 * 4. Detect high-fee outliers
 *
 * * @version 1.0
 */
public class TransactionAuditApp {

    public static void main(String[] args) {

        /*
         * Sample input transactions
         */
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        TransactionSorter sorter = new TransactionSorter();

        /*
         * Use Bubble Sort for small batch (≤100)
         */
        sorter.bubbleSortByFee(new ArrayList<>(transactions));

        /*
         * Use Insertion Sort for medium batch
         */
        sorter.insertionSortByFeeAndTimestamp(new ArrayList<>(transactions));

        /*
         * Detect outliers
         */
        sorter.detectOutliers(transactions);
    }
}