
/*
 * @source https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 * @author xiaoque
 * @date 2025.03.22
 */
public class BinarySearchTreeToGreaterSumTree {
    public TreeNode bstToGst(TreeNode root) {
        // traverse the tree in post order, (right, root, left)
        // recursively dp(right), compute root, dp(left)
        // with return value current sum

        dp(root, 0);
        return root;
    }

    private int dp(TreeNode node, int sum) {
        if (node != null) {
            node.val = node.val + dp(node.right, sum);
            return dp(node.left, node.val);
        }
        return sum;
    }
}
