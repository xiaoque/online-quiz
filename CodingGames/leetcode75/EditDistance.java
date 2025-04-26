public class EditDistance {
    /*
     * dp[i] [j] = moves at index i of word1, index j at word2
     * if word1[i] == word2[j], no move, dp[i][j] = dp[i - 1][j - 1]
     * 1. insert a char in word1, same as compare word1[i] to word2[j - 1],
     * dp[i][j] = dp[i][j - 1] + 1
     * 2. delete a char in word1, dp[i][j] = dp[i - 1][j] + 1
     * 3. replace a char, dp[i][j] = dp[i][j] + 1
     * Initial cases, dp[i][0], need to delete all chars to be equal to null
     * dp[i][0] = i, same for dp[0][j] = j
     */
    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        // initial value
        for (int i = 1; i <= length1; i++)
            dp[i][0] = i;
        for (int i = 1; i <= length2; i++)
            dp[0][i] = i;

        // compute array
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[length1][length2];
    }
}
