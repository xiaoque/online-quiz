/*
 * @source https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/description/
 * @author xiaoque
 * @date 2025.04.02
 */
public class LongestZigZagPathInBinaryTree {
    class Solution {
        public int max = 0;

        public int longestZigZag(TreeNode root) {
            dfs(root, true, 0);
            dfs(root, false, 0);
            return max;
        }

        // isLeaf stores current direction
        private void dfs(TreeNode root, boolean isLeft, int curr) {
            if (root == null)
                return;
            max = Math.max(max, curr);
            // for each node, either follows previous path or creat a new one
            if (isLeft) {
                dfs(root.left, false, curr + 1);
                dfs(root.right, true, 1);
            } else {
                dfs(root.right, true, curr + 1);
                dfs(root.left, false, 1);
            }
        }
    }
}

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
