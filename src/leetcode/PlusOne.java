package leetcode;

/*
 * @source https://leetcode.com/problems/plus-one/
 * @author xiaoque
 * @date 2025.03.22
 */
public class PlusOne {
    // go backwards
    public int[] plusOne(int[] digits) {
        boolean plusOne = true;
        for (int i = digits.length - 1; i >= 0; i--) {
            int tmp = digits[i] + (plusOne ? 1 : 0);
            digits[i] = tmp % 10;
            plusOne = tmp / 10 != 0;
            if (!plusOne) {
                return digits;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}
