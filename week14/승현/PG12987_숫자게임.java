import java.util.*;
/*
    효율성 테스트
    테스트 1 〉	통과 (112.58ms, 64.6MB)
    테스트 2 〉	통과 (56.93ms, 66MB)
    테스트 3 〉	통과 (119.58ms, 67.7MB)
 */
public class PG12987_숫자게임 {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        int len = A.length;
        int aIdx = 0;
        int bIdx = 0;

        while(aIdx < len && bIdx < len){
            // B가 이긴 경우
            if (A[aIdx] < B[bIdx]) {
                answer++;
                aIdx++;
                bIdx++;
            }
            else {
                bIdx++;
            }
        }

        return answer;
    }
}