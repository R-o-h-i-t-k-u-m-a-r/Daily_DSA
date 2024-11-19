package Recursion.Medium;

import java.util.Stack;

//GFG: https://www.geeksforgeeks.org/problems/reverse-a-stack/1

class Solution
{ 
    static void reverse(Stack<Integer> s)
    {
        // Approach: Recursion
        //T.C : O(n^2)
        
        //base case: if stack has only one element then no need to reverse
        if(s.size()==1){
            return;
        }
        
        //hypothesis: assuming method will reverse the stack after removing
        //stack top element
        int top = s.pop();
        reverse(s);
        
        //induction: after reverseing the stack we need to insert the top
        //element
        insert(s,top);
        return;
    }
    static void insert(Stack<Integer> stack, int ele){
        //base case: if stack has been empty then we need to insert the ele
        if(stack.isEmpty()==true){
            stack.push(ele);
            return;
        }
        
        //hypothesis: assuming method will insert the ele after removing
        //top of stack
        int top = stack.pop();
        insert(stack,ele);
        
        //induction: after successfully inserting the ele into stack we 
        //need to insert the top to stack back
        stack.push(top);
        return;
    }
}
