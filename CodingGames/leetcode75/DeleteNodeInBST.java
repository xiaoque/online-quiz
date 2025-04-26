/*
 * @source 
 * @author xiaoque
 * @date 2025.04.25
 */
public class DeleteNodeInBST {
    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null)
            return root;

        // end condition, found the key
        if (root.val == key) {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                TreeNode right = root.right;
                while (right.left != null)
                    right = right.left;
                // place left subtree under right node
                right.left = root.left;
                root = root.right;
                return root;
            }
        }

        // call recursion
        if (root.val > key)
            root.left = deleteNode(root.left, key);
        if (root.val < key)
            root.right = deleteNode(root.right, key);

        return root;
    }
}
