//Leetcode : 2070. Most Beautiful Item for Each Query

import java.util.Arrays;

class Solution {
    public int binaryFunc(int items[][], int val){
        int left = 0;
        int right = items.length - 1;
        int mid = 0;
        int maxBeauty = 0;
        while(left<=right){
            mid = left + (right-left)/2;
            if(items[mid][0]>val){
                right = mid - 1;
            }
            else{
                maxBeauty = Math.max(maxBeauty,items[mid][1]);
                left = mid+1;
            }
        }
        
        return maxBeauty;
    }
    public int[] maximumBeauty(int[][] items, int[] queries) {
        //Approach : using sorting technique T.C = O(nlogn)+O(n^2)
        //sort the items based on price
        // Arrays.sort(items,new Comparator<int[]>(){
        //     public int compare(int a[],int b[]){
        //         return a[0]-b[0];
        //     }
        // });
        //Arrays.sort(items,(a,b)->a[0]-b[0]);
         Arrays.sort(items,(a,b)->Integer.compare(a[0],b[0]));
        //items = Arrays.stream(items).sorted(Comparator.comparingInt(a->a[0])).toArray(int[][]::new);

        //update the beauty of sorted items ot maximum beauty till the ith index
        int maxBeautySeen = items[0][1];
        for(int i=1;i<items.length;i++){
            maxBeautySeen = Math.max(maxBeautySeen,items[i][1]);
            items[i][1] = maxBeautySeen;
        }
        int n = queries.length;
        int result[] = new int[n];
        for(int i=0;i<n;i++){
            int val = binaryFunc(items,queries[i]);
            result[i] = val;
        }

        return result;
    }
}