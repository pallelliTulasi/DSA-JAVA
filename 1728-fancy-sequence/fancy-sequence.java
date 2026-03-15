import java.util.ArrayList;
import java.util.List;

class Fancy {
    private final int MOD = 1000000007;
    private long add;
    private long mult;
    private List<Long> seq;

    public Fancy() {
        add = 0;
        mult = 1;
        seq = new ArrayList<>();
    }
    
    public void append(int val) {
        long currentVal = ((val - add) % MOD + MOD) % MOD; 
        long invMult = modPow(mult, MOD - 2);
        long x = (currentVal * invMult) % MOD;
        
        seq.add(x);
    }
    
    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }
    
    public void multAll(int m) {
        mult = (mult * m) % MOD;
        add = (add * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if (idx >= seq.size()) {
            return -1;
        }
        
        long x = seq.get(idx);
        long res = (x * mult + add) % MOD;
        return (int) res;
    }

    private long modPow(long base, int exp) {
        long res = 1;
        base = base % MOD;
        
        while (exp > 0) {
            if ((exp % 2) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }
        
        return res;
    }
}