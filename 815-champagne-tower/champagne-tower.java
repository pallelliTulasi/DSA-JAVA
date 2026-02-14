class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] t = new double[102][102];
        t[0][0] = (double) poured;
        for(int r=0;r<=query_row;r++){
            for(int c=0;c <= r; c++){
                if(t[r][c]>1){
                    double over = (t[r][c]-1.0)/2.0;
                    t[r+1][c] += over;
                    t[r+1][c+1]+=over;
                    t[r][c]=1;
                }
            }
        }
        return Math.min(1.0,t[query_row][query_glass]);
    }
}