//Leetcode : 2558. Take Gifts From the Richest Pile

import java.util.PriorityQueue;

class Solution {
    public long pickGifts(int[] gifts, int k) {
        //Approach : Priority Queue(max-heap)
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a); //max-heap
        long sum = 0;
        long mxGift = 0;
        //Heapify --> O(n)
        for(int gift: gifts){ 
            pq.add(gift);
            sum+=gift;
        }

        while(k!=0){
            mxGift+=pq.peek(); //O(1)
            int maxGift = (int)Math.floor(Math.sqrt(pq.poll())); //O(log(n))
            mxGift-=maxGift;
            pq.add(maxGift); //O(log(n))
            k--;
        }

        return sum - mxGift;
    }
}