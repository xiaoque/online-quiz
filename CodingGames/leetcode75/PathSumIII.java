import java.util.HashMap;
import java.util.Map;

/*
 * @source https://leetcode.com/problems/path-sum-iii/submissions/1618427507/
 * @author xiaoque
 * @date 2025.04.26
 */
public class PathSumIII {
    // OPTION 1: recursion
    // go downwards => dfs
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        int count = dfs(root, targetSum);
        // start new path and count
        count += pathSum(root.left, targetSum);
        count += pathSum(root.right, targetSum);
        return count;
    }

    // count number of path
    public int dfs(TreeNode root, long targetSum) {
        if (root == null)
            return 0;
        int count = targetSum == root.val ? 1 : 0;
        count += dfs(root.left, targetSum - root.val);
        count += dfs(root.right, targetSum - root.val);
        return count;
    }

    // OPTION 2: backtracking
    class Solution {
        int count = 0;

        public int pathSum(TreeNode root, int targetSum) {
            if (root == null)
                return 0;
            // map to store frequeces of sum
            Map<Long, Integer> sumFreq = new HashMap<>();
            sumFreq.put(0l, 1);
            dfs(root, targetSum, 0, sumFreq);
            return count;
        }

        public void dfs(TreeNode root, int sum, long current, Map<Long, Integer> freq) {
            if (root == null)
                return;

            current += root.val;
            if (freq.containsKey(current - sum)) {
                count += freq.get(current - sum);
            }

            freq.put(current, freq.getOrDefault(current, 0) + 1);
            dfs(root.left, sum, current, freq);
            dfs(root.right, sum, current, freq);
            freq.put(current, freq.get(current) - 1);
        }
    }
}
