import java.util.*;
public class PG1837_GPS {
	/*
	 * 정확성  테스트
	테스트 1 〉	통과 (34.95ms, 97.6MB)
	 * 시간이 time일때 현재 pos에 있을때 수정해야하는 최소 횟수를 dp[time][pos]로 저장
	 * 2차원 dp 메모이제이션으로 작성
	 */
    static int INF=987654321;
    static int[][] dp;
    static ArrayList<Integer>[] list;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        list=new ArrayList[n+1];
        dp=new int[k][n+1];
        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
            list[i].add(i);
        }
        for(int i=0; i<m; i++){
            list[edge_list[i][0]].add(edge_list[i][1]);
            list[edge_list[i][1]].add(edge_list[i][0]);
        }
        
        Arrays.fill(dp[0],INF); //초기 위치 변할 수 없으므로 세팅
        for(int i=1; i<k; i++)
            Arrays.fill(dp[i],-1); //중간 위치는 모두 -1로 초기화
        dp[0][gps_log[0]]=0;
        int answer = filldp(k-1,gps_log[k-1],gps_log);
        return answer!=INF? answer:-1;
    }
    static int filldp(int time, int pos,int[] log){
        if(dp[time][pos]!=-1) return dp[time][pos];
        dp[time][pos]=INF;
        for(int before:list[pos]){
            int tmp = filldp(time-1,before,log);
            if(log[time]!=pos) tmp++;
            dp[time][pos]=Math.min(dp[time][pos],tmp);
        }
        return dp[time][pos];
    }
}