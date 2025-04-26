/*
 * @source https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 * @author xiaoque
 * @date 2025.04.26
 */
public class CountGoodNodesBinaryTree {
    /*
     * Good if current value >= root and max value from root to current
     * traversal using DFS with memo value of max
     */
    public int goodNodes(TreeNode root) {

        // call dfs
        return dfs(root, root.val);
    }

    private int dfs(TreeNode root, int value) {
        if (root == null)
            return 0;
        // update count if current node is good
        int result = root.val >= value ? 1 : 0;
        // update value
        value = Math.max(value, root.val);
        // traverse tree
        result += dfs(root.left, value) + dfs(root.right, value);
        return result;
    }
}
