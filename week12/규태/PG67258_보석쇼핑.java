import java.util.*;
class PG67258_보석쇼핑 {
	/*
	 * 회전 초밥 문제를 연상시키며 해결
	 * 1.이분탐색으로 가능한 최소 길이 구하기
	 * 2.기존보다 짧은길이로 가능하다면 처음으로 가능한 구간을 새로 업데이트
	 */
    static int n,minstart=-1,minlen=100000;
    static int[] gemidx,count;
    static Map<String,Integer> jewel = new HashMap<>();
    public int[] solution(String[] gems) {
        n=gems.length;
        gemidx = new int[n]; // gems 를 보석 index로 변환한 int배열
        
        //맵에 보석별 고유 번호를 저장 후 gemidx 배열에 해당 보석번호 저장
        int idx=0;
        for(int i=0; i<n; i++){
            if(!jewel.containsKey(gems[i])){
                gemidx[i]=idx;
                jewel.put(gems[i],idx++);
            }
            else gemidx[i]=jewel.get(gems[i]);
        }
        
        //idx=보석의 종류 수 ~ 전체 n개를 시작으로 가능한 길이 이분탐색
        int start=idx,end=n;
        while(start<=end){
            int mid = (start+end)/2;
            count = new int[idx]; //새로운 길이 확인시 보석idx count하는 배열 리셋
            if(sizecheck(mid)) end=mid-1;
            else start=mid+1;
        }
        
        int[] answer = {minstart+1,minstart+minlen};
        return answer;
    }
    static boolean sizecheck(int len){ //회전초밥문제 느낌으로 나온 idx count 
        for(int i=0; i<len; i++)
            count[gemidx[i]]++;
        if(valcheck(0,len)) return true; // 매 순간마다 가능한지 check, 가능하다면 종료
        for(int i=len; i<n; i++){
            count[gemidx[i]]++;
            count[gemidx[i-len]]--;
            if(valcheck(i-len+1,len)) return true;
        }
        return false;
    }
    static boolean valcheck(int start, int len){ 
        for(int i=0; i<count.length; i++)
            if(count[i]==0) return false;
        minstart=start; minlen=len; // 모든 idx가 한번이라도 count 되었다면 시작점, 길이 업뎃
        return true;
    }
}
/*
 * 테스트 1 〉	통과 (17.90ms, 54MB)
테스트 2 〉	통과 (24.50ms, 56.2MB)
테스트 3 〉	통과 (16.18ms, 58.2MB)
테스트 4 〉	통과 (39.34ms, 60.3MB)
테스트 5 〉	통과 (33.74ms, 61.8MB)
테스트 6 〉	통과 (26.91ms, 64.6MB)
테스트 7 〉	통과 (54.92ms, 67.8MB)
테스트 8 〉	통과 (58.01ms, 68.7MB)
테스트 9 〉	통과 (27.99ms, 68.2MB)
테스트 10 〉	통과 (59.82ms, 75.6MB)
테스트 11 〉	통과 (140.29ms, 79.6MB)
테스트 12 〉	통과 (85.51ms, 80MB)
테스트 13 〉	통과 (187.33ms, 80.2MB)
테스트 14 〉	통과 (90.00ms, 79.5MB)
테스트 15 〉	통과 (115.07ms, 80.3MB)
 */