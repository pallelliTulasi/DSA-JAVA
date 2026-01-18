class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] rowPrefix = new int[m][n + 1];
        int[][] colPrefix = new int[n][m + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPrefix[i][j + 1] = rowPrefix[i][j] + grid[i][j];
                colPrefix[j][i + 1] = colPrefix[j][i] + grid[i][j];
            }
        }

        for (int k = Math.min(m, n); k > 1; k--) {
            for (int r = 0; r <= m - k; r++) {
                for (int c = 0; c <= n - k; c++) {
                    if (isMagic(grid, rowPrefix, colPrefix, r, c, k)) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }
private boolean isMagic(int[][] grid, int[][] rowPrefix, int[][] colPrefix, int r, int c, int k) {
        Integer target = null;
        for (int i = r; i < r + k; i++) {
            int sum = rowPrefix[i][c + k] - rowPrefix[i][c];
            if (target == null) target = sum;
            else if (target != sum) return false;
        }
        for (int j = c; j < c + k; j++) {
            int sum = colPrefix[j][r + k] - colPrefix[j][r];
            if (target != sum) return false;
        }
        int diag1 = 0, diag2 = 0;
        for (int i = 0; i < k; i++) {
            diag1 += grid[r + i][c + i];
            diag2 += grid[r + i][c + k - 1 - i];
        }
        return target == diag1 && target == diag2;
    }
}
