from typing import List

class Solution:
    def minBitwiseArray(self, nums: List[int]) -> List[int]:
        ans = []
        for n in nums:
            if n == 2:
                ans.append(-1)
            else:
                # Find the smallest x such that x | (x + 1) == n
                # Since constraints are small (<= 1000), we can loop
                found = False
                for x in range(n):
                    if (x | (x + 1)) == n:
                        ans.append(x)
                        found = True
                        break
                
                # If no x is found (though logic suggests checking n=2 handles most),
                # append -1 just in case.
                if not found:
                    ans.append(-1)
        return ans