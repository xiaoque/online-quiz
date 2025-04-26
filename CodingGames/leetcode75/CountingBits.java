/*
 * @source https://leetcode.com/problems/counting-bits/
 * @author xiaoque
 * @date 2025.04.26
 */
public class CountingBits {
    /*
     * start from 1
     * 1 : 1
     * 2 : 1 0
     * 3 : 1 1 => 2 + (3 - 2)
     * 4 : 1 0 0
     * 5 : 1 0 1 => 4 + (5 - 4)
     * 6 : 1 1 1 => 4 + (6 - 4)
     * For i == 2^n, count is aways 1, else count = count(i - 2^(n - 1)) + 1
     */
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        int prev = 1;
        for (int i = 1; i <= n; i++) {
            if (i - prev < prev) {
                result[i] = result[i - prev] + 1;
            } else if (i - prev == prev) {
                prev = i;
                result[i] = 1;
            }
        }
        return result;
    }
}
