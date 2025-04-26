import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * @source https://leetcode.com/problems/maximum-subsequence-score/
 * @author xiaoque
 * @date 2025.04.13
 */
public class MaximumSubsequenceScore {
    record Pair(int a, int b) {
    }

    public long maxScore(int[] nums1, int[] nums2, int k) {
        // order pair by value of nums2, desc
        Pair[] pairs = new Pair[nums1.length];
        for (int i = 0; i < nums1.length; i++)
            pairs[i] = new Pair(nums1[i], nums2[i]);
        Arrays.sort(pairs, (x, y) -> y.b - x.b);
        PriorityQueue<Integer> q = new PriorityQueue<>();

        // compute value for window size k
        long result = -1, sum = 0;
        for (int i = 0; i < nums1.length; i++) {
            q.offer(pairs[i].a);
            sum += pairs[i].a;
            if (i >= k - 1) {
                result = Math.max(result, sum * pairs[i].b);
                sum -= q.poll();
            }
        }
        return result;
    }
}
