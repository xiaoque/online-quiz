
/*
 * @source https://leetcode.com/problems/move-zeroes/
 * @author xiaoque
 * @date 2025.03.22
 */
public class MovingZeros {
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] == 0) {
                fast++;
            } else {
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }
        }
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }
}
