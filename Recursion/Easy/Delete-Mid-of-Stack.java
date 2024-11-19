//GFG: https://www.geeksforgeeks.org/problems/delete-middle-element-of-a-stack/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

import java.util.Stack;

class Solution {
    // Function to delete middle element of a stack.
    public void deleteMid(Stack<Integer> s, int sizeOfStack) {
        // Approach : Using Recursion
        int mid = (int)Math.floor((sizeOfStack+1)/2);
        helpFunc(s,mid);
    }
    
    public void helpFunc(Stack<Integer> stack, int mid){
        //base case: if we found stack size is equal to mid then stack 
        //top element will be mid element and need to remove from stack
        if(stack.size()==mid){
            stack.pop();
            return;
        }
        
        //hypothesis: if stack size is not equal to mid then we are 
        //assuming method will remove mid element after removing 
        //stack top element
        int top = stack.pop();
        helpFunc(stack,mid);
        
        //Induction: after removing stack mid element we need to insert
        //top element which has been removed above
        stack.push(top);
        return;
    }
}
