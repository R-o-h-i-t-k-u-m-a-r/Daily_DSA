package Trie.Medium;

//Leetcode : 3043. Find the Length of the Longest Common Prefix


class Solution {
    static class Trie {
        Trie number[];

        Trie(){
            number = new Trie[10];
        }
    }

    public void insert(Trie root, String str){
        Trie temp = root;
        for(char ch: str.toCharArray()){
            int digit = Character.getNumericValue(ch);
            if(temp.number[digit]==null){
                temp.number[digit] = new Trie();
            }
            //shift root node to it's child node
            temp = temp.number[digit];
        }
    }

    public int search(Trie root, String str){
        Trie temp = root;
        int len = 0;
        for(char ch: str.toCharArray()){
            int digit = Character.getNumericValue(ch);

            if(temp.number[digit]==null){
                break;
            }
            temp = temp.number[digit];
            len++;
        }

        return len;
    }
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        //Approach 1: Brute Force
        //T.C : O(m⋅log10​M + n⋅log10​N)
        //S.C : O(m⋅log10​M)
        // HashSet<Integer> set = new HashSet<>();

        // //insert all the possible prefix for each number in arr1
        // for(int num: arr1){
        //     while(set.contains(num)==false && num>0){
        //         set.add(num);
        //         num = num/10;
        //     }
        // }
        // int result = 0;
        // //check all possible prefix from arr2 elements in set
        // for(int num: arr2){
        //     while(!set.contains(num) && num>0){
        //         num = num/10;
        //     }
        //     //if any prefix found then get it digit length
        //     if(num>0){
        //         result = Math.max(result,(int)Math.log10(num)+1);
        //     }
        // }

        // return result;

        //Approach 2: Using Trie(Prefix Tree)
        //T.C : O(m⋅d+n⋅d)
        //S.C : O(m⋅d)
        //create root node of Trie
        Trie root = new Trie();

        //insert all the arr1 element prefix to Trie
        for(int num: arr1){
            String str = Integer.toString(num);
            insert(root,str);
        }

        //now iterate to arr2 and find out longest possible prefix from trie
        int result = 0;
        for(int num: arr2){
            String str = Integer.toString(num);
            int prefixLength = search(root,str);
            result = Math.max(result,prefixLength);
        }

        return result;
    }
}