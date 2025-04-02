
import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/
 * @author xiaoque
 * @date 2025.03.29
 */
public class MaximumNumberEventsCanBeAttendedII {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (e1, e2) -> e1[1] - e2[1]);
        int[][] dp = new int[events.length + 1][k + 1];

        for (int i = 1; i <= events.length; i++) {
            int index = search(events, events[i - 1][0], i - 1);
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[index][j - 1] + events[i - 1][2]);
            }
        }
        return dp[events.length][k];

    }

    // dp[i][j] for event i, with j attended events
    // 1. not attend dp[i][j] = dp[j-1][j]
    // 2. attend dp[i][j] = dp[k+1][j-1] + event[i] k is the last event that ends
    // before i begins

    private int search(int[][] events, int startTime, int right) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (events[mid][1] >= startTime) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
