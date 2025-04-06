/*
 * @source https://leetcode.com/problems/number-of-provinces/
 * @author xiaoque
 * @date 2025.04.06
 */
public class NumberProvinces {
    // ----------------- iterate from city ----------------------------------------
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(isConnected, visited, i);
            }
        }
        return count;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int curr) {
        visited[curr] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[i][curr] == 1 && !visited[i])
                dfs(isConnected, visited, i);
        }

    }

    // ----------------- iterate from matrix -------------------------------------
    // max 200 cities, using array to store visited
    // isConnected[i][j] = [j][i], then visit [i][0 - n] to check if connects new
    // city
    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        // visit matrix, do dfs if has 1, and mark visited
        for (int i = 0; i < n; i++) {
            // because the matrix is symetric, only need to visit half
            for (int j = 0; j < i; j++) {
                if (isConnected[i][j] == 1 && !visited[i] && !visited[j]) {
                    dfs(isConnected, visited, i, j);
                    count++;
                }
            }
        }
        // add up all non-visited city, they form a province itself
        for (boolean visit : visited) {
            count += visit ? 0 : 1;
        }
        return count;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int row, int col) {
        // end condition for the recursion
        if (visited[row] && visited[col]) {
            return;
        }
        // check row and col seperately
        if (!visited[row]) {
            visited[row] = true;
            for (int i = 0; i < isConnected.length; i++) {
                if (isConnected[i][row] == 1)
                    dfs(isConnected, visited, i, row);
            }
        }
        if (!visited[col]) {
            visited[col] = true;
            for (int i = 0; i < isConnected.length; i++) {
                if (isConnected[i][col] == 1)
                    dfs(isConnected, visited, i, col);
            }
        }

    }
}
