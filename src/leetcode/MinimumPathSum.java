package leetcode;

import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/minimum-path-sum/
 * @author xiaoque
 * @date 2025.03.22
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int column = grid[0].length;
        int row = grid.length;
        int[][] memo = new int[row][column];

        for (int[] rows : memo) {
            Arrays.fill(rows, -1);
        }

        return dp(grid, row - 1, column - 1, memo);
    }

    private int dp(int[][] grid, int m, int n, int[][] memo) {
        if (m == 0 && n == 0) {
            return grid[0][0];
        }
        if (n < 0 || m < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[m][n] != -1)
            return memo[m][n];

        // memo[i][j] = min(memo[i-1][j] , memo[i][j-1])+val[i][j]
        memo[m][n] = Math.min(dp(grid, m - 1, n, memo), dp(grid, m, n - 1, memo)) + grid[m][n];
        return memo[m][n];
    }
}
