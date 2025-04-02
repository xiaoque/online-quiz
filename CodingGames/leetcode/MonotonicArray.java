
/*
 * @source https://leetcode.com/problems/monotonic-array/description/
 * @author xiaoque
 * @date 2025.03.22
 */
public class MonotonicArray {
    public boolean isMonotonic(int[] nums) {
        boolean isIncrease = false;
        boolean isInitialized = false;
        for (int i = 0; i < nums.length - 1; i++) {
            if (!isInitialized) {
                if (nums[i] != nums[i + 1]) {
                    isInitialized = true;
                    isIncrease = nums[i] < nums[i + 1];
                }
            } else {
                if (isIncrease && nums[i] > nums[i + 1]) {
                    return false;
                }
                if (!isIncrease && nums[i] < nums[i + 1]) {
                    return false;
                }
            }
        }
        return true;

    }
}
