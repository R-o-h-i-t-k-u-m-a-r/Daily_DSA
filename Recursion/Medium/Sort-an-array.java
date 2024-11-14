//Leetcode : 912. Sort an Array

import java.util.ArrayList;

class Solution {
    public void sort(ArrayList<Integer> list){
        //base case: list have only one element then no need to sort 
        if(list.size()==1){
            return;
        }
        //hypothesis
        //reduce list from by 1 and store the last element
        int num = list.remove(list.size()-1);
        //assuming this function call will sort the list elements
        sort(list);

        //Induction 
        //assuming after sorting the list this function call insert the num at the correct position
        insert(list,num);
    }

    public void insert(ArrayList<Integer> list, int num){
        //base case: if list is empty or last element of list is less then num then we can simply
        //push the num into list as argument list is sorted 
        if(list.size()==0 || list.get(list.size()-1)<=num){
            list.add(num);
            return;
        }

        //hypothesis
        //if array is not empty and list last element not less then num 
        //it means num position will be in between first and last element
        //reduce list from by 1 and store the last element
        int ele = list.remove(list.size()-1);
        //assuming this function call will insert the num into list at the correct position
        insert(list,num);

        //Induction
        //after inserting num at the correct position we need to add ele at the last position of list
        //as argument list is alread sort 
        list.add(ele);
    }
    public int[] sortArray(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int num: nums){
            list.add(num);
        }
        sort(list);
        int n = nums.length;
        for(int i=0;i<n;i++){
            nums[i] = list.get(i);
        }
        return nums;
    }
}