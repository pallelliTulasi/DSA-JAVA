class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int num = i, ok = 1;
            while (num > 0) {
                int d = num % 10;
                if (d == 0 || i % d != 0) { ok = 0; break; }
                num /= 10;
            }
            if (ok == 1) res.add(i);
        }
        return res;
        
    }
}