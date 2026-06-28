#given a ppositive integer n, you need to count the numbers less than or equal to n having exactly 9 divisors.


public class NineDivisors{
  public static int CountNumbers(int n){
    int count=0;
    for(int i=0;i<=n;i++){
      int div=0;
      for(int j=0;j<=i;j++){
        if(i%j==0){
          div++;
        }
      }
      if(div==9){
        count++;
      }
    }
    return count;
  }
}
