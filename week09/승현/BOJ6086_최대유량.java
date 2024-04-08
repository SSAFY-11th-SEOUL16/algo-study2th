import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 시간 : 84ms
 * 백준 다이아 정현우님 블로그와
 * 구글링을 통해서 최대유량 알고리즘인
 * 포드-폴커슨 알고리즘 학습하면서
 * 코드 참고해서 풀이했습니다.
 */

public class BOJ6086_최대유량 {
	static int N;
	static int[][] flow;
	static int[][] capacity;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		flow = new int[53][53];
		capacity = new int[53][53];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			int from =0 , to = 0;
			if (a.charAt(0) - 'A' >= 0 && a.charAt(0) - 'A'<= 25) {
				from = a.charAt(0) - 'A' + 1;
			}
			else if (a.charAt(0) - 'a' >= 0 && a.charAt(0) - 'a'<= 25) {
				from = a.charAt(0) - 'a' + 27;
			}
			String b = st.nextToken();
			if (b.charAt(0) - 'A' >= 0 && b.charAt(0) - 'A'<= 25) {
				to = b.charAt(0) - 'A' + 1;
			}
			else if (b.charAt(0) - 'a' >= 0 && b.charAt(0) - 'a'<= 25) {
				to  = b.charAt(0) - 'a' + 27;
			}
			int c = Integer.parseInt(st.nextToken());

			capacity[from][to] += c;
			capacity[to][from] += c;
		}
		
		maxFlow(1, 26);
	}
	
	// src부터 sink까지의 최대 유량을 계산
	public static void maxFlow(int src, int sink) {
		int totalFlow = 0;
		int[] p = new int[53];
		Queue<Integer> q;
		while (true) {
			Arrays.fill(p, -1);
			q = new LinkedList<>();
			
			p[src] = src;
			q.add(src);
			
			while (!q.isEmpty() && p[sink] == -1) {
				int cur = q.poll();
				for (int next = 1; next <= 52; next++) {
					// 용량 - 유량이 남아있는 경우, 아직 연결되지 않은 간선을 탐색
					if (capacity[cur][next] - flow[cur][next] > 0 && p[next] == -1) {
						q.add(next);
						p[next] = cur;
					}
				}
			}
			// 경로가 더 이상 없는 경우 종료
			if (p[sink] == -1)
				break;
			
			// 증가 경로를 통해서 보낼 유량 결정, 끝 지점부터 처음 지점까지 거꾸로 가면서 남는 capacity가 가장 작은 구간을 더함
			int amount = Integer.MAX_VALUE;
			for (int i = sink; i != src; i = p[i]) {
				amount = Math.min(capacity[p[i]][i] - flow[p[i]][i], amount);
			}
			// 각 구간에 유량을 더해줌, 
			for (int i = sink; i != src; i = p[i]) {
				flow[p[i]][i] += amount;
				flow[i][p[i]] -= amount;
			}
			totalFlow += amount;
		}
		System.out.println(totalFlow);
	}
}
