class Solution:
    def minBitwiseArray(self, nums: list[int]) -> list[int]:
        ans = []
        for target in nums:
            if target == 2:
                ans.append(-1)
            else:
                for bit in range(31):
                    if not (target >> (bit + 1) & 1):
                        ans.append(target ^ (1 << bit))
                        break
        return ans