import java.util.LinkedList;
import java.util.List;

/*
 * @source https://leetcode.com/problems/leaf-similar-trees/description/
 * @author xiaoque
 * @date 2025.04.26
 */
public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // same value and same sequence
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();

        getLeafNodes(root1, list1);
        getLeafNodes(root2, list2);

        // compare list

        return list1.equals(list2);
    }

    // visite dfs, add all leaf node then compare
    private void getLeafNodes(TreeNode root, List<Integer> leaves) {
        if (root == null)
            return;
        if (root.left == null && root.right == null)
            leaves.add(root.val);
        else {
            getLeafNodes(root.left, leaves);
            getLeafNodes(root.right, leaves);
        }
    }
}
