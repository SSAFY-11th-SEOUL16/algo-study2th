import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간: 127ms
 */
public class Solution_4008_숫자_만들기 {
	static int N;
	static int[] nums;
	static int[] opCnt; // +, -, *, /
	static int resMin;
	static int resMax;
	
	private static int calc(int op, int curr, int next) {
		if(op == 0) return curr + next;
		else if(op == 1) return curr - next;
		else if(op == 2) return curr * next;
		return curr / next;
	}
	
	private static void solve(int index, int currSum) {
		if(index == N) {
			if(resMin > currSum) resMin = currSum;
			if(resMax < currSum) resMax = currSum;
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(opCnt[i] == 0) continue;
			opCnt[i] -= 1;
			solve(index + 1, calc(i, currSum, nums[index]));
			opCnt[i] += 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			nums = new int[N];
			opCnt = new int[4];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				opCnt[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			resMin = Integer.MAX_VALUE;
			resMax = Integer.MIN_VALUE;
			solve(1, nums[0]);
			sb.append("#").append(t).append(" ").append((resMax - resMin)).append("\n");
		}
		System.out.println(sb);

	}
}
