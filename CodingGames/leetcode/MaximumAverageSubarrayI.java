
/*
 * @source https://leetcode.com/problems/maximum-average-subarray-i/
 * @author xiaoque
 * @date 2025.03.24
 */
public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        // compute initial result, then construct new result by remove first element and
        // add curr element

        int result = 0;
        int curr = 0;
        for (int i = 0; i < k; i++) {
            curr += nums[i];
        }
        result = curr;
        for (int i = k; i < nums.length; i++) {
            curr = curr - nums[i - k] + nums[i];
            result = Math.max(curr, result);
        }

        return 1.0 * result / k;
    }
}
