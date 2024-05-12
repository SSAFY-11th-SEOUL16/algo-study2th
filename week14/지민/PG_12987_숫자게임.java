import java.util.*;

/**
효율성  테스트
테스트 1 〉	통과 (144.65ms, 71.5MB)
테스트 2 〉	통과 (130.83ms, 69.7MB)
테스트 3 〉	통과 (114.98ms, 71MB)
*/

class PG_12987_숫자게임 {
    public PriorityQueue<Integer> pA;
    public PriorityQueue<Integer> pB;
    
    public int solve() {
        int cnt = 0;
        while(!pA.isEmpty() && !pB.isEmpty()) {
            if(pA.peek() < pB.peek()) {
                pA.poll();
                pB.poll();
                cnt += 1;
            }
            else pB.poll();
        }
        
        return cnt;
    }
    
    public int solution(int[] A, int[] B) {
        pA = new PriorityQueue<>();
        pB = new PriorityQueue<>();
        
        for(int i = 0; i < A.length; i++) {
            pA.add(A[i]);
            pB.add(B[i]);
        }
        
        return solve();
    }
}
