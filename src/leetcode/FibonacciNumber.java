package leetcode;

/*
 * @source https://leetcode.com/problems/fibonacci-number/
 * @author xiaoque
 * @date 2025.03.22
 */
public class FibonacciNumber {
    // recursively with memo
    public int fib(int n) {
        if (n == 2 || n == 1)
            return 1;
        int[] memo = new int[n + 1];
        return recurFib(memo, n);
    }

    private int recurFib(int[] memo, int n) {
        if (n == 0 || n == 1)
            return n;
        if (memo[n] != 0)
            return memo[n];
        memo[n] = recurFib(memo, n - 1) + recurFib(memo, n - 2);
        return memo[n];
    }
}
