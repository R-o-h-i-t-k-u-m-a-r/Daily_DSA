package PrefixArray.Medium;

import java.util.ArrayList;

//Leetcode : 1352. Product of the Last K Numbers

class ProductOfNumbers {
    //Approach 1: Brute Force
    //T.C : O(k)
    //private ArrayList<Integer> list;

    //Approach 2: Using Prefix array
    //T.C : O(1)
    private ArrayList<Integer> prefix;
    private int n = 0;
    public ProductOfNumbers() {
        //list = new ArrayList<>();
        prefix = new ArrayList<>();
    }
    
    public void add(int num) {
        //list.add(num);
        if(num==0){
            prefix = new ArrayList<>(); //remove all the products from the prefix array
            n = 0;
        }
        else{
            if(n==0){
                prefix.add(num);//if prefix is emtpy then add  first number as product
            }
            else{
                prefix.add(prefix.get(n-1)*num);//take product with prefix num
            }
            n+=1;
        }
    }
    
    public int getProduct(int k) {
        //T.C : O(k)
        // int product = 1;
        // int n = list.size();
        // int i = n-1;
        // while(k>0){
        //     product*=list.get(i);
        //     i--;
        //     k--;
        // }
        // return product;

        //T.C : O(1)
        if(k>n){
            return 0;
        }
        if(k==n){
            return prefix.get(n-1);
        }
        return prefix.get(n-1)/prefix.get(n-k-1);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
