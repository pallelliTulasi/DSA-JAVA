class Solution:
    def largestSquareArea(self, bottomLeft, topRight):
        n = len(bottomLeft)
        max_area = 0

        for i in range(n):
            for j in range(i + 1, n):
                x1 = max(bottomLeft[i][0], bottomLeft[j][0])
                y1 = max(bottomLeft[i][1], bottomLeft[j][1])
                x2 = min(topRight[i][0], topRight[j][0])
                y2 = min(topRight[i][1], topRight[j][1])

                if x1 < x2 and y1 < y2:
                    width = x2 - x1
                    height = y2 - y1
                    side = min(width, height)
                    area = side * side
                    max_area = max(max_area, area)

        return max_area
