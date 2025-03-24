package leetcode;

import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/k-radius-subarray-averages/
 * @author xiaoque
 * @date 2025.03.24
 */
public class KRadiusSubarrayAverages {
    public int[] getAverages(int[] nums, int k) {
        // for index [0, k-1] and [length -k, length-1] are -1
        // only need to compute for [k, length-1-k]

        if (k == 0)
            return nums;
        int subLength = 2 * k + 1, n = nums.length;
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        // first check if 2k+1 < nums.length
        if (subLength > n) {
            return result;
        }
        long sum = 0;
        // intial value at index k with range [0, 2k]
        for (int i = 0; i < subLength; i++) {
            sum += nums[i];
        }
        // Note: (int)(calculation) to assure cast after calcu
        result[k] = (int) (sum / subLength);
        for (int i = k + 1; i < n - k; i++) {
            sum = sum - nums[i - k - 1] + nums[i + k];
            result[i] = (int) (sum / subLength);
        }

        return result;
    }
}
