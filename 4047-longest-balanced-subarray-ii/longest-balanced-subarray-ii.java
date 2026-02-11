import java.util.HashMap;
import java.util.Map;

class Solution {
    class SegmentTree {
        int[] min, max, lazy;
        
        public SegmentTree(int n) {
            min = new int[4 * n];
            max = new int[4 * n];
            lazy = new int[4 * n];
        }
        
        private void apply(int node, int val) {
            min[node] += val;
            max[node] += val;
            lazy[node] += val;
        }
        
        private void pushDown(int node) {
            if (lazy[node] != 0) {
                apply(2 * node, lazy[node]);
                apply(2 * node + 1, lazy[node]);
                lazy[node] = 0;
            }
        }
        
        private void pushUp(int node) {
            min[node] = Math.min(min[2 * node], min[2 * node + 1]);
            max[node] = Math.max(max[2 * node], max[2 * node + 1]);
        }
        
        public void modify(int node, int l, int r, int ql, int qr, int val) {
            if (ql <= l && r <= qr) {
                apply(node, val);
                return;
            }
            pushDown(node);
            int mid = l + (r - l) / 2;
            if (ql <= mid) modify(2 * node, l, mid, ql, qr, val);
            if (qr > mid) modify(2 * node + 1, mid + 1, r, ql, qr, val);
            pushUp(node);
        }
        
        public int query(int node, int l, int r, int target) {
            if (l == r) return l;
            
            pushDown(node);
            int mid = l + (r - l) / 2;
            
            if (min[2 * node] <= target && target <= max[2 * node]) {
                return query(2 * node, l, mid, target);
            } else {
                return query(2 * node + 1, mid + 1, r, target);
            }
        }
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n + 1);
        Map<Integer, Integer> last = new HashMap<>();
        
        int now = 0;
        int ans = 0;
        
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            int det = (x % 2 == 0) ? 1 : -1;
            
            if (last.containsKey(x)) {
                st.modify(1, 0, n, last.get(x), n, -det);
                now -= det;
            }
            
            last.put(x, i);
            
            st.modify(1, 0, n, i, n, det);
            now += det;
            
            int pos = st.query(1, 0, n, now);
            ans = Math.max(ans, i - pos);
        }
        
        return ans;
    }
}