//Leetcode : 3174 Clear Digits


class Solution {
    public String clearDigits(String s) {
        //Approach : Using Stack
        //T.C : O(n)
        // Stack<Character> stack = new Stack<>();

        // for(char ch: s.toCharArray()){
        //     if(Character.isDigit(ch)==false){
        //         stack.push(ch);
        //     }
        //     else{
        //         if(stack.isEmpty()==false){
        //             stack.pop();
        //         }
        //     }
        // }

        // StringBuilder result = new StringBuilder();
        // while(stack.isEmpty()==false){
        //     result.append(stack.pop());
        // }
        // return result.reverse().toString();

        //Approach : Without using stack
        //T.C : O(n)
        StringBuilder result = new StringBuilder();
        int size = 0;
        for(char ch: s.toCharArray()){
            if(Character.isDigit(ch)==true){
                if(result.length()>0){
                    result.deleteCharAt(size-1);
                    size-=1;
                }
            }
            else{
                result.append(ch);
                size+=1;
            }
        }

        return result.toString();

    }
}