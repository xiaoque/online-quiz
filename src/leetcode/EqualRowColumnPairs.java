package leetcode;

import java.util.HashMap;
import java.util.Map;

/*
 * @source https://leetcode.com/problems/equal-row-and-column-pairs/
 * @author xiaoque
 * @date 2025.03.24
 */
public class EqualRowColumnPairs {
    /**
     * Note: transform row and col into string then compare
     */
    public int equalPairs(int[][] grid) {
        // count the freq of row, cant sure that rows are unique
        Map<String, Integer> rows = new HashMap<>();

        for (int i = 0; i < grid.length; i++) {
            String row = formRowColumnString(grid, i, true);
            rows.put(row, rows.getOrDefault(row, 0) + 1);
        }

        int count = 0;
        for (int i = 0; i < grid[0].length; i++) {
            String col = formRowColumnString(grid, i, false);
            count += rows.getOrDefault(col, 0);
        }
        return count;
    }

    private String formRowColumnString(int[][] grid, int index, boolean isRow) {
        StringBuilder sb = new StringBuilder();
        if (isRow) {
            for (int i = 0; i < grid[0].length; i++) {
                sb.append('-' + grid[index][i]);
            }
        } else {
            for (int i = 0; i < grid.length; i++) {
                sb.append('-' + grid[i][index]);
            }
        }
        return sb.toString();
    }
}
