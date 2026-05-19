class Solution {
    public void moveZeroes(int[] nums) {
        int i=0,j=0;
        for(;j<nums.length;j++){
            if(nums[j]!=0){
                int  t= nums[j];
                nums[j]=nums[i];
                nums[i]=t;
                i++;
            }
        }
    }
}