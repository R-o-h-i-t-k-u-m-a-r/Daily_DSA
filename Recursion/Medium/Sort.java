//GFG: https://www.geeksforgeeks.org/problems/sort-a-stack/1

import java.util.Stack;

class GfG {
    public Stack<Integer> sort(Stack<Integer> s) {
        //Approach: Using Recursion IBH
        //base case: if stack has only one element then no need to sort
        if(s.size()==1){
            return s;
        }
        //Hypothesis part:
        //assuming method will sort the stack after removing its top ele
        int ele = s.pop();
        sort(s);
        //Induction part: 
        //after sorting the stack we need to insert ele to correct position
        //into stack
        insert(s,ele);
        
        //after sorting  return stack
        return s;
    }
    
    public void insert(Stack<Integer> s, int ele){
        //base case
        if(s.size()==0 || s.peek()<=ele){
            s.push(ele);
            return;
        }
        
        //hypothesis part:
        //if ele is not greater than stack top element then after removing
        //stack top element we assume methode will insert the ele into its
        //correct position
        int num = s.pop();
        insert(s,ele);
        
        //induction part:
        //after inserting ele into its correct position we need to push 
        //num into stack as stack was already sorted and num is greatest 
        //element of stack 
        s.push(num);
    }
}
