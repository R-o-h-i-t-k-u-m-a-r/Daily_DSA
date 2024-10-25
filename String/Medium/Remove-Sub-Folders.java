//Leetcode : 1233
class Solution {
    public List<String> approach2(String[] folder){
        //Approach-2 (Using Sorting)
        //T.C : O(n*logn) //You can consider the length of each character as well - O(n*L*logn)
        //S.C : O(1)
        // Sort the folder array lexicographically
        Arrays.sort(folder);
        List<String> result = new ArrayList<>();
        
        // The first folder can never be a sub-folder after sorting
        result.add(folder[0]);

        // Iterate through the sorted folders
        for (int i = 1; i < folder.length; i++) {
            String currFolder = folder[i];
            String lastFolder = result.get(result.size() - 1);
            lastFolder += "/";  // Add '/' to the last folder to check for sub-folder

            // If the current folder does not start with the last folder, it is not a sub-folder
            if (!currFolder.startsWith(lastFolder)) {
                result.add(currFolder);
            }
        }

        return result;
    }
    public List<String> removeSubfolders(String[] folder) {
        // Approach 1: Using HashSet 
        // Create a set from the folder array
        HashSet<String> folderSet = new HashSet<>(Arrays.asList(folder));
        List<String> result = new ArrayList<>();

        for(String currFolder: folder){
            boolean isSubFolder = false;
            String tempFolder = currFolder;

            // Continue until currFolder is empty
            while(!currFolder.isEmpty()){
                int position = currFolder.lastIndexOf('/'); // Find the last occurrence of '/'
                if(position == -1) break;  // Exit if there are no more '/' characters

                currFolder = currFolder.substring(0, position);  // Get the parent folder
                // Check if the parent folder exists in the set
                if (folderSet.contains(currFolder)) {
                    isSubFolder = true;  // It is a sub-folder
                    break;
                }

            }

            // If it's not a sub-folder, add it to the result list
            if (!isSubFolder) {
                result.add(tempFolder);
            }
        }

        return result;

        

    }
}