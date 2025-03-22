package leetcode;

/*
 * @source https://leetcode.com/problems/single-number/
 * @author xiaoque
 * @date 2025.03.22
 */
public class SingleNumber {
    // XOR operation
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }
        return result;
    }
}
