from typing import List

class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        total_area = sum(l * l for _, _, l in squares)
        half_area = total_area / 2.0

        low = min(y for _, y, _ in squares)
        high = max(y + l for _, y, l in squares)

        def area_below(h):
            area = 0.0
            for _, y, l in squares:
                if h <= y:
                    continue
                elif h >= y + l:
                    area += l * l
                else:
                    area += l * (h - y)
            return area

        for _ in range(60):
            mid = (low + high) / 2.0
            if area_below(mid) < half_area:
                low = mid
            else:
                high = mid

        return low
