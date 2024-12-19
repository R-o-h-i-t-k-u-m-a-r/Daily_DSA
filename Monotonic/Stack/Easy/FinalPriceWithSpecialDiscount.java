package Monotonic.Stack.Easy;

import java.util.Stack;

//Leetcode : 1475. Final Prices With a Special Discount in a Shop


class Solution {
    public int[] finalPrices(int[] prices) {
        // Approach 1: Brute Force
        // T.C : O(n^2)
        // int n = prices.length;
        // int result[] = new int[n];

        // for(int i=0;i<n;i++){
        //     int j = i + 1;
        //     while(j<n && prices[j]>prices[i]){
        //         j++;
        //     }
        //     if(j>=n){
        //         result[i] = prices[i];
        //     }
        //     else{
        //         result[i] = prices[i] - prices[j];
        //     }
        // }

        // return result;

        // Approach 2: Monotonic Stack
        Stack<Integer> stack = new Stack<>();
        int n = prices.length;
        int i = 0;
        while(i<n){
            while(stack.isEmpty()==false && prices[i]<=prices[stack.peek()]){
                prices[stack.peek()] = prices[stack.peek()] - prices[i];
                stack.pop();
            }
            stack.push(i);
            i++;
        }

        return prices;
    }
}