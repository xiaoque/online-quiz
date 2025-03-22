package leetcode;

import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/longest-increasing-subsequence/
 * @author xiaoque
 * @date 2025.03.22
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // memo[i] means max subsequence for [0,...,i], memo[i] = max(memo[i-1]) + 1
        int[] memo = new int[nums.length + 1];
        Arrays.fill(memo, 1);
        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, dp(nums, i, memo));
        }
        return result;
    }

    private int dp(int[] nums, int index, int[] memo) {
        if (index == 0)
            return 1;
        if (memo[index] != 1) {
            return memo[index];
        }
        int result = 1;
        for (int i = 0; i < index; i++) {
            if (nums[i] < nums[index]) {
                result = Math.max(result, dp(nums, i, memo) + 1);
            }
        }
        memo[index] = result;
        return result;
    }
}
