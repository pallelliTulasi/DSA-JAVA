from typing import List

class Solution:
    def maximizeSquareHoleArea(self, n: int, m: int, hBars: List[int], vBars: List[int]) -> int:
        def longest_consecutive(bars: List[int]) -> int:
            if not bars:
                return 0
            bars.sort()
            max_len = 1
            curr_len = 1
            for i in range(1, len(bars)):
                if bars[i] == bars[i-1] + 1:
                    curr_len += 1
                else:
                    curr_len = 1
                max_len = max(max_len, curr_len)
            return max_len
        
        maxH = longest_consecutive(hBars)
        maxV = longest_consecutive(vBars)
        
       
        side = min(maxH, maxV) + 1
        return side * side
