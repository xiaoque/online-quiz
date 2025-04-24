import java.util.Map;
import java.util.stream.Collectors;

/*
 * @source https://leetcode.com/problems/minimum-window-substring/
 * @author xiaoque
 * @date 2025.04.21
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        // get freq of chars in t
        Map<Character, Integer> freq = t.chars()
                .mapToObj(val -> (char) val)
                .collect(Collectors.groupingBy(
                        x -> x,
                        Collectors.summingInt(x -> 1)));

        int count = freq.size();
        int left = 0, right = 0, start = 0, result = Integer.MAX_VALUE;
        // expand right index until it contains all chars in t
        while (right < s.length()) {
            char c = s.charAt(right);
            // reduce count when the char reach 0
            freq.put(c, freq.getOrDefault(c, 0) - 1);
            if (freq.get(c) == 0)
                count--;

            // move left while count = 0
            if (count == 0) {
                c = s.charAt(left);
                while (freq.get(c) < 0) {
                    freq.put(c, freq.get(c) + 1);
                    left++;
                    c = s.charAt(left);
                }
                // check if current size is minimal
                if (result > right - left + 1) {
                    result = right - left + 1;
                    start = left;
                }

                // move left to find next window
                freq.put(c, freq.get(c) + 1);
                left++;
                count++;
            }
            right++;
        }

        return (result != Integer.MAX_VALUE) ? s.substring(start, start + result) : "";
    }
}
