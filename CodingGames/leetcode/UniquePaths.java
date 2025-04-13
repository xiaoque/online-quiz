import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/unique-paths/
 * @author xiaoque
 * @date 2025.04.13
 */
public class UniquePaths {
    // direction {{1, 0}, {0, 1}}
    // dp[i] [j] = dp[i - 1][j] + dp[i] [j - 1]
    /**
     * start, 1, 1, 1, 1, 1
     * 1 , 2, 3, 4, 5, 6
     */
    // row0 = 1, col0 = 1, iterate from i [1, m), and j[1, n)
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        Arrays.fill(memo[0], 1);
        for (int i = 1; i < m; i++) {
            memo[i][0] = 1;
            for (int j = 1; j < n; j++) {
                memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
            }
        }
        return memo[m - 1][n - 1];
    }
}
