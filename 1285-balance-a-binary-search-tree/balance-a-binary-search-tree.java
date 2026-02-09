import java.util.ArrayList;
import java.util.List;

class Solution {
    private List<Integer> sortedNodes = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        inorderTraversal(root);
        return buildBalancedBST(0, sortedNodes.size() - 1);
    }

    private void inorderTraversal(TreeNode node) {
        if (node == null) return;
        
        inorderTraversal(node.left);
        sortedNodes.add(node.val);
        inorderTraversal(node.right);
    }

    private TreeNode buildBalancedBST(int start, int end) {
        if (start > end) return null;

        int mid = start + (end - start) / 2;

        TreeNode node = new TreeNode(sortedNodes.get(mid));

        node.left = buildBalancedBST(start, mid - 1);
        node.right = buildBalancedBST(mid + 1, end);

        return node;
    }
}