/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    long total = 0, max = 0;
    public int maxProduct(TreeNode root) {
        total = dfs(root);
        dfs(root);
        return (int)(max % 1000000007);
    }
    private long dfs(TreeNode node) {
        if (node == null) return 0;
        long sum = node.val + dfs(node.left) + dfs(node.right);
        max = Math.max(max, sum * (total - sum));
        return sum;
    }
}
