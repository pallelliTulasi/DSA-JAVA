class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {

        
        for (int i = 0; i < k / 2; i++) {

           
            int row1 = x + i;
            int row2 = x + k - 1 - i;

            for (int j = 0; j < k; j++) {
                int col = y + j;

                int temp = grid[row1][col];
                grid[row1][col] = grid[row2][col];
                grid[row2][col] = temp;
            }
        }

        return grid;
    }
}