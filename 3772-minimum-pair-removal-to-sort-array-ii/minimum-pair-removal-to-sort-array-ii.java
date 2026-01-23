import java.util.TreeSet;

class Solution {
    private static class Pair implements Comparable<Pair> {
        long sum;
        int index;

        Pair(long sum, int index) {
            this.sum = sum;
            this.index = index;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.sum != other.sum) {
                return Long.compare(this.sum, other.sum);
            }
            return Integer.compare(this.index, other.index);
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        long[] val = new long[n];
        for (int i = 0; i < n; i++) val[i] = nums[i];

        int[] next = new int[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = i + 1;
            prev[i] = i - 1;
        }

        TreeSet<Pair> pq = new TreeSet<>();
        int inversions = 0;

        for (int i = 0; i < n - 1; i++) {
            pq.add(new Pair(val[i] + val[i + 1], i));
            if (val[i] > val[i + 1]) inversions++;
        }

        int ops = 0;

        while (inversions > 0) {
            Pair best = pq.pollFirst();
            if (best == null) break;

            int i = best.index;
            int j = next[i];

            int left = prev[i];
            int right = next[j];

            if (left >= 0) {
                pq.remove(new Pair(val[left] + val[i], left));
                if (val[left] > val[i]) inversions--;
            }
            if (right < n) {
                pq.remove(new Pair(val[j] + val[right], j));
                if (val[j] > val[right]) inversions--;
            }
            if (val[i] > val[j]) inversions--;

            long newSum = val[i] + val[j];
            val[i] = newSum;

            next[i] = right;
            if (right < n) prev[right] = i;

            if (left >= 0) {
                pq.add(new Pair(val[left] + val[i], left));
                if (val[left] > val[i]) inversions++;
            }
            if (right < n) {
                pq.add(new Pair(val[i] + val[right], i));
                if (val[i] > val[right]) inversions++;
            }

            ops++;
        }

        return ops;
    }
}