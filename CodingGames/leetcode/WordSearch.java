
/*
 * @source https://leetcode.com/problems/word-search/description/
 * @author xiaoque
 * @date 2025.03.22
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        // for each pos [i][j], [i+1][j], [i][j+1], to verify
        // loop until find the first char
        // then start from this position, recursively to see if there is a match

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) == board[i][j]) {
                    int[][] memo = new int[m][n];
                    boolean tmp = dp(board, word, i, j, 0, memo);
                    if (tmp)
                        return tmp;
                }
            }
        }
        return false;
    }

    private boolean dp(char[][] board, String word, int i, int j, int index, int[][] memo) {
        if (index == word.length())
            return true;
        if (i >= board.length || j >= board[0].length || i < 0 || j < 0 || memo[i][j] == 1)
            return false;
        if (board[i][j] != word.charAt(index))
            return false;
        else {
            memo[i][j] = 1;
            if (dp(board, word, i + 1, j, index + 1, memo) || dp(board, word, i, j + 1, index + 1, memo)
                    || dp(board, word, i - 1, j, index + 1, memo) || dp(board, word, i, j - 1, index + 1, memo))
                return true;
        }
        // need to reset this postion because can go backward
        memo[i][j] = 0;
        return false;
    }
}
