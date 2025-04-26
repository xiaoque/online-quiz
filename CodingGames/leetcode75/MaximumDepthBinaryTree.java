/*
 * @source https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 * @author xiaoque
 * @date 2025.04.26
 */
public class MaximumDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        // end condition left return 0
        if (root == null)
            return 0;
        // else return max from left and right node
        int depth = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        return depth;
    }
}
