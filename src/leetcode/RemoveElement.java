package leetcode;

/*
 * @source https://leetcode.com/problems/remove-element/
 * @author xiaoque
 * @date 2025.03.22
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }

        return slow;
    }
}
