import static java.util.Map.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @source https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * @author xiaoque
 * @date 2025.04.13
 */
public class LetterCombinationsPhoneNumber {
    // result.length = digits.length
    // static map for digit - letters mapping
    public static Map<Character, char[]> map = Map.ofEntries(
            entry('2', new char[] { 'a', 'b', 'c' }),
            entry('3', new char[] { 'd', 'e', 'f' }),
            entry('4', new char[] { 'g', 'h', 'i' }),
            entry('5', new char[] { 'j', 'k', 'l' }),
            entry('6', new char[] { 'm', 'n', 'o' }),
            entry('7', new char[] { 'p', 'q', 'r', 's' }),
            entry('8', new char[] { 't', 'u', 'v' }),
            entry('9', new char[] { 'w', 'x', 'y', 'z' }));

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (null == digits || digits.length() == 0)
            return result;
        backtracking(digits, 0, new StringBuilder(), result);
        return result;
    }

    // backtracking with inputs: digits, currIndex, path, result
    public void backtracking(String digits, int index, StringBuilder sb, List<String> result) {
        // end condition: currIndex == digits.length - 1, add path to result
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }
        // for all letters in currIndex
        for (char c : map.get(digits.charAt(index))) {
            // add one to path, and call backtracking
            sb.append(c);
            backtracking(digits, index + 1, sb, result);
            // remove from path
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
