import java.util.LinkedList;
import java.util.Queue;

/*
 * @source https://leetcode.com/problems/rotting-oranges/
 * @author xiaoque
 * @date 2025.04.26
 */
public class RottingOranges {
    public static int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int orangesRotting(int[][] grid) {
        // store 2 points, count 1s
        Queue<int[]> queue = new LinkedList<>();
        int freshCnt = countOranges(grid, queue);
        int m = grid.length;
        int n = grid[0].length;

        // special cases when no fresh orange or no rotted orange
        if (freshCnt == 0)
            return 0;
        if (queue.size() == 0)
            return -1;

        // visite graph BFS, 2 points be root level nodes
        int step = 0;
        int x = 0, y = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] curr = queue.poll();
                // update current node to 0
                grid[curr[0]][curr[1]] = 0;

                // check if 4 direction contains 1 node
                for (int[] dir : directions) {
                    x = dir[0] + curr[0];
                    y = dir[1] + curr[1];
                    if (x < 0 || x == m || y < 0 || y == n)
                        continue;
                    // update 1 node, and put it for next traversal
                    if (grid[x][y] == 1) {
                        freshCnt--;
                        grid[x][y] = 0;
                        queue.offer(new int[] { x, y });
                    }
                }
                size--;
            }
            // add step
            step++;
        }
        return freshCnt == 0 ? step - 1 : -1;
    }

    private int countOranges(int[][] grid, Queue<int[]> queue) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    count++;
                else if (grid[i][j] == 2)
                    queue.offer(new int[] { i, j });
            }
        }
        return count;
    }
}
