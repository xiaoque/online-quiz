package leetcode;

/*
 * @source https://leetcode.com/problems/increasing-triplet-subsequence/
 * @author xiaoque
 * @date 2025.03.23
 */
public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        // only need to store min 2 numbers before nums[i]
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        if (nums == null || nums.length < 3)
            return false;
        for (int num : nums) {
            if (num <= min1) {
                min1 = num;
            } else if (num <= min2) {
                min2 = num;
            } else {
                return true;
            }
        }
        return false;
    }

}
