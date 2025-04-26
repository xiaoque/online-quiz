import java.util.Stack;

/*
 * @source https://leetcode.com/problems/decode-string/
 * @author xiaoque
 * @date 2025.04.25
 */
public class DecodeString {
    public static char OPEN = '[';
    public static char CLOSE = ']';

    public String decodeString(String s) {
        // use 2 stack, one for number, one for chars
        Stack<Integer> number = new Stack<>();
        Stack<StringBuilder> strs = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int tmp = 0;
        for (char c : s.toCharArray()) {
            // compute number
            if (Character.isDigit(c)) {
                tmp = tmp * 10 + (c - '0');
            } else if (OPEN == c) {
                // store previous number and start a stringbuilder
                number.push(tmp);
                strs.push(sb);
                sb = new StringBuilder();
                tmp = 0;
            } else if (CLOSE == c) {
                // current sb have the whole chars in []
                // repeat it and append to previous sb
                int count = number.pop();
                StringBuilder curr = sb;
                sb = strs.pop();
                while (count-- > 0) {
                    sb.append(curr);
                }
            } else {
                // apend the chars
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
