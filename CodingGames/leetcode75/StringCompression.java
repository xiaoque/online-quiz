
/*
 * @source https://leetcode.com/problems/string-compression/
 * @author xiaoque
 * @date 2025.03.23
 */
public class StringCompression {
    public int compress(char[] chars) {
        // 2 pointers, 1 for index of char, 1 for modify
        if (chars.length == 1)
            return 1;
        int slow = 0, fast = 1, count = 1;
        char prev = chars[0];
        while (fast < chars.length) {
            if (chars[fast] == prev) {
                count++;
            } else {
                // need to compress array
                slow = compressChar(chars, prev, slow, count);
                prev = chars[fast];
                count = 1;
            }
            fast++;
        }
        return compressChar(chars, prev, slow, count);
    }

    private int compressChar(char[] chars, char c, int index, int count) {
        chars[index++] = c;
        if (count > 1) {
            for (char tmp : String.valueOf(count).toCharArray()) {
                chars[index++] = tmp;
            }
        }
        return index;
    }
}
