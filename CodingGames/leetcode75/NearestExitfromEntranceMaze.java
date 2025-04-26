import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @source https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/
 * @author xiaoque
 * @date 2025.04.12
 */
public class NearestExitfromEntranceMaze {
    // maze[entrance[0]][entrance[1]] as root
    // [i][j] -> [i - 1][j] / [i + 1][j] / [i][j - 1] / [i][j + 1] (need range
    // check)
    public static int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int nearestExit(char[][] maze, int[] entrance) {
        // bfs, use queue to store next node
        Deque<int[]> queue = new ArrayDeque<>();
        // start from level 0, mark entrance as visited
        int level = 0;
        int m = maze.length;
        int n = maze[0].length;
        maze[entrance[0]][entrance[1]] = '+';
        queue.addLast(entrance);
        int x = 0, y = 0;
        // while queue is not empty
        while (!queue.isEmpty()) {
            // get the size of current level, visit all nodes, level ++
            int size = queue.size();
            level++;
            while (size > 0) {
                int[] current = queue.removeFirst();
                // visite node by iterating 4 directions
                for (int[] move : directions) {
                    x = current[0] + move[0];
                    y = current[1] + move[1];
                    // check if out of range or wall
                    if (x < 0 || y < 0 || x == m || y == n)
                        continue;
                    if (maze[x][y] == '+')
                        continue;
                    // if a direction has exist, return
                    if (x == 0 || y == 0 || x == m - 1 || y == n - 1)
                        return level;
                    // add to queue, and mark it as visited
                    queue.addLast(new int[] { x, y });
                    maze[x][y] = '+';
                }
                size--;
            }
        }
        // return -1 if no such path exists
        return -1;
    }
}
