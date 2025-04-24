import java.util.Arrays;

/*
 * @source 
 * @author xiaoque
 * @date 2025.04.24
 */
public class MinimumNumberArrowsBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        // sort the points by start node
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        // iterate points, update a range[max start, min end]
        int end = points[0][1], count = 1;
        for (int i = 1; i < points.length; i++) {
            // if next node ends inside the range, update a min end
            if (points[i][0] <= end) {
                end = Math.min(points[i][1], end);
            } else {
                // otherwise, add count and update range
                count++;
                end = points[i][1];
            }
        }
        return count;
    }
}
