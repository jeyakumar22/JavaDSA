package DSASheet.Recursion;

import java.util.*;

public class chatgptReadable{

    // ✅ Utility: Print all subsequences
    public void printAllSubsequences(int[] arr, int ind, List<Integer> ds, int n) {
        if (ind >= n) {
            System.out.println(ds);
            return;
        }

        // Not take the current element
        printAllSubsequences(arr, ind + 1, ds, n);

        // Take the current element
        ds.add(arr[ind]);
        printAllSubsequences(arr, ind + 1, ds, n);
        ds.remove(ds.size() - 1); // Always remove last
    }

    // ✅ Print all subsequences whose sum = k
    public void printSubsequencesWithSum(int k, int sum, int[] arr, int ind, List<Integer> ds, int n) {
        if (ind == n) {
            if (sum == k) {
                System.out.println(ds);
            }
            return;
        }

        // Not take
        printSubsequencesWithSum(k, sum, arr, ind + 1, ds, n);

        // Take
        ds.add(arr[ind]);
        printSubsequencesWithSum(k, sum + arr[ind], arr, ind + 1, ds, n);
        ds.remove(ds.size() - 1);
    }

    // ✅ Print only one subsequence whose sum = k
    public boolean printOneSubsequenceWithSum(int k, int sum, int[] arr, int ind, List<Integer> ds, int n) {
        if (ind == n) {
            if (sum == k) {
                System.out.println(ds);
                return true;
            }
            return false;
        }

        // Take
        ds.add(arr[ind]);
        if (printOneSubsequenceWithSum(k, sum + arr[ind], arr, ind + 1, ds, n)) return true;
        ds.remove(ds.size() - 1);

        // Not take
        if (printOneSubsequenceWithSum(k, sum, arr, ind + 1, ds, n)) return true;

        return false;
    }

    // ✅ Count all subsequences with sum = k
    public int countSubsequencesWithSum(int k, int sum, int[] arr, int ind, int n) {
        if (sum > k) return 0;
        if (ind == n) return sum == k ? 1 : 0;

        // Take
        int left = countSubsequencesWithSum(k, sum + arr[ind], arr, ind + 1, n);

        // Not take
        int right = countSubsequencesWithSum(k, sum, arr, ind + 1, n);

        return left + right;
    }

    // ✅ Combination Sum I: pick same element multiple times
    public void combinationSum(int target, int ind, int[] arr, List<List<Integer>> ans, List<Integer> ds) {
        if (ind >= arr.length) {
            if (target == 0) ans.add(new ArrayList<>(ds));
            return;
        }

        // Pick same element if it's allowed
        if (arr[ind] <= target) {
            ds.add(arr[ind]);
            combinationSum(target - arr[ind], ind, arr, ans, ds);
            ds.remove(ds.size() - 1);
        }

        // Skip element
        combinationSum(target, ind + 1, arr, ans, ds);
    }

    // ✅ Combination Sum II - Unique elements only (brute using Set)
    public void combinationSum2Brute(int target, int ind, int[] arr, Set<List<Integer>> ans, List<Integer> ds) {
        if (ind >= arr.length) {
            if (target == 0) ans.add(new ArrayList<>(ds));
            return;
        }

        // Pick
        if (arr[ind] <= target) {
            ds.add(arr[ind]);
            combinationSum2Brute(target - arr[ind], ind + 1, arr, ans, ds);
            ds.remove(ds.size() - 1);
        }

        // Skip
        combinationSum2Brute(target, ind + 1, arr, ans, ds);
    }

    // ✅ Combination Sum II - Unique elements with no duplicate results
    public void combinationSum2(int target, int ind, int[] arr, List<List<Integer>> ans, List<Integer> ds) {
        if (target == 0) {
            ans.add(new ArrayList<>(ds));
            return;
        }

        for (int i = ind; i < arr.length; i++) {
            if (i > ind && arr[i] == arr[i - 1]) continue; // Skip duplicates
            if (arr[i] > target) break;

            ds.add(arr[i]);
            combinationSum2(target - arr[i], i + 1, arr, ans, ds);
            ds.remove(ds.size() - 1);
        }
    }

    // ✅ User Operation Selection
    public static void op(String answer) {
        Scanner jk = new Scanner(System.in);
        System.out.println("Enter the number of elements:");
        int n = jk.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Element " + i + ": ");
            arr[i] = jk.nextInt();
        }

        chatgptReadable problem = new chatgptReadable();
        List<Integer> ds = new ArrayList<>();
        int k;

        switch (answer) {
            case "print_all_subsequence":
                problem.printAllSubsequences(arr, 0, ds, n);
                break;

            case "print_ss_eequals_k":
                System.out.print("Enter target sum k: ");
                k = jk.nextInt();
                problem.printSubsequencesWithSum(k, 0, arr, 0, ds, n);
                break;

            case "print_ss_eequals_k_oneAnsOnly":
                System.out.print("Enter target sum k: ");
                k = jk.nextInt();
                problem.printOneSubsequenceWithSum(k, 0, arr, 0, ds, n);
                break;

            case "count_ss_equalto_k":
                System.out.print("Enter target sum k: ");
                k = jk.nextInt();
                int count = problem.countSubsequencesWithSum(k, 0, arr, 0, n);
                System.out.println("Total valid subsequences: " + count);
                break;

            case "combinationSum":
                System.out.print("Enter target sum: ");
                k = jk.nextInt();
                List<List<Integer>> ans1 = new ArrayList<>();
                problem.combinationSum(k, 0, arr, ans1, ds);
                System.out.println("Combinations (repeats allowed): " + ans1);
                break;

            case "combinationSum2":
                System.out.print("Enter target sum: ");
                k = jk.nextInt();
                Arrays.sort(arr); // Important for duplicate handling
                List<List<Integer>> ans2 = new ArrayList<>();
                problem.combinationSum2(k, 0, arr, ans2, ds);
                System.out.println("Unique combinations (no repeats): " + ans2);
                break;
        }

        jk.close();
    }

    // ✅ Entry Point
    public static void main(String[] args) {
        op("combinationSum2"); // You can change this to test other cases
    }
}
