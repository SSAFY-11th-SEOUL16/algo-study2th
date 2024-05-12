import java.util.*;
/*
효율성  테스트
테스트 1 〉	통과 (62.53ms, 66.9MB)
테스트 2 〉	통과 (106.88ms, 64.9MB)
테스트 3 〉	통과 (117.17ms, 64.5MB)
 */
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int n = A.length;
        int a = 0;
        int b = 0;
        int ans = 0;
        while (a < n && b < n) {
            if (A[a] < B[b]) {
                ++ans;
                ++a;
                ++b;
            } else {
                ++b;
            }
        }

        return ans;
    }
}