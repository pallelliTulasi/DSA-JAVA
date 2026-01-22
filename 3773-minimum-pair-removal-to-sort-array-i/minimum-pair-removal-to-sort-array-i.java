import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        int operations = 0;
        
        while (true) {
            boolean isSorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    isSorted = false;
                    break;
                }
            }
            
            if (isSorted) {
                return operations;
            }
            
            int minSum = Integer.MAX_VALUE;
            int mergeIndex = -1;
            
            for (int i = 0; i < list.size() - 1; i++) {
                int currentSum = list.get(i) + list.get(i + 1);
                if (currentSum < minSum) {
                    minSum = currentSum;
                    mergeIndex = i;
                }
            }
            
            list.set(mergeIndex, minSum);
            list.remove(mergeIndex + 1);
            
            operations++;
        }
    }
}