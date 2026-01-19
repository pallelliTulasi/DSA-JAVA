class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] prefix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i - 1][j - 1] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
        int left = 0, right = Math.min(m, n), res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(prefix, mid, threshold)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
private boolean check(int[][] prefix, int len, int threshold) {
        for (int i = len; i < prefix.length; i++) {
            for (int j = len; j < prefix[0].length; j++) {
                int sum = prefix[i][j] - prefix[i - len][j] - prefix[i][j - len] + prefix[i - len][j - len];
                if (sum <= threshold) return true;
            }
        }
        return false;
    }
}
