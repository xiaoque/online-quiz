package leetcode;

import java.util.Arrays;

/*
 * @source https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 * @author xiaoque
 * @date 2025.03.29
 */
public class MaxProfitInJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // memo[0] = 0, no job
        // memo[1] = profit[0], take first job
        // memo[i] = max of dont take job memo[i-1] vs memo[j] + profit[i] where
        // j.endtime < i.begintime

        // contrust a map order by starttime
        int n = startTime.length;
        int[][] map = new int[n][3];
        for (int i = 0; i < n; i++) {
            map[i][0] = startTime[i];
            map[i][1] = endTime[i];
            map[i][2] = profit[i];
        }
        // sort by endtime
        Arrays.sort(map, (a, b) -> a[1] - b[1]);
        int[] memo = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int tmp = search(map, map[i][0], i);
            memo[i + 1] = Math.max(memo[i], memo[tmp] + map[i][2]);
        }
        return memo[n];

    }

    private int search(int[][] map, int val, int i) {
        int left = 0, right = i;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (map[left][1] > val) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
