
import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/determine-if-two-strings-are-close/
 * @author xiaoque
 * @date 2025.03.24
 */
public class DetermineTwoStringsAreClose {
    /**
     * Note: 1, contain same chars or 2, the freq of char is the same (3a == 3b)
     */
    public boolean closeStrings(String word1, String word2) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }
        // first check condition 1
        for (char c : word2.toCharArray()) {
            if (count1[c - 'a'] == 0)
                return false;
            count2[c - 'a']++;
        }

        // check condition 2
        Arrays.sort(count1);
        Arrays.sort(count2);
        for (int i = 0; i < count1.length; i++) {
            if (count1[i] != count2[i])
                return false;
        }
        return true;
    }
}
