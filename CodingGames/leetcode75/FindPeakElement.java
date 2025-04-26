/*
 * @source https://leetcode.com/problems/find-peak-element/
 * @author xiaoque
 * @date 2025.04.13
 */
public class FindPeakElement {
    // if mid < mid + 1 => peek is at right
    // mid < mid - 1 => peek is at left
    public int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // case when mid = n - 1
            if (mid == nums.length - 1 || nums[mid] > nums[mid + 1])
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
