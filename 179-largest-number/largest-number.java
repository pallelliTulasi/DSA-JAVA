class Solution {
    public String largestNumber(int[] nums) {
        String[] numString = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            numString[i] = Integer.toString(nums[i]);

        }
        Arrays.sort(numString, (a, b) -> (b+a).compareTo(a+b));
        if(numString[0].equals("0")){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for(String numStr : numString){
            sb.append(numStr);
        }
        return sb.toString();
    }
}