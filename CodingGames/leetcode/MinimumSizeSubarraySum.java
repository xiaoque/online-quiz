
/*
 * @source https://leetcode.com/problems/minimum-size-subarray-sum/
 * @author xiaoque
 * @date 2025.03.24
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        // Note: nums[i] >0, target >0, sum(int[]) >= target, min(length) or 0
        // find if it's possible by cumulate nums[i]
        // get the min length
        int begin = 0, curr = 0, minLength = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            curr += nums[i];
            // try to reduce size
            while (curr >= target) {
                minLength = Math.min(i - begin + 1, minLength);
                curr -= nums[begin];
                begin++;
            }
        }
        return minLength != Integer.MAX_VALUE ? minLength : 0;
    }
}
