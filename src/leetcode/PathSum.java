package leetcode;

/*
 * @source https://leetcode.com/problems/path-sum/
 * @author xiaoque
 * @date 2025.03.22
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // go dfs, try each node until find the result
        if (root == null)
            return false;
        if (root.left == null && root.right == null && root.val == targetSum)
            return true;
        boolean left = false, right = false;
        if (root.left != null) {
            left = hasPathSum(root.left, targetSum - root.val);
        }
        if (root.right != null) {
            right = hasPathSum(root.right, targetSum - root.val);
        }
        return left || right;
    }
}
