class Solution:
    def maxSideLength(self, mat, threshold):
        m, n = len(mat), len(mat[0])
        prefix = [[0] * (n + 1) for _ in range(m + 1)]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                prefix[i][j] = (
                    mat[i - 1][j - 1]
                    + prefix[i - 1][j]
                    + prefix[i][j - 1]
                    - prefix[i - 1][j - 1]
                )

        left, right, res = 0, min(m, n), 0
        while left <= right:
            mid = (left + right) // 2
            if self.check(prefix, mid, threshold):
                res = mid
                left = mid + 1
            else:
                right = mid - 1

        return res

    def check(self, prefix, length, threshold):
        for i in range(length, len(prefix)):
            for j in range(length, len(prefix[0])):
                total = (
                    prefix[i][j]
                    - prefix[i - length][j]
                    - prefix[i][j - length]
                    + prefix[i - length][j - length]
                )
                if total <= threshold:
                    return True
        return False
