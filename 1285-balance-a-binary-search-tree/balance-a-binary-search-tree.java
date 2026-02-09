class Solution {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> vals = new ArrayList<>();

        inorder(root, vals);
        return build(vals, 0, vals.size() - 1);
    }

    private void inorder(TreeNode node, List<Integer> vals) {
        if (node == null) return;
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }

    private TreeNode build(List<Integer> vals, int l, int r) {
        if (l > r) return null;

        int m = (l + r) / 2;
        TreeNode node = new TreeNode(vals.get(m));
        node.left = build(vals, l, m - 1);
        node.right = build(vals, m + 1, r);

        return node;
    }
}
