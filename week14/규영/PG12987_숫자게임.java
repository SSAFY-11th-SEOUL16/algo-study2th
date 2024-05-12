import java.util.*;

class PG12987_숫자게임 {
    public int solution(int[] A, int[] B) {
        // A보다 큰 수를 몇 개나 갖고 있는지 세어준다
        int ans = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        for (int i = 0; i < A.length; i++)
            if (A[ans] < B[i]) ans++;
        return ans;
    }
}

/*
    테스트 1 〉	통과 (57.96ms, 65.2MB)
    테스트 2 〉	통과 (53.51ms, 62.8MB)
    테스트 3 〉	통과 (62.66ms, 66.7MB)
*/