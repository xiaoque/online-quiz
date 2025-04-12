import java.util.ArrayList;
import java.util.List;

/*
 * @source https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero
 * @author xiaoque
 * @date 2025.04.11
 */
public class ReorderRoutesMakeAllPathsLeadToCityZero {
    public int minReorder(int n, int[][] connections) {
        // create the adjacency list
        List<List<Integer>> list = createAdjacencyList(n, connections);

        // traverse the graph from 0 until end node, if out-edge, count ++
        return dfs(list, 0, new boolean[n]);
    }

    public List<List<Integer>> createAdjacencyList(int n, int[][] connections) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        // for in-edges, change it into negative number (correct direction)
        // for out-edges, put the positive number (wrong direction)
        for (int[] path : connections) {
            list.get(path[0]).add(path[1]);
            list.get(path[1]).add(-path[0]);
        }
        return list;
    }

    public int dfs(List<List<Integer>> list, int current, boolean[] visited) {
        // return current nodes's count + dfs(its neighbours)
        int count = 0;
        visited[current] = true;
        for (Integer city : list.get(current)) {
            // avoid re-visiting and re-counting cities
            if (!visited[Math.abs(city)])
                count += dfs(list, Math.abs(city), visited) + (city > 0 ? 1 : 0);
        }
        return count;
    }
}
