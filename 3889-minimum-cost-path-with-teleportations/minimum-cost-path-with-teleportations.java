import java.util.*;

class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int INF = Integer.MAX_VALUE / 2;

        int[][][] dp = new int[k + 1][m][n];
        
        for (int i = 0; i <= k; i++) {
            for (int r = 0; r < m; r++) {
                Arrays.fill(dp[i][r], INF);
            }
        }

        dp[0][0][0] = 0;
        
        runGridDP(dp[0], grid, m, n);

        List<int[]> cells = new ArrayList<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                cells.add(new int[]{r, c, grid[r][c]});
            }
        }
        cells.sort((a, b) -> Integer.compare(b[2], a[2]));

        for (int t = 1; t <= k; t++) {
            for (int r = 0; r < m; r++) {
                System.arraycopy(dp[t - 1][r], 0, dp[t][r], 0, n);
            }

            int runningMin = INF;
            int idx = 0;
            while (idx < cells.size()) {
                int val = cells.get(idx)[2];
                int start = idx;

                while (idx < cells.size() && cells.get(idx)[2] == val) {
                    idx++;
                }

                for (int i = start; i < idx; i++) {
                    int[] c = cells.get(i);
                    runningMin = Math.min(runningMin, dp[t - 1][c[0]][c[1]]);
                }

                if (runningMin < INF) {
                    for (int i = start; i < idx; i++) {
                        int[] c = cells.get(i);
                        dp[t][c[0]][c[1]] = Math.min(dp[t][c[0]][c[1]], runningMin);
                    }
                }
            }

            runGridDP(dp[t], grid, m, n);
        }

        return dp[k][m - 1][n - 1];
    }

    private void runGridDP(int[][] layer, int[][] grid, int m, int n) {
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (layer[r][c] >= Integer.MAX_VALUE / 2) continue;

                if (r + 1 < m) {
                    int nextCost = layer[r][c] + grid[r + 1][c];
                    if (nextCost < layer[r + 1][c]) {
                        layer[r + 1][c] = nextCost;
                    }
                }
                if (c + 1 < n) {
                    int nextCost = layer[r][c] + grid[r][c + 1];
                    if (nextCost < layer[r][c + 1]) {
                        layer[r][c + 1] = nextCost;
                    }
                }
            }
        }
    }
}