class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            int distinctCount = 0;
            int maxFreq = 0;

            for (int j = i; j < n; j++) {
                int charIndex = s.charAt(j) - 'a';

                if (freq[charIndex] == 0) {
                    distinctCount++;
                }
                
                freq[charIndex]++;
                maxFreq = Math.max(maxFreq, freq[charIndex]);

                if (maxFreq * distinctCount == (j - i + 1)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }
}