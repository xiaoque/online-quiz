
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * @source https://leetcode.com/problems/sliding-window-median/)
 * @author xiaoque
 * @date 2025.03.25
 */
public class SlidingWindowMedian {
    /**
     * Would have nums.length -k +1 values in return
     * use priority queue to maintain order
     * 2 heaps to keep median at the top of queue
     * left {3, 3, 1} right {4,5}
     */
    PriorityQueue<Integer> leftHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> rightHeap = new PriorityQueue<>();
    Map<Integer, Integer> removed = new HashMap<>();
    int leftCount = 0, rightCount = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            addToHeap(nums[i]);
            if (i >= k - 1) {
                result[index] = findMedian();
                index++;
                removeFromHeap(nums[i - k + 1]);
            }
        }
        return result;

    }

    // initial leftHeap first, then balance to right
    private void addToHeap(int val) {
        if (rightHeap.size() > 0 && val >= rightHeap.peek()) {
            rightHeap.add(val);
            rightCount++;
        } else {
            leftHeap.add(val);
            leftCount++;
        }
        rebalanceHeap();
    }

    private void removeFromHeap(int val) {
        removed.put(val, removed.getOrDefault(val, 0) + 1);
        if (val <= leftHeap.peek()) {
            leftCount--;
            verifyMovedNumberForHeap(leftHeap);
        } else {
            rightCount--;
            verifyMovedNumberForHeap(rightHeap);
        }
        rebalanceHeap();
    }

    private void rebalanceHeap() {
        while (leftCount > rightCount + 1) {
            rightHeap.add(leftHeap.poll());
            rightCount++;
            leftCount--;
        }
        while (leftCount < rightCount) {
            leftHeap.add(rightHeap.poll());
            leftCount++;
            rightCount--;
        }
        verifyMovedNumber();
    }

    private void verifyMovedNumber() {
        verifyMovedNumberForHeap(leftHeap);
        verifyMovedNumberForHeap(rightHeap);
    }

    private void verifyMovedNumberForHeap(PriorityQueue<Integer> queue) {
        while (!queue.isEmpty() && removed.getOrDefault(queue.peek(), 0) > 0) {
            int tmp = queue.peek();
            removed.put(tmp, removed.get(tmp) - 1);
            queue.poll();
        }
    }

    private double findMedian() {
        // if two queue of same size, k is even
        return (leftCount == rightCount) ? ((double) leftHeap.peek()) / 2 + ((double) rightHeap.peek()) / 2
                : leftHeap.peek();
    }
}
