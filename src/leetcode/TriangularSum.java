package leetcode;

/*
 * @source https://leetcode.com/problems/find-triangular-sum-of-an-array/
 * @author xiaoque
 * @date 2025.03.25
 */
public class TriangularSum {
    public int triangularSum(int[] nums) {
        // use a num to store current length, and modify array in place
        int[] result = new int[nums.length];
        if (nums.length == 1)
            return nums[0];

        // initial first round
        int curr = nums.length - 1;
        for (int i = 0; i < curr; i++) {
            result[i] = (nums[i] + nums[i + 1]) % 10;
        }
        curr--;
        while (curr >= 1) {
            for (int i = 0; i < curr; i++) {
                result[i] = (result[i] + result[i + 1]) % 10;
            }
            curr--;
        }
        return result[0];
    }
}
