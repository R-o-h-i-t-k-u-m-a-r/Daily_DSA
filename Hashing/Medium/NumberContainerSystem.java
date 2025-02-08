package Hashing.Medium;

import java.util.*;

//Leetcode : 2349. Design a Number Container System

class NumberContainers {
    //Approach 1: Using HashMap and TreeSet
    // HashMap<Integer,Integer> map;
    // HashMap<Integer, TreeSet<Integer>> set;

    //Approach 2: using hashmap and min-heap
    HashMap<Integer,Integer> map;
    HashMap<Integer,PriorityQueue<Integer>> mapHeap;
    public NumberContainers() {
        map = new HashMap<>();
        //set = new HashMap<>();
        mapHeap = new HashMap<>();
    }
    
    public void change(int index, int number) {
        //if number is not present
        // if(map.containsKey(index)==false){
        //     //put the number into index value map
        //     map.put(index,number);
        //     //insert index of the number into set
        //     set.putIfAbsent(number,new TreeSet<>());
        //     set.get(number).add(index);
        // }
        // else{
        //     //get the number of present at the index
        //     int num = map.get(index);
        //     //remove num index from the treeset
        //     set.get(num).remove(index);
        //     //if there is num remain then remove this num from set
        //     if(set.get(num).size()==0){
        //         set.remove(num);
        //     }
        //     //now over-ride the index value into map
        //     map.put(index,number);
        //     //now put this number index into set
        //     set.putIfAbsent(number,new TreeSet<>());
        //     set.get(number).add(index);
        // }
        
        //put the number at the index
        map.put(index,number);
        //put the number index into min heap
        mapHeap.putIfAbsent(number,new PriorityQueue<>());
        mapHeap.get(number).offer(index);
    }
    
    public int find(int number) {
        // //if number is not present then return -1
        // if(set.containsKey(number)==false){
        //     return -1;
        // }
        // //extract the min-index from the set
        // return set.get(number).first();

        //if number is not present then return -1
        if(mapHeap.containsKey(number)==false){
            return -1;
        }

        //iterate over min-heap
        while(mapHeap.get(number).isEmpty()==false){
            int index = mapHeap.get(number).peek(); //get the min-index of the number
            //if we found number is at index then return
            if(map.get(index)==number){
                return index;
            }
            //if there is another number at this index , it means we were concern to remove this 
            //index from the min-heap while the time of insertion
            mapHeap.get(number).poll();
        }
        //if min-heap becomes empty then we need to remove this number 
        if(mapHeap.get(number).isEmpty()==true){
            mapHeap.remove(number);
        }
        return -1;
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */
