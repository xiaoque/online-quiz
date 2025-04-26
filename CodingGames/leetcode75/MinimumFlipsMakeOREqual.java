/*
 * @source https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/
 * @author xiaoque
 * @date 2025.04.26
 */
public class MinimumFlipsMakeOREqual {
    public int minFlips(int a, int b, int c) {
        int result = 0;
        while (a != 0 || b != 0 || c != 0) {
            // last bit value
            int x1 = a & 1;
            int x2 = b & 1;
            int x3 = c & 1;

            if (x3 != (x1 | x2)) {
                if ((x1 & x2) == 1)
                    result += 2;
                else
                    result++;
            }
            // shift values
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }
        return result;
    }
}
