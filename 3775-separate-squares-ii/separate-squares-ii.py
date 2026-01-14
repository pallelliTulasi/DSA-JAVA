class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        events = []
        for x, y, l in squares:
            events.append((y, 1, x, x + l))
            events.append((y + l, -1, x, x + l))
        events.sort()

        active = []
        prefix = [0.0]
        ys = []
        totalArea = 0.0

        def unionWidth(intervals):
            if not intervals:
                return 0.0
            intervals.sort()
            width = 0
            cur_start, cur_end = intervals[0]
            for s, e in intervals[1:]:
                if s <= cur_end:
                    cur_end = max(cur_end, e)
                else:
                    width += cur_end - cur_start
                    cur_start, cur_end = s, e
            width += cur_end - cur_start
            return width
        prevY = events[0][0]
        for y, typ, x1, x2 in events:
            if y > prevY:
                width = unionWidth(active)
                area = width * (y - prevY)
                totalArea += area
                prefix.append(totalArea)
                ys.append((prevY, y, width))
                prevY = y
            if typ == 1:
                active.append((x1, x2))
            else:
                active.remove((x1, x2))

        half = totalArea / 2.0
        for i, (y1, y2, width) in enumerate(ys):
            if prefix[i+1] >= half:
                need = half - prefix[i]
                return y1 + need / width
        return events[0][0]
