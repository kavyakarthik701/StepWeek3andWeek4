import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + ":" + riskScore;
    }
}

public class RiskManagement {

    // 🔹 Bubble Sort (Ascending riskScore)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swapCount = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                }
            }
        }

        System.out.println("Bubble Sort (Ascending): " + Arrays.toString(arr));
        System.out.println("Swaps: " + swapCount);
    }

    // 🔹 Insertion Sort (Descending riskScore + accountBalance)
    public static void insertionSort(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort (Descending): " + Arrays.toString(arr));
    }

    // 🔹 Top 10 highest risk clients
    public static void topClients(Client[] arr, int k) {
        System.out.println("Top " + k + " highest risk clients:");
        for (int i = 0; i < Math.min(k, arr.length); i++) {
            System.out.println(arr[i].name + "(" + arr[i].riskScore + ")");
        }
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // Copy arrays for separate sorting
        Client[] bubbleArray = clients.clone();
        Client[] insertionArray = clients.clone();

        // 🔹 Bubble Sort
        bubbleSort(bubbleArray);

        // 🔹 Insertion Sort
        insertionSort(insertionArray);

        // 🔹 Top Clients (after descending sort)
        topClients(insertionArray, 3);
    }
}