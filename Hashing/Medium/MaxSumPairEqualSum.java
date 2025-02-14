package Hashing.Medium;

//Leetcode : 2342. Max Sum of a Pair With Equal Sum of Digits

class Solution {
    public int digitSum(int num){
        int sum = 0;
        while(num!=0){
            sum+=num%10;
            num = num/10;
        }
        return sum;
    }
    public int maximumSum(int[] nums) {
        //Appraoch 1: Using Map
        // HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();

        // //collect all the number having same digit value
        // for(int num: nums){
        //     int value = digitSum(num);
        //     map.putIfAbsent(value,new ArrayList<>());
        //     map.get(value).add(num);
        // }
        // boolean found = false;
        // int result = Integer.MIN_VALUE;
        // for(List<Integer> list:map.values()){
        //     if(list.size()>1){
        //         found = true;
        //         //sort the list to get the last two max nums
        //         Collections.sort(list);
        //         int size = list.size();
        //         int sum = list.get(size-1) + list.get(size-2);
        //         result = Math.max(result,sum);
        //     }
        // }
        // if(!found) return -1;

        // return result;

        //Approach 2: Optimization over map 
        // HashMap<Integer,Integer> map = new HashMap<>();

        // //get the sum of all the elements having same digit sum
        // int result = -1;
        // for(int num: nums){
        //     int value = digitSum(num);
        //     //if this digit sum value is not present then store it into
        //     if(map.containsKey(value)==false){
        //         map.put(value,num);
        //     }
        //     else{
        //         int sum = map.get(value) + num;
        //         map.put(value,Math.max(map.get(value),num)); //store only maximum number digit sum
        //         result = Math.max(result,sum);
        //     }
        // }

        // return result;

        //Appraoch 3: Optimization over apporach 2
        //Intution: the maximum possible digit sum of nubmer is 10^9 i.e 9+9+9+9+9+9+9+9+9=81
        //So we could use fixed size of array to store the digit sum value
        int map[] = new int[82];
        int result = -1;

        for(int num: nums){
            int value = digitSum(num);
            if(map[value]==0){
                map[value] = num;
            }
            else{
                int sum = map[value] + num;
                result = Math.max(result,sum);
                map[value] = Math.max(map[value],num);
            }
        }

        return result;
    }
}