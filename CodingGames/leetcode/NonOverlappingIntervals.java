import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/non-overlapping-intervals/
 * @author xiaoque
 * @date 2025.04.22
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int moves = 0;
        int endTime = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (interval[0] < endTime) {
                moves++;
                endTime = endTime == Integer.MIN_VALUE ? interval[1] : Math.min(endTime, interval[1]);
            } else {
                endTime = interval[1];
            }
        }
        return moves;
    }
}
