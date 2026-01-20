class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int target = nums.get(i);
            int res = -1;

            // Special case for 2 (the only even prime)
            if (target == 2) {
                ans[i] = -1;
                continue;
            }

            // Iterate from 0 to target to find the smallest x
            for (int x = 0; x < target; x++) {
                if ((x | (x + 1)) == target) {
                    res = x;
                    break; 
                }
            }
            ans[i] = res;
        }
        return ans;
    }
}