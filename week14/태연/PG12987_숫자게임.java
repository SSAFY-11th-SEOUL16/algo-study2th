import java.util.Arrays;

class PG12987_숫자게임 {
    
	/*
	 *  - 효율성  테스트
	 *	테스트 1 〉	통과 (62.39ms, 67.1MB)
	 *	테스트 2 〉	통과 (56.85ms, 81.1MB)
	 *	테스트 3 〉	통과 (65.80ms, 65.1MB)
	 * 
	 *  - 정렬 후 투포인터
	 */
	
    int[] sortedA;
    int[] sortedB;
    
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        sortedA = A;
        sortedB = B;
        
        Arrays.sort(sortedA);
        Arrays.sort(sortedB);
        int aLen = sortedA.length;
        int bLen = sortedB.length;
        int a = aLen-1;
        int b = bLen-1;
        
        while(a!= -1 && b!= -1){
            if(sortedA[a]>=sortedB[b]){
                a--;
            } else {
                a--;
                b--;
                answer++;
            }
        }
        
        return answer;
    }
}