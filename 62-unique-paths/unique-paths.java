class Solution {
    public int uniquePaths(int m, int n) {
        long paths = 1;
        for (int i = 1; i <= n - 1; i++) {
            paths = paths * (m + i - 1) / i;
        }
        return (int) paths;
    }
}