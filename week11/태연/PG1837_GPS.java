import java.util.*;

class Solution {

    /*
     *  테스트 1 〉	통과 (47.83ms, 84MB)
     * 
     *  백트래킹 하는데 시간 어디까지 줄여야하는지 몰라서
     * 
     *  다익스트라로 남은거리 재서 한번 자르고
     * 
     *  메모이제이션으로 한번 자르고
     * 
     *  다음 GPS 좌표 우선순위로 백트래킹함 
     */
    
    static void dfs(int[] curLog, int change, int pos){
        if(change>=answer) return;
        
        if(pos==k-1){
            if(curLog[k-1]==gpsLog[k-1]){
                answer = change;
            }
            return;
        }

        int cur = curLog[pos];
        if(memo[cur][pos]<=change) return;
        else memo[cur][pos]=change;
        
        
        int next = gpsLog[pos+1];
        if(adj[next][cur] && dist[next]<= k-pos-1){
            curLog[pos+1]=next;
            dfs(curLog, change, pos+1);
        }
        
        for(int i=1; i<=n; i++){
            if(!adj[cur][i] || i==next || dist[i]> k-pos-1) continue;
            
            curLog[pos+1]=i;
            dfs(curLog, change+1, pos+1);
            
        }
    }
    
    static int answer, n, m, k;
    static boolean[][] adj;
    static int[] gpsLog, dist;
    static int[][] memo;
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        answer = k-2;
        this.n=n;
        this.m=m;
        this.k=k;
        this.gpsLog=gps_log;
        
        adj = new boolean[n+1][n+1];
        for(int i=0; i<m; i++){
            adj[edge_list[i][0]][edge_list[i][1]] = true;
            adj[edge_list[i][1]][edge_list[i][0]] = true;
        }
        
        // Node당 최소변화를 연산하기위한 memo
        memo = new int[n+1][k]; 
        for(int i=1; i<=n; i++){
            Arrays.fill(memo[i],Integer.MAX_VALUE);
        }
        
        // 목적지까지의 최단거리 연산
        dist = new int[n+1]; 
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] o1, int[] o2) -> o1[1]-o2[1]);
        dist[gpsLog[k-1]]=0;
        pq.add(new int[]{gpsLog[k-1],0});
        boolean[] v = new boolean[n+1];
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(v[cur[0]]) continue;
            
            v[cur[0]] = true;
            
            for(int i=1; i<=n; i++){
                if(!adj[cur[0]][i] || v[i] || dist[i]<=dist[cur[0]]+1) continue;
                
                dist[i] = dist[cur[0]]+1;
                pq.add(new int[]{i,dist[i]}); 
            }
        }
        if(dist[gpsLog[0]]>=k) return -1;
        
        for(int i=1; i<=n; i++) adj[i][i]=true;
        // 경로 백트래킹
        int[] curLog = new int[k]; 
        curLog[0]=gpsLog[0];
        dfs(curLog, 0, 0);
        
        return answer;
    }
}