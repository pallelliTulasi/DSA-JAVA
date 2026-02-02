import java.util.TreeMap;

class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        long baseCost = nums[0];
        int k_needed = k - 1;
        
        if (k_needed == 0) return baseCost;
        
        int n = nums.length;
        int w = dist + 1;
        
        TreeMap<Integer, Integer> top = new TreeMap<>();
        TreeMap<Integer, Integer> rest = new TreeMap<>();
        
        long currentTopSum = 0;
        int topCount = 0;
        
        int limit = Math.min(n - 1, w);
        for (int i = 1; i <= limit; i++) {
            int val = nums[i];
            top.put(val, top.getOrDefault(val, 0) + 1);
            currentTopSum += val;
            topCount++;
        }
        
        while (topCount > k_needed) {
            int largest = top.lastKey();
            removeOne(top, largest);
            currentTopSum -= largest;
            topCount--;
            rest.put(largest, rest.getOrDefault(largest, 0) + 1);
        }
        
        long minSum = currentTopSum;
        
        for (int i = w + 1; i < n; i++) {
            int outVal = nums[i - w];
            if (rest.containsKey(outVal)) {
                removeOne(rest, outVal);
            } else {
                removeOne(top, outVal);
                currentTopSum -= outVal;
                topCount--;
            }
            
            int inVal = nums[i];
            top.put(inVal, top.getOrDefault(inVal, 0) + 1);
            currentTopSum += inVal;
            topCount++;
            
            if (topCount > k_needed) {
                int largest = top.lastKey();
                removeOne(top, largest);
                currentTopSum -= largest;
                topCount--;
                rest.put(largest, rest.getOrDefault(largest, 0) + 1);
            }
            
            while (topCount < k_needed && !rest.isEmpty()) {
                int smallestRest = rest.firstKey();
                removeOne(rest, smallestRest);
                top.put(smallestRest, top.getOrDefault(smallestRest, 0) + 1);
                currentTopSum += smallestRest;
                topCount++;
            }
            
            while (!rest.isEmpty() && top.lastKey() > rest.firstKey()) {
                int valTop = top.lastKey();
                int valRest = rest.firstKey();
                
                removeOne(top, valTop);
                currentTopSum -= valTop;
                topCount--;
                
                removeOne(rest, valRest);
                
                rest.put(valTop, rest.getOrDefault(valTop, 0) + 1);
                top.put(valRest, top.getOrDefault(valRest, 0) + 1);
                currentTopSum += valRest;
                topCount++;
            }
            
            minSum = Math.min(minSum, currentTopSum);
        }
        
        return baseCost + minSum;
    }
    
    private void removeOne(TreeMap<Integer, Integer> map, int key) {
        int count = map.get(key);
        if (count == 1) {
            map.remove(key);
        } else {
            map.put(key, count - 1);
        }
    }
}