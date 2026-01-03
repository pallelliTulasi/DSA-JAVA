class Solution {
    public int numOfWays(int n) {
        final int MOD = 1_000_000_007;
        long a = 6, b = 6;
        for(int i = 2; i <= n; i++){
            long newA = (2*a+2*b)% MOD;
            long newB = (2*a+3*b)% MOD;
            a = newA;
            b = newB;


        }
        return (int)((a+b)% MOD);
    }
}