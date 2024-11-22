//GFG : https://www.geeksforgeeks.org/problems/subsets-1587115621/1

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class solve
{
    //Function to find all possible unique subsets.
    public static ArrayList <ArrayList <Integer>> AllSubsets(int arr[], int n)
    {
        // Appraoch: Recursion Pick & No-Pick mechanism
        HashSet<ArrayList<Integer>> set = new HashSet<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        
        solve(arr,n-1,list1,set);
        
        set.forEach(list->result.add(list));
        
        //sort the result in lexicographical order
        Collections.sort(result,(l1,l2)->{
            int minSize = Math.min(l1.size(),l2.size());
            for(int i=0;i<minSize;i++){
                int cmp = Integer.compare(l1.get(i),l2.get(i));
                if(cmp!=0){
                    return cmp; //Return non-zero comparizon result
                }
            }
            // If all elements are equal, compare by size (shorter list comes first)
            return Integer.compare(l1.size(),l2.size());
        });
        
        // result = result.stream().sorted((l1,l2)->{
        //     int minSize = Math.min(l1.size(),l2.size());
        //     for(int i=0;i<minSize;i++){
        //         int cmp = Integer.compare(l1.get(i),l2.get(i));
        //         if(cmp!=0) return cmp;
        //     }
        //     return Integer.compare(l1.size(),l2.size());
        // })
        // .collect(Collectors.toList());
        
        return result;
        
    }
    public static void solve(int arr[],int n ,ArrayList<Integer> list, HashSet<ArrayList<Integer>> set){
        if(n<0){
            
            Collections.sort(list);
            set.add(list);
            return;
        }
        
        ArrayList<Integer> temp = new ArrayList<>(list);
        //pick 
        temp.add(arr[n]);
        solve(arr,n-1,temp,set);
        //no pick
        solve(arr,n-1,list,set);
        
        return;
    }
}
