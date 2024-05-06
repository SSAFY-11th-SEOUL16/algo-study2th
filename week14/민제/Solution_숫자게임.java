import java.util.Arrays;

public class Solution_숫자게임 {

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int answer = 0;

        int n = A.length;
        int selectB = 0;


        /*
        풀이
        a,b모두 정렬후 b가 더 큰 최소까지 이동후 answer++
         */
        for(int selectA=0; selectA<n; selectA++) {
            while (selectB < n && A[selectA] >= B[selectB]) {
                selectB++;
            }
            if(selectB >= n) break;
            answer++;
            selectB++;
        }

        return answer;
    }
}
