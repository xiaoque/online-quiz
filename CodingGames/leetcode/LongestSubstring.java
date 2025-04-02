
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/*
 * @source https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * @author xiaoque
 * @date 2025.03.22
 */
public class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        // use Set to see if subset contains repeating chars
        // a pointer to store begin of the set, compute size on each repeating char

        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int max = 0;
        int begin = 0;
        Set<Character> chars = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!chars.add(c)) {
                // compute current length of non repeating char
                max = Math.max(max, chars.size());
                // remove the repeat char
                while (chars.contains(c)) {
                    chars.remove(s.charAt(begin));
                    begin++;
                }
                chars.add(c);
            }
        }
        return Math.max(max, chars.size());
    }

    public static void main(String[] args) {
        testFunc();
    }

    private static void testFunc() {
        Function<String, Integer> func = str -> lengthOfLongestSubstring(str);
        valid(func, "", 0);
        valid(func, " ", 1);
        valid(func, "aaaaaaaaaaaa", 1);
        valid(func, "abcabcbb", 3);
        valid(func, "pwwkew", 3);
        valid(func, "abcdefghi,.", 11);
    }

    private static void valid(Function<String, Integer> func, String input, Integer result) {
        System.out.println(String.format("Case: %s,  %s", input, func.apply(input).equals(result) ? "OK"
                : String.format("KO -- Expect: %s, got %s", result, func.apply(input))));
    }
}
