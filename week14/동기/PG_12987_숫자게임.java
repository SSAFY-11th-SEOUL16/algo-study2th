/*
효율성  테스트
테스트 1 〉	통과 (117.21ms, 65.6MB)
테스트 2 〉	통과 (67.17ms, 80.3MB)
테스트 3 〉	통과 (57.05ms, 62.7MB)
*/

import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int n = A.length;
        Arrays.sort(A);
        Arrays.sort(B);
        int aPoint = n-1;
        int bPoint =n-1;
        
        for(int i=0; i<n; i++){
            if(A[aPoint] < B[bPoint]){
                bPoint--;
                answer++;
            }
            aPoint--;
        }
        return answer;
    }
}