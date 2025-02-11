package Stack.Medium;

//Leetcode : 1910. Remove All Occurrences of a Substring

class Solution {
    public String removeOccurrences(String s, String part) {
        //Approach :(Simulation)
        StringBuilder result = new StringBuilder();
        int n = part.length();
        for(char ch: s.toCharArray()){
            result.append(ch);
            if(result.length()>=n){
                String str = result.toString();
                while(str.length()>=n && str.endsWith(part)){
                    str = str.substring(0,str.length()-n);
                }
                result = new StringBuilder(str);
            }
        }

        return result.toString();
    }
}