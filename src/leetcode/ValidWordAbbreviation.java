package leetcode;

import java.util.function.BiFunction;

/*
 * @source https://leetcode.com/problems/valid-word-abbreviation/description/
 * @author xiaoque
 * @date 2025.03.22
 */
public class ValidWordAbbreviation {
    public static boolean validWordAbbreviation(String word, String abbr) {
        // two pointer, one at word, one at abbr
        // if abbr is number and not leading 0, then find next common letter
        // two special case, one is equal, one is total number

        if (word.equals(abbr) || (String.valueOf(word.length()).equals(abbr)))
            return true;

        int index1 = 0, index2 = 0;

        while (index1 < word.length() && index2 < abbr.length()) {
            // no abbr
            if (word.charAt(index1) == abbr.charAt(index2)) {
                index1++;
                index2++;
            } else {
                // abbr begins, first check 0
                if (abbr.charAt(index2) - '0' == 0)
                    return false;
                int length = 0;
                while (index2 < abbr.length() && abbr.charAt(index2) >= '0' && abbr.charAt(index2) <= '9') {
                    length = length * 10 + abbr.charAt(index2) - '0';
                    index2++;
                }
                // got the number, see if the number if valid
                index1 += length;
                if (index1 == word.length() && index2 == abbr.length()) {
                    return true;
                }
                if (index1 > word.length() || word.charAt(index1) != abbr.charAt(index2)) {
                    return false;
                }
            }
        }
        return index1 == word.length() && index2 == abbr.length();
    }

    public static void main(String[] args) {
        testFunc();

    }

    private static void testFunc() {
        BiFunction<String, String, Boolean> test = (s1, s2) -> validWordAbbreviation(s1, s2);
        System.out.println("══════════════════════════════════");
        validate(test, "apple", "a2e");
        validate(test, "internationalization", "i12iz4n");
        validate(test, "substitution", "s10n");
        validate(test, "substitution", "sub4u4");
        validate(test, "substitution", "12");
        validate(test, "substitution", "substitution");
        validate(test, "substitution", "s0ubstitution");
        validate(test, "substitution", "s55n");
        validate(test, "substitution", "s010n");
    }

    private static void validate(BiFunction<String, String, Boolean> test, String s1, String s2) {
        System.out.println(String.format("String %s -- %s : %s", s1, s2, test.apply(s1, s2)));
    }
}
