/*
 * @source https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree
 * @author xiaoque
 * @date 2025.04.03
 */
public class LowestCommonAncestorBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // end condition for the traversal
        if (root == null || root == p || root == q)
            return root;

        // traverse left and right nodes
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // case when p and q are descendant of current node
        if (left != null && right != null)
            return root;

        // null means node p, q not found in left branch
        if (left == null)
            return right;
        return left;

    }
}
