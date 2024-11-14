//GFG: https://www.geeksforgeeks.org/problems/print-1-to-n-without-using-loops3621/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

class Solution{
    static void printTillN(int N){
        //Approach : Recursion IBH Technique
        
        //Step 3: Base case
        //We need to reduce the value of N till 1
        if(N==0){
            return;
        }
        //Step 1:  Hypothesis design
        //we wish function to reduce N 
        printTillN(N-1);
        
        //Step 2: Induction
        //after reducing value of N we can print the value
        System.out.print(N+" ");
    }
}