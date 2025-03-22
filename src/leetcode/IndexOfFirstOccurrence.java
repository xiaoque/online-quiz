package leetcode;

/*
 * @source https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * @author xiaoque
 * @date 2025.03.22
 */
public class IndexOfFirstOccurrence {
    public int strStr(String haystack, String needle) {
        // when first char matches, check if substring equals to needle
        if (needle.length() > haystack.length())
            return -1;
        int n = needle.length();
        for (int i = 0; i <= haystack.length() - n; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if ((haystack.substring(i, i + n)).equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
