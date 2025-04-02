import java.util.ArrayList;
import java.util.List;

/*
 * @source https://leetcode.com/problems/palindrome-partitioning/
 * @author xiaoque
 * @date 2025.04.02
 */
public class PalindromePartitioning {
    boolean[][] memo;

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        memo = new boolean[s.length()][s.length()];
        computePalindrome(s, memo);
        bk(s, 0, new ArrayList<>(), result);
        return result;
    }

    // partition => size [1, n], end condition for backtrack would be index >n or
    // current is not a palindrome
    public void bk(String s, int index, List<String> comb, List<List<String>> result) {
        if (index >= s.length()) {
            result.add(new ArrayList<>(comb));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            // memo[i][j] checks for range[i, j] => substring(i, j + 1)
            if (memo[index][i]) {
                comb.add(s.substring(index, i + 1));
                bk(s, i + 1, comb, result);
                comb.remove(comb.size() - 1);
            }
        }

    }

    // compute the palindrome array
    private void computePalindrome(String s, boolean[][] memo) {
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || memo[i + 1][j - 1])) {
                    memo[i][j] = true;
                }
            }
        }
    }
}
