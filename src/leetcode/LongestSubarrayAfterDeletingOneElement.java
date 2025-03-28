package leetcode;

/*
 * @source https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
 * @author xiaoque
 * @date 2025.03.24
 */
public class LongestSubarrayAfterDeletingOneElement {
    /*
     * Note: binary array, remove exactly 1 element, ideally 0
     * use left pointer for begin of 1, and right as curr position
     * same as 1004, just with k = 1, and add case when there is no 0
     */
    public int longestSubarray(int[] nums) {
        int max = 0, left = 0, zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero++;
            }
            while (zero > 1) {
                if (nums[left] == 0)
                    zero--;
                left++;
            }
            // compute current length
            max = Math.max(max, i - left);
        }
        if (zero == 0) {
            max = nums.length - 1;
        }
        return max;
    }
}
