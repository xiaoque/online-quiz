import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @source https://leetcode.com/problems/removing-stars-from-a-string/
 * @author xiaoque
 * @date 2025.04.02
 */
public class RemovingStarsFromString {
    // add char into stack, if s[i] is star, then pop one
    // rebuild string using stringbuilder then reverse
    public String removeStars(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '*')
                stack.pop();
            else
                stack.push(c);
        }
        // construct sb
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
