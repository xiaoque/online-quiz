/*
 * @source https://leetcode.com/problems/is-subsequence/
 * @author xiaoque
 * @date 2025.04.26
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        // s should be shorter than t
        if (s.length() > t.length())
            return false;
        // use 2 pointer, iterate t, move s forward only if a match
        int index1 = 0, index2 = 0;
        while (index1 < s.length() && index2 < t.length()) {
            if (s.charAt(index1) == t.charAt(index2))
                index1++;
            index2++;
        }
        return index1 == s.length();
    }
}
