/*
 * @source https://leetcode.com/problems/longest-common-subsequence/
 * @author xiaoque
 * @date 2025.04.13
 */
public class LongestCommonSubsequence {
    // use 1d array
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // the max value will the min length of text1 / text2
        int max = Math.min(m, n);
        int[] dp = new int[n + 1];
        int pre = 0;
        for (int i = 1; i <= m; i++) {
            pre = dp[0];
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[j] = pre + 1;
                else
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                if (max == dp[j])
                    return max;
                pre = tmp;
            }
        }
        return dp[n];
    }

    // use 2d-array
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // the max value will the min length of text1 / text2
        int max = Math.min(m, n);
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (max == dp[i][j])
                    return max;
            }
        }
        return dp[m][n];
    }
}
