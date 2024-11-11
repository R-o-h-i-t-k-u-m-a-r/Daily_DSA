package Array.Medium;
//Leetcode : 2601 Prime-Subtraction-Operation
import java.util.Arrays;

class Solution {
    public void sievePrime(boolean isPrime[]){
        isPrime[0] = false; // 0 is not a prime number
        isPrime[1] = false; // 1 is not a prime number

        for(int i=2;i*i<1000;i++){
            if(isPrime[i]==true){
                for(int j=i*i;j<1000;j+=i){
                    isPrime[j] = false;
                }
            }
        }
    }
    public boolean primeSubOperation(int[] nums) {
        int n = nums.length;
        boolean isPrime[] = new boolean[1000];
        //isPrime[i] == true means, i is a prime number else i is no a prime number
        Arrays.fill(isPrime,true);
        sievePrime(isPrime); //it will populate my isPrime array //O(1)


        //start traversing nums array from right to left
        //O(n * maxNum)
        for(int i=n-2;i>=0;i--){
            //if current element is strictly less then next element then no need to do further anything
            if(nums[i]<nums[i+1]){
                continue;
            }
            //otherwise we need to check whether current elment can be updated to a prime number or not
            //iterate to all the possible prime numbers of nums[i]
            for(int prime = 2;prime<nums[i];prime++){
                if(!isPrime[prime]){
                    continue;
                }
                //after subtracting prime to nums[i] resultant should be strictly less then its next 
                //element
                if((nums[i]-prime)<nums[i+1]){
                    nums[i] = nums[i] - prime;
                    break;
                }
            }
            //after iterating to all the possible prime numbers if resultant is not strictly less than 
            //its next next element then it would not be possible to make strictly increasing array
            if(nums[i]<nums[i+1]==false){
                return false;
            }
        }

        return true;
    }
}