package leetcode;

/*
 * @source https://leetcode.com/problems/check-if-it-is-a-straight-line/description/
 * @author xiaoque
 * @date 2025.03.22
 */
public class CheckStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        // its a straight line
        // (y -y1)/(y1 -y0) = (x-x1)/(x1-x0)

        if (coordinates.length == 2)
            return true;
        int x0 = coordinates[0][0], y0 = coordinates[0][1],
                x1 = coordinates[1][0], y1 = coordinates[1][1];
        int dx = x1 - x0, dy = y1 - y0;
        for (int[] co : coordinates) {
            if (dx * (co[1] - y1) != dy * (co[0] - x1))
                return false;
        }
        return true;
    }
}
