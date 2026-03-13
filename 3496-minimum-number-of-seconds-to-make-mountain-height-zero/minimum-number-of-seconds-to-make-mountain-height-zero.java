class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long minW = workerTimes[0];
        for (int w : workerTimes) {
            if (w < minW) {
                minW = w;
            }
        }

        long low = 1;
        long high = minW * (long) mountainHeight * (mountainHeight + 1) / 2;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (canDo(mid, mountainHeight, workerTimes)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canDo(long limit, int mountainHeight, int[] workerTimes) {
        long totalHeight = 0;
        
        for (long w : workerTimes) {
            long x = (long) ((Math.sqrt(1.0 + 8.0 * ((double) limit / w)) - 1.0) / 2.0);
            
            while ((w * (x + 1) * (x + 2)) / 2 <= limit) {
                x++;
            }
            
            while (x > 0 && (w * x * (x + 1)) / 2 > limit) {
                x--;
            }
            
            totalHeight += x;
            
            if (totalHeight >= mountainHeight) {
                return true; 
            }
        }
        
        return totalHeight >= mountainHeight;
    }
}