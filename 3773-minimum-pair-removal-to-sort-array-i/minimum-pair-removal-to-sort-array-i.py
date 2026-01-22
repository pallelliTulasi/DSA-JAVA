class Solution:
    def minimumPairRemoval(self, nums: List[int]) -> int:
        operations = 0
        while True:
            is_sorted = True
            for i in range(len(nums) - 1):
                if nums[i] > nums[i + 1]:
                    is_sorted = False
                    break
            
            if is_sorted:
                return operations
            
            min_sum = float('inf')
            merge_index = -1
            
            for i in range(len(nums) - 1):
                current_sum = nums[i] + nums[i + 1]
                if current_sum < min_sum:
                    min_sum = current_sum
                    merge_index = i
            
            nums[merge_index] = nums[merge_index] + nums[merge_index + 1]
            nums.pop(merge_index + 1)
            
            operations += 1