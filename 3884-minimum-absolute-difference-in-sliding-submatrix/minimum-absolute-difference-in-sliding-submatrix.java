import java.util.Arrays;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        
        int[] vals = new int[k * k];
        
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                
                int idx = 0;
                for (int r = i; r < i + k; r++) {
                    for (int c = j; c < j + k; c++) {
                        vals[idx++] = grid[r][c];
                    }
                }
                
                Arrays.sort(vals);
                
                int minDiff = Integer.MAX_VALUE;
                for (int x = 1; x < vals.length; x++) {
                    if (vals[x] != vals[x - 1]) {
                        minDiff = Math.min(minDiff, vals[x] - vals[x - 1]);
                    }
                }
                
                if (minDiff == Integer.MAX_VALUE) {
                    ans[i][j] = 0;
                } else {
                    ans[i][j] = minDiff;
                }
            }
        }
        
        return ans;
    }
}