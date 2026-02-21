class Solution {
    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        int mask = 0b10100010100010101100;
        for (int i = left; i <= right; i++) {
            if (((1 << Integer.bitCount(i)) & mask) != 0) {
                count++;
            }
        }
        return count;
    }
}
