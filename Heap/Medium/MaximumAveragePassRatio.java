//Leetcode : 1792. Maximum Average Pass Ratio
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // Approach 1: Brute Force
        // int n = classes.length;
        // double avgMarks[] = new double[n];
        // for(int i=0;i<n;i++){
        //     avgMarks[i] = (double) classes[i][0]/classes[i][1];
        // }
        // while(extraStudents!=0){
           
        //     double tempAvg[] = new double[n];
        //     for(int i=0;i<n;i++){
        //         tempAvg[i] = (double) (classes[i][0]+1)/(classes[i][1]+1);
        //     }
            
        //     double maxDiff= 0;
        //     int index = 0;

        //     for(int i=0;i<n;i++){
        //         double delta = tempAvg[i] - avgMarks[i];
        //         if(maxDiff<delta){
        //             maxDiff= delta;
        //             index = i;
        //         }
        //     }
        //     //update the maximum difference ratio
        //     avgMarks[index] = tempAvg[index];
        //     extraStudents--;
        //     classes[index][0]++;
        //     classes[index][1]++;
        // }

        // double result = 0.0;
        // for(int i=0;i<n;i++){
        //     result+=avgMarks[i];
        // }

        // return result/n;

        //Approach 2: using max-heap
        int n = classes.length;
        PriorityQueue<double[]> pq = new PriorityQueue<>((a,b)->Double.compare(b[0],a[0])); //max-heap

        for(int i=0;i<n;i++){
            double curr_pr = (double) classes[i][0]/classes[i][1];
            double new_pr = (double) (classes[i][0]+1)/(classes[i][1]+1);
            double delta = new_pr-curr_pr;
            pq.offer(new double[]{delta,i});
        }

        while(extraStudents-- >0){ //O(log(k))
            double arr[] = pq.poll(); //O(log(n))
            int index = (int) arr[1];

            //update the classes index with maxium delta
            classes[index][0]++;
            classes[index][1]++;

            double curr_pr = (double) classes[index][0]/classes[index][1];
            double new_pr = (double) (classes[index][0]+1)/(classes[index][1]+1);
            double delta = new_pr - curr_pr;
            pq.offer(new double[]{delta,index}); //O(log(n))
        }

        double result = 0.0;
        for(int i=0;i<n;i++){
            result+=(double) classes[i][0]/classes[i][1];
        }

        return result / n;
    }
}