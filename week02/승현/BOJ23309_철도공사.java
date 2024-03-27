import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23309_철도공사 {

	static class Station {
		int prev;
		int next;
	}

	public static void main(String[] args) throws Exception {
		int a, b;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 역 개수
		int M = Integer.parseInt(st.nextToken()); // 공사 횟수
		Station[] stations = new Station[1000001];
		st = new StringTokenizer(br.readLine());
		int ip = Integer.parseInt(st.nextToken());
		int fp = ip;
		stations[ip] = new Station();
		int is = 0;
		for (int i = 1; i < N; i++) {
			is = Integer.parseInt(st.nextToken());
			stations[is] = new Station();
			stations[ip].next = is;
			stations[is].prev = ip;
			ip = is;
		}
		if (N > 1) {
			stations[fp].prev = is;
			stations[is].next = fp;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String info = st.nextToken();
			if (info.equals("BN")) {
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				sb.append(stations[a].next);
				sb.append('\n');
				if (stations[b] == null)
					stations[b] = new Station();
				stations[b].next = stations[a].next;
				stations[stations[a].next].prev = b;
				stations[a].next = b;
				stations[b].prev = a;
				
			} else if (info.equals("BP")) {
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				sb.append(stations[a].prev);
				sb.append('\n');
				if (stations[b] == null)
					stations[b] = new Station();
				stations[b].prev = stations[a].prev;
				stations[stations[a].prev].next = b;
				stations[a].prev = b;
				stations[b].next = a;
				
			} else if (info.equals("CN")) {
				a = Integer.parseInt(st.nextToken());
				sb.append(stations[a].next);
				sb.append('\n');
				stations[stations[stations[a].next].next].prev = a;
				stations[a].next = stations[stations[a].next].next;
				
			} else if (info.equals("CP")) {
				a = Integer.parseInt(st.nextToken());
				sb.append(stations[a].prev);
				sb.append('\n');
				stations[stations[stations[a].prev].prev].next = a;
				stations[a].prev = stations[stations[a].prev].prev;
			}
		}
		System.out.println(sb.toString());
	}
}