class Solution {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k; 
        boolean[] seen = new boolean[need];
        int allOnesMask = need - 1; 
        int hashVal = 0;
        
        for (int i = 0; i < s.length(); i++) {
            hashVal = ((hashVal << 1) & allOnesMask) | (s.charAt(i) - '0');
            
            if (i >= k - 1) {
                if (!seen[hashVal]) {
                    seen[hashVal] = true;
                    need--;
                    
                    if (need == 0) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}