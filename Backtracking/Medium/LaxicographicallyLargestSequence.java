package Backtracking.Medium;

import java.util.Arrays;

//Leetcode : 1718. Construct the Lexicographically Largest Valid Sequence

class Solution {
    public boolean solve(int i,int n,int result[],boolean used[]){
        if(i>=(n*2-1)){
            return true;
        }
        //if current cell is already occupied then check for next cell
        if(result[i]!=-1){
            return solve(i+1,n,result,used);
        }

        //assigned from largest number to get always lexicographically largest valid sequence
        for(int num=n;num>0;num--){
            //if current num is alredy used then skip this number
            if(used[num]==true){
                continue;
            }
            //take this number
            used[num] = true;
            result[i] = num;

            //explore this number
            if(num==1){
                if(solve(i+1,n,result,used)==true){
                    return true;
                }
            }
            else{
                int j = result[i] + i; //finding valid index to assign one more remaining num
                //if j'th index is withing bound and result has not assigned any value previously at this 
                //j'th index 
                if(j<result.length && result[j]==-1){
                    //set the number at this j'th position 
                    result[j] = num;
                    if(solve(i+1,n,result,used)==true){
                        return true;
                    }
                    //un-set the number at this j'th position
                    result[j] = -1;
                }
            }

            //un-take this number
            result[i] = -1;
            used[num] = false;
        }

        return false;
    }
    public int[] constructDistancedSequence(int n) {
        //Approach : Recusion with Backtracking
        int size = n*2 -1 ;
        int result[] = new int[size];
        Arrays.fill(result,-1);
        boolean used[] = new boolean[n+1];
        
        solve(0,n,result,used);

        return result;
    }
}
