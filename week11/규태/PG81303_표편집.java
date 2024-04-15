import java.util.*;
public class PG81303_표편집 {
	/*
	 * 2주차 연습문제였던 철도공사와 유사한 방법으로 해결
	 * 시간을 어떻게 적는게 좋을지 몰라서 시간은 최하단에 적겠습니다.
	 */
    static Stack<Integer> stack = new Stack<Integer>();
    static int len;
    static int[] next,prev; //이전 이후 노드 번호 저장
    static boolean[] deleted; //삭제여부 체크 활용 용도
    public String solution(int n, int k, String[] cmd) {
        StringTokenizer st;
        len=n;
        prev = new int[n+2];
        next = new int[n+2];
        deleted = new boolean[n+2];
        for(int i=1; i<=n+1; i++){
            prev[i]=i-1; next[i]=i+1;
            if(i==n+1) next[i]=-1;
        }
        
        int cur=k+1;
        for(int i=0; i<cmd.length; i++){
            st = new StringTokenizer(cmd[i]);
            String action = st.nextToken();
            if(action.equals("U")){
                int move = Integer.parseInt(st.nextToken());
                for(int m=1; m<=move; m++)
                    cur=prev[cur];
            } 
            else if(action.equals("D")){
                int move = Integer.parseInt(st.nextToken());
                for(int m=1; m<=move; m++)
                    cur=next[cur];
            } 
            else if(action.equals("C")){
                stack.push(cur);
                deleted[cur]=true;
                cur = remove(cur);
            } 
            else {
                int top = stack.pop();
                deleted[top]=false;
                revive(top);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++)
            if(deleted[i]) sb.append('X');
            else sb.append('O');
        return sb.toString();
    }
    static int remove(int idx){
        int cur=idx;
        next[prev[cur]]=next[cur];
        prev[next[cur]]=prev[cur];
        
        if(next[next[cur]]==-1) return prev[cur];
        else return next[cur];      
    }
    static void revive(int idx){
        next[prev[idx]]=idx;
        prev[next[idx]]=idx;
    }
}
/*
 * 정확성  테스트
테스트 1 〉	통과 (0.15ms, 78.4MB)
테스트 2 〉	통과 (0.13ms, 65.3MB)
테스트 3 〉	통과 (0.12ms, 76.5MB)
테스트 4 〉	통과 (0.11ms, 76.1MB)
테스트 5 〉	통과 (0.25ms, 72.7MB)
테스트 6 〉	통과 (0.25ms, 69.8MB)
테스트 7 〉	통과 (0.29ms, 73.2MB)
테스트 8 〉	통과 (0.42ms, 75.3MB)
테스트 9 〉	통과 (0.24ms, 74.5MB)
테스트 10 〉	통과 (0.29ms, 74.5MB)
테스트 11 〉	통과 (1.57ms, 77.7MB)
테스트 12 〉	통과 (1.33ms, 78.7MB)
테스트 13 〉	통과 (1.18ms, 75.2MB)
테스트 14 〉	통과 (2.29ms, 71.5MB)
테스트 15 〉	통과 (1.44ms, 78.4MB)
테스트 16 〉	통과 (1.55ms, 74.9MB)
테스트 17 〉	통과 (2.91ms, 76.7MB)
테스트 18 〉	통과 (2.93ms, 81.8MB)
테스트 19 〉	통과 (2.71ms, 76.8MB)
테스트 20 〉	통과 (2.42ms, 79MB)
테스트 21 〉	통과 (2.39ms, 78.2MB)
테스트 22 〉	통과 (3.30ms, 84.7MB)
테스트 23 〉	통과 (0.15ms, 72.5MB)
테스트 24 〉	통과 (0.11ms, 77.4MB)
테스트 25 〉	통과 (0.14ms, 76.9MB)
테스트 26 〉	통과 (0.15ms, 79.1MB)
테스트 27 〉	통과 (0.14ms, 75.8MB)
테스트 28 〉	통과 (0.17ms, 76MB)
테스트 29 〉	통과 (0.18ms, 75.1MB)
테스트 30 〉	통과 (0.19ms, 74MB)
효율성  테스트
테스트 1 〉	통과 (132.37ms, 110MB)
테스트 2 〉	통과 (139.31ms, 112MB)
테스트 3 〉	통과 (136.33ms, 113MB)
테스트 4 〉	통과 (139.92ms, 114MB)
테스트 5 〉	통과 (138.71ms, 114MB)
테스트 6 〉	통과 (228.83ms, 112MB)
테스트 7 〉	통과 (121.61ms, 106MB)
테스트 8 〉	통과 (150.38ms, 95.8MB)
테스트 9 〉	통과 (171.38ms, 114MB)
테스트 10 〉통과 (185.20ms, 131MB)
 */