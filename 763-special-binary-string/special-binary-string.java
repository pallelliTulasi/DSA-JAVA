import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String makeLargestSpecial(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        
        int count = 0;
        int i = 0;
        List<String> specialSubstrings = new ArrayList<>();
        
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '1') {
                count++;
            } else {
                count--;
            }
            
            if (count == 0) {
                String inner = s.substring(i + 1, j);
                String maximizedInner = makeLargestSpecial(inner);
                specialSubstrings.add("1" + maximizedInner + "0");
                i = j + 1;
            }
        }
        
        Collections.sort(specialSubstrings, Collections.reverseOrder());
        return String.join("", specialSubstrings);
    }
}