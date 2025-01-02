package PrefixArray.Medium;

//Leetcode : 2559. Count Vowel Strings in Ranges


class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
       //Approach 1: Brute Force
    //    int n = words.length;
    //    int m = queries.length;
    //    int result[] = new int[m];
    //    Set<Character>  set = new HashSet<>();
    //    char vowel[] = {'a','e','i','o','u'};
    //    for(char ch: vowel){
    //     set.add(ch);
    //    }

    //    for(int i=0;i<m;i++){
    //     int count = 0;
    //     for(int j=queries[i][0];j<=queries[i][1];j++){
    //         String str = words[j];
    //         if(set.contains(str.charAt(0)) && set.contains(str.charAt(str.length()-1))){
    //             count++;
    //         }
    //     }

    //     result[i] = count;
    //    } 

    //    return result;

    //Approach 2: Prefix sum
    //T.C: O(n+m)
    int n = words.length;
    int m = queries.length;
    int result[] = new int[m];
    //char vowel[] = {'a','e','i','o','u'};
    //Set<Character> set = new HashSet<>();
    //populate the vowels in set
    // for(char ch: vowel){
    //     set.add(ch);
    // }
    int prefix[] = new int[n];
    int vowels = 0;
    for(int i=0;i<n;i++){
        String str = words[i];
        if(isVowel(str.charAt(0)) && isVowel(str.charAt(str.length()-1))){
         vowels++;   
        }
        prefix[i] = vowels;
    }

    for(int i=0;i<m;i++){
        int l = queries[i][0];
        int r = queries[i][1];
        result[i] = prefix[r] - (l>0?prefix[l-1]:0);
    }

    return result;
    }

    private boolean isVowel(char ch){
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u'){
            return true;
        }
        return false;
    }
}