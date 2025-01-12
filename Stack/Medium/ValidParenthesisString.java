package Stack.Medium;

//Leetcode : 2116. Check if a Parentheses String Can Be Valid


class Solution {
    public boolean canBeValid(String s, String locked) {
        //Approach : Using Stack
        //T.C : O(n)
        //S.C : O(n)
        
        //if string has odd length then will not be balanced
        // int n = s.length();
        // if(n%2==1){
        //     return false;
        // }
        // Stack<Integer> openStack = new Stack<>();
        // Stack<Integer> openCloseStack = new Stack<>();

        // for(int i=0;i<n;i++){
        //     if(locked.charAt(i)=='0'){
        //         openCloseStack.push(i);
        //     }
        //     else if(s.charAt(i)=='('){
        //         openStack.push(i);
        //     }
        //     else if(openStack.isEmpty()==false){
        //         openStack.pop();
        //     }
        //     else if(openCloseStack.isEmpty()==false){
        //         openCloseStack.pop();
        //     }
        //     else{
        //         return false;
        //     }
        // }

        
        // while(openStack.isEmpty()==false && openCloseStack.isEmpty()==false){
        //     if(openStack.peek()>openCloseStack.peek()){
        //         return false;
        //     }
        //     openStack.pop();
        //     openCloseStack.pop();
        // }

        // return openStack.isEmpty();

        //Approach 2: Using Greedy 
        //T.C : O(n)

        int n = s.length();
        if(n%2==1){
            return false;
        }
        
        //iterate left to right to count open parenthesis
        //if any string having locked value 0 then choose for 
        //open parenthesis greedly as from left to right  as many as open
        //paranthesis we will have that will help to pair with close parenthesis to balance
        int open = 0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='(' || locked.charAt(i)=='0'){
                open++;
            }
            else{
                open--;
            }
            //if open value goes -ve it means there are some close parentheses which need 
            //open parenthesis to balance but we don't have it means string will be unbalanced
            if(open<0){
                return false;
            }
        }
        //repeat same process for close parenthesis from right to left
        int close = 0;
        for(int i=n-1;i>=0;i--){
            if(s.charAt(i)==')' || locked.charAt(i)=='0'){
                close++;
            }
            else{
                close--;
            }
            if(close<0){
                return false;
            }
        }

        return true;
    }
}