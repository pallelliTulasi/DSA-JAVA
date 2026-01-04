class Solution {
    public int sumFourDivisors(int[] nums) {
       int total = 0;
       for(int n : nums){
        int sum = 0, count = 0;
        for(int d = 1;d*d <= n;d++){
            if(n%d == 0){
                count++;
                sum += d;
                if(d != n/d){
                    count++;
                    sum += n/d;
                }
            }
            if(count>4) break;
        }
        if(count == 4) total += sum;
       } 
       return total;
    }
}