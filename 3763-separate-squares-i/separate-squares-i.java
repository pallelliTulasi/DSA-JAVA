class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0;
        double low = Double.MAX_VALUE;
        double high = Double.MIN_VALUE;

        for (int[] s : squares) {
            int y = s[1];
            int l = s[2];
            totalArea += (double) l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double halfArea = totalArea / 2.0;

        for (int i = 0; i < 60; i++) {
            double mid = (low + high) / 2.0;
            double areaBelow = 0;

            for (int[] s : squares) {
                int y = s[1];
                int l = s[2];
                if (mid <= y) continue;
                if (mid >= y + l) areaBelow += (double) l * l;
                else areaBelow += (double) l * (mid - y);
            }

            if (areaBelow < halfArea) low = mid;
            else high = mid;
        }

        return low;
    }
}
