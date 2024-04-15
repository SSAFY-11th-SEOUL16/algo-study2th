import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2610_회의준비 {			// 140ms
	static int n, m, k;
	static ArrayList<Integer>[] graph;
	static ArrayList<Integer> result;
	static int[][] dist;
	static boolean[] visited;
	static Queue<Integer> q;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine()); 
        m = Integer.parseInt(br.readLine()); 
        graph = new ArrayList[n+1];
        dist = new int[n+1][n+1];
        result = new ArrayList<>();
        visited = new boolean[n+1];
        
        for (int i = 1; i <= n; i++) {
        	graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	graph[a].add(b);
        	graph[b].add(a);
        }
        getDist();
        k = 0;
        getResult();
        System.out.println(k);
        Collections.sort(result);
        for (int r : result) {
        	System.out.println(r);
        }
    }
    public static void getDist() {
    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= n; j++) {
    			if (i == j) {
    				dist[i][j] = 0;
    			}
    			else if (graph[i].contains(j)) {
    				dist[i][j] = 1;
    			}
    			else {
    				dist[i][j] = 101;
    			}
    		}
    	}
    	
    	for (int k = 1; k <= n; k++) {
    		for (int i = 1; i <= n; i++) {
    			for (int j = 1; j <= n; j++) {
    				dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
    			}
    		}
    	}
    }
    
    public static void getResult() {
    	while (!allVisited()) {
    		if (q.size() == 1) {
    			result.add(q.poll());
    		}
    		else {
    			int head = q.peek();
    			int hd = 101;
    			while (!q.isEmpty()) {
    				int now = q.poll();
    				int max = 0;
    				for (int i = 1; i <= n; i++) {
    					if (now != i && dist[now][i] < 101) {
    						if (dist[now][i] > max) {
    							max = dist[now][i];
    						}
    					}
    				}
    				if (max < hd) {
    					head = now;
    					hd = max;
    				}
    			}
    			result.add(head);
    		}
    	}
    }
    
    public static boolean allVisited() {
    	for (int i = 1; i <= n; i++) {
    		if (!visited[i]) {
    			q = new LinkedList<>();
    			k++;
    			q.offer(i);
    			visited[i] = true;
    			for (int j = 1; j <= n; j++) {
    				if (i != j && dist[i][j] <= 100) {
    					q.offer(j);
    					visited[j] = true;
    				}
    			}
    			return false;
    		}
    	}
    	return true;
    }
}