
import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @source https://leetcode.com/problems/sliding-window-maximum/
 * @author xiaoque
 * @date 2025.03.30
 */
public class SlidingWindowMaximun {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // size is n-k+1
        int[] result = new int[nums.length - k + 1];

        // use a queue to keep track of num left->right = small -> big
        // curr > big, add to right, and remove previous num
        // curr
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {

            // remove num that not in the window
            if (!queue.isEmpty() && queue.peekFirst() <= i - k) {
                queue.removeFirst();
            }
            // remove previous num that smaller than curr
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
            // after removeLast, left only contains number greater than curr
            // so the max num should be on the left side
            if (i >= k - 1) {
                result[i - k + 1] = nums[queue.peekFirst()];
            }
        }

        return result;
    }
}
