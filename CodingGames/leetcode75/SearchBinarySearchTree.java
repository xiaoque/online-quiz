/*
 * @source https://leetcode.com/problems/search-in-a-binary-search-tree/
 * @author xiaoque
 * @date 2025.04.26
 */
public class SearchBinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val == val)
                return curr;
            if (curr.val > val)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return null;
    }
}
