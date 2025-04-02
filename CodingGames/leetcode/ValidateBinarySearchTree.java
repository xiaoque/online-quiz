
/*
 * @source https://leetcode.com/problems/validate-binary-search-tree/
 * @author xiaoque
 * @date 2025.03.22
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        // see if a node with a current max value left < root and < currmax
        // do it recursively (left, root.val) (right, )
        return dp(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    private boolean dp(TreeNode root, long max, long min) {
        if (root == null)
            return true;
        if (root.val >= max || root.val <= min)
            return false;
        return dp(root.left, root.val, min) && dp(root.right, max, root.val);

    }
}
