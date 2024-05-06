import java.util.*;
public class PG12987_숫자게임 {
	// 투포인터 사용
	static int n;
    public int solution(int[] A, int[] B) {
        n = A.length;
        Arrays.sort(A); Arrays.sort(B);
        
        int answer = 0;
        for(int i=0,j=0; i<n && j<n; i++){
            while(A[i]>=B[j]){
                j++; 
                if(j>=n) break;
            }
            if(j>=n) break;
            answer++; j++;
        }
        return answer;
    }
}    
/*
정확성  테스트
테스트 1 〉	통과 (0.37ms, 76.5MB)
테스트 2 〉	통과 (0.35ms, 78.1MB)
테스트 3 〉	통과 (0.33ms, 78.6MB)
테스트 4 〉	통과 (0.45ms, 77.4MB)
테스트 5 〉	통과 (0.40ms, 73.1MB)
테스트 6 〉	통과 (0.39ms, 71MB)
테스트 7 〉	통과 (0.65ms, 72.2MB)
테스트 8 〉	통과 (0.37ms, 81.7MB)
테스트 9 〉	통과 (0.91ms, 77.2MB)
테스트 10 〉	통과 (0.67ms, 70.5MB)
테스트 11 〉	통과 (1.35ms, 81.3MB)
테스트 12 〉	통과 (0.57ms, 67.4MB)
테스트 13 〉	통과 (3.62ms, 91.8MB)
테스트 14 〉	통과 (5.17ms, 76.9MB)
테스트 15 〉	통과 (3.79ms, 79.8MB)
테스트 16 〉	통과 (6.67ms, 85.9MB)
테스트 17 〉	통과 (0.47ms, 74.7MB)
테스트 18 〉	통과 (0.51ms, 77MB)
효율성  테스트
테스트 1 〉	통과 (69.98ms, 66.8MB)
테스트 2 〉	통과 (66.10ms, 77.4MB)
테스트 3 〉	통과 (55.62ms, 63.2MB)
 */