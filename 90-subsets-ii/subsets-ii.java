import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Step 1: Sort to group duplicates
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // Add valid subset

        for (int i = start; i < nums.length; i++) {
            // Step 2: Skip duplicates at the same tree level
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            
            current.add(nums[i]);
            backtrack(i + 1, nums, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }
}