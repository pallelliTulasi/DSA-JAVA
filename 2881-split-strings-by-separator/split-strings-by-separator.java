import java.util.*;

class Solution {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            // Escape separator for regex if needed
            String[] parts = word.split("\\" + separator);
            for (String part : parts) {
                if (!part.isEmpty()) {
                    result.add(part);
                }
            }
        }
        
        return result;
    }
}
