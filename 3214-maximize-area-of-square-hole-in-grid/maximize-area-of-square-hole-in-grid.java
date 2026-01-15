import java.util.*;

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxH = longestConsecutive(hBars);
        int maxV = longestConsecutive(vBars);
        
        // side length of square hole
        int side = Math.min(maxH, maxV) + 1;
        
        return side * side;
    }
    
    private int longestConsecutive(int[] bars) {
        if (bars.length == 0) return 0;
        Arrays.sort(bars);
        int maxLen = 1, currLen = 1;
        
        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i-1] + 1) {
                currLen++;
            } else {
                currLen = 1;
            }
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }
}
