import java.util.HashMap;
import java.util.Map;

/*
 * @source https://leetcode.com/problems/coin-change/
 * @author xiaoque
 * @date 2025.05.14
 */
public class ConstructBinaryTree {
    /*
     * preorder: root, left, right
     * inorder: left, root, right
     * 1. a tree in inorder [l, r], their root node preorder[i] in inorder -> m
     * 2. left length = m - l, right length = r - m
     * 3. root of left tree in preorder i + 1, root of right tree i+1+m-l
     * 4, repeat step 1-3 to find root/current node, and then recursive for left and right tree
     */
    public BinaryTreeNode buildTree(int[] preorder, int[] inorder) {
        // creat a mapping from value to index
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return constructTree(preorder, map, 0, 0, inorder.length - 1);
    }

    /*
    * $root: root index in preorder
    * $left: left border of the subtree in inorder
    * $right: right border of the subtree in inorder
    * */
    private BinaryTreeNode constructTree(int[] preorder, Map<Integer, Integer> map, int root, int left, int right) {
        // end condition when l and r meet
        if (left > right)
            return null;

        // find corresponding index in inorder
        int m = map.get(preorder[root]);
        BinaryTreeNode current = new BinaryTreeNode(preorder[root]);

        // continue the construction
        current.left = constructTree(preorder, map, root + 1, left, m - 1);
        current.right = constructTree(preorder, map, root + 1 + m - left, m + 1, right);
        return current;
    }
}
