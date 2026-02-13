import java.util.*;

class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int maxLen = 0;

        maxLen = Math.max(maxLen, checkSingle(s, 'a'));
        maxLen = Math.max(maxLen, checkSingle(s, 'b'));
        maxLen = Math.max(maxLen, checkSingle(s, 'c'));

        int[] buffer = new int[2 * n + 1];
        Arrays.fill(buffer, -2);

        maxLen = Math.max(maxLen, checkPair(s, 'a', 'b', 'c', buffer));
        maxLen = Math.max(maxLen, checkPair(s, 'a', 'c', 'b', buffer));
        maxLen = Math.max(maxLen, checkPair(s, 'b', 'c', 'a', buffer));

        maxLen = Math.max(maxLen, checkAllThree(s));

        return maxLen;
    }

    private int checkSingle(String s, char target) {
        int max = 0;
        int current = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == target) {
                current++;
            } else {
                max = Math.max(max, current);
                current = 0;
            }
        }
        return Math.max(max, current);
    }

    private int checkPair(String s, char c1, char c2, char forbidden, int[] seen) {
        int n = s.length();
        int max = 0;
        int start = 0;

        for (int i = 0; i <= n; i++) {
            if (i == n || s.charAt(i) == forbidden) {
                if (start < i) {
                    max = Math.max(max, processSegment(s, start, i, c1, c2, seen, n));
                }
                start = i + 1;
            }
        }
        return max;
    }

    private int processSegment(String s, int start, int end, char c1, char c2, int[] seen, int n) {
        int max = 0;
        int balance = 0;
        
        seen[n] = start - 1; 
        
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (c == c1) balance++;
            else balance--;
            
            int offsetBal = balance + n;
            if (seen[offsetBal] != -2) {
                max = Math.max(max, i - seen[offsetBal]);
            } else {
                seen[offsetBal] = i;
            }
        }
        
        balance = 0;
        seen[n] = -2; 
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (c == c1) balance++;
            else balance--;
            seen[balance + n] = -2;
        }
        
        return max;
    }

    private int checkAllThree(String s) {
        int max = 0;
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);
        
        int a = 0, b = 0, c = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a') a++;
            else if (ch == 'b') b++;
            else if (ch == 'c') c++;
            
            long diff1 = a - b;
            long diff2 = b - c;
            
            long key = (diff1 << 32) | (diff2 & 0xFFFFFFFFL);
            
            if (map.containsKey(key)) {
                max = Math.max(max, i - map.get(key));
            } else {
                map.put(key, i);
            }
        }
        return max;
    }
}