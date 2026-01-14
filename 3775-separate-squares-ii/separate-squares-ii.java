import java.util.*;

class Solution {
    public double separateSquares(int[][] squares) {
        List<int[]> events = new ArrayList<>();
        for (int[] sq : squares) {
            int x = sq[0], y = sq[1], l = sq[2];
            events.add(new int[]{y, 1, x, x + l});
            events.add(new int[]{y + l, -1, x, x + l});
        }
        events.sort(Comparator.comparingInt(a -> a[0]));

        Map<Long, Integer> active = new HashMap<>();
        List<Integer> stripY1 = new ArrayList<>();
        List<Integer> stripY2 = new ArrayList<>();
        List<Double> stripWidth = new ArrayList<>();
        List<Double> prefix = new ArrayList<>();
        prefix.add(0.0);

        double totalArea = 0.0;
        int prevY = events.get(0)[0];

        for (int[] e : events) {
            int y = e[0], typ = e[1], x1 = e[2], x2 = e[3];
            if (y > prevY) {
                double width = unionWidthFromActive(active);
                double area = width * (y - prevY);
                totalArea += area;
                prefix.add(totalArea);
                stripY1.add(prevY);
                stripY2.add(y);
                stripWidth.add(width);
                prevY = y;
            }
long key = pack(x1, x2);
            if (typ == 1) {
                active.put(key, active.getOrDefault(key, 0) + 1);
            } else {
                int c = active.getOrDefault(key, 0);
                if (c <= 1) active.remove(key);
                else active.put(key, c - 1);
            }
        }

        double half = totalArea / 2.0;
        for (int i = 0; i < stripWidth.size(); i++) {
            if (prefix.get(i + 1) >= half) {
                double need = half - prefix.get(i);
                double w = stripWidth.get(i);
                if (w == 0.0) return stripY1.get(i); // degenerate strip
                return stripY1.get(i) + need / w;
            }
        }
        return events.get(0)[0];
    }

    private double unionWidthFromActive(Map<Long, Integer> active) {
        if (active.isEmpty()) return 0.0;
        List<int[]> intervals = new ArrayList<>(active.size());
        for (long key : active.keySet()) {
            int x1 = (int)(key >> 32);
            int x2 = (int)(key & 0xffffffffL);
            intervals.add(new int[]{x1, x2});
        }
        intervals.sort(Comparator.comparingInt(a -> a[0]));
        double width = 0.0;
     
        int curStart = intervals.get(0)[0], curEnd = intervals.get(0)[1];
        for (int i = 1; i < intervals.size(); i++) {
            int s = intervals.get(i)[0], e = intervals.get(i)[1];
            if (s <= curEnd) {
                curEnd = Math.max(curEnd, e);
            } else {
                width += curEnd - curStart;
                curStart = s;
                curEnd = e;
            }
        }
        width += curEnd - curStart;
        return width;
    }

    private long pack(int x1, int x2) {
        return ((long)x1 << 32) ^ (x2 & 0xffffffffL);
    }
}