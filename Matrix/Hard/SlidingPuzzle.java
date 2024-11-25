package Matrix.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

//Leetcode : 773. Sliding Puzzle

class Solution {
    public int slidingPuzzle(int[][] board) {
        //Approach : BFS and String 
        //T.C : O(6!) : The puzzle has 6 ! = 720 possible permutations of the board, as there are 6   
        //tiles, including the blank tile (0). In the worst case, BFS explores all permutations.
        //S.C : O(6!) All states are stored in map
        int row = board.length;
        int col = board[0].length;
        String start = "";
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                start+=Integer.toString(board[i][j]);
            }
        }

        String target = "123450";
        
        //Positions representing the adjaceny list
        HashMap<Integer,ArrayList<Integer>> map = new  HashMap<>();
        map.put(0,new ArrayList<>(List.of(1,3)));
        map.put(1,new ArrayList<>(List.of(0,2,4)));
        map.put(2,new ArrayList<>(List.of(1,5)));
        map.put(3,new ArrayList<>(List.of(0,4)));
        map.put(4,new ArrayList<>(List.of(1,3,5)));
        map.put(5,new ArrayList<>(List.of(2,4)));

        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        Set<String> visited = new HashSet<>();
        visited.add(start);

        int level = 0; //track the number of moves

        while(!queue.isEmpty()){
            int n = queue.size();

            //Process all the element at the current level
            for(int i=0;i<n;i++){
                String curr = queue.poll();

                //if current is equal to target then return 
                if(curr.equals(target)){
                    return level;
                }

                int indexOfZero = curr.indexOf('0'); //Find the index of '0'
                for(int swapIndex: map.get(indexOfZero)){
                    char newStateArray[] = curr.toCharArray();

                    //swap the positions
                    char temp = newStateArray[indexOfZero];
                    newStateArray[indexOfZero] = newStateArray[swapIndex];
                    newStateArray[swapIndex] = temp;

                    String newState = new String(newStateArray);

                    //if not visited then add to set
                    if(!visited.contains(newState)){
                        queue.offer(newState);
                        visited.add(newState);
                    }
                }
            }
            level++;
        }

        return -1;

    }
}