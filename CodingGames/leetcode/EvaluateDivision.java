import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * @source https://leetcode.com/problems/evaluate-division
 * @author xiaoque
 * @date 2025.04.12
 */
public class EvaluateDivision {
    // case 1, a / b, b / c => a / c by *
    // case 2, a / b => b / a by 1 / result
    // convert to a graph with weights
    // correct direction then *, reversed direction /
    // adjacency list with destination and weight
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // build the adjacency list
        Map<String, Map<String, Double>> map = new HashMap<>();

        // initialize the graph
        for (int i = 0; i < equations.size(); i++) {
            String start = equations.get(i).get(0);
            String end = equations.get(i).get(1);
            // for a / b, put {b, value} for entry a, and put {a, 1 / value} for entry b
            map.computeIfAbsent(start, k -> new HashMap<>()).put(end, values[i]);
            map.computeIfAbsent(end, k -> new HashMap<>()).put(start, 1 / values[i]);
        }

        double[] result = new double[queries.size()];
        // for each list in queries
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            if (map.containsKey(start) && map.containsKey(end)) {
                // visit the graph and store the result
                result[i] = dfs(map, start, end, new HashSet<String>());
            } else {
                // if contains letters not apeared before, put -1
                result[i] = -1;
            }
        }

        return result;
    }

    // visite the graph and return result
    public double dfs(Map<String, Map<String, Double>> map, String start, String end, Set<String> visited) {
        // if start == end
        if (start.equals(end))
            return 1;

        visited.add(start);
        // compare neighbour with end node
        // if match return value, or continue traversal
        for (Map.Entry<String, Double> entry : map.get(start).entrySet()) {
            if (!visited.contains(entry.getKey())) {
                if (entry.getKey().equals(end))
                    return entry.getValue();
                double result = dfs(map, entry.getKey(), end, visited);
                if (result != -1) {
                    return entry.getValue() * result;
                }
            }
        }
        // return when not found
        return -1;
    }
}
