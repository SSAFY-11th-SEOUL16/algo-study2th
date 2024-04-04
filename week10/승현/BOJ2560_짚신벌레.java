import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2560_짚신벌레 {			// 112ms
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		// 응애 + 은퇴
		int[] bug = new int[N+d+1];
		// 현역
		int[] can = new int[N+d+1];
		bug[0] = 1;
		can[a] = 1;
		can[b] = -1;
		bug[d] = -1;
		
		// 0~a-1 => 응애, a~b-1 => 현역, b~d-1 =>은퇴, d => 사망
		for (int i = 1; i <= N; i++) {			
			can[i] %= 1000;
			can[i] += can[i-1];
			bug[i] += can[i];
			bug[i] %= 1000;
			bug[i] += bug[i-1];
			can[i+a] += can[i];
			can[i+b] -= can[i];
			bug[i+d] -= can[i];
		}

		System.out.println(bug[N] % 1000);
	}
}
