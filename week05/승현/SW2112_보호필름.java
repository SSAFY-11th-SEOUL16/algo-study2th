import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2112_보호필름 {
	static int result;
	static int D, W, K;
	static String[][] plate;
	static boolean checked;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // rows
			W = Integer.parseInt(st.nextToken()); // cols
			K = Integer.parseInt(st.nextToken()); // 합격기준
			plate = new String[D][W];
			checked = false;
			
			for (int i = 0; i < D; i++) {
				plate[i] = br.readLine().split(" ");	// A = 0, B = 1
			}
			
			result = K;
			for (int i = 0; i < K; i++) {
					comb(0, i, 0);
					if (checked)
						break;
				}
			
			sb.append("#").append(tc).append(" ").append(result);
			if (tc < T)
				sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void comb(int depth, int d, int start) {
		if (depth == d) {
			if (check()) {
				result = d;
				checked = true;
			}
			return;
		}
		if (checked)
			return;
		for (int i = start; i < D; i++) {

			String[] temp = plate[i].clone();
			for (int j = 0; j < W; j++) {
				plate[i][j] = "1";
			}
			comb(depth+1, d, i+1);
			for (int j = 0; j < W; j++) {
				plate[i][j] = "0";
			}
			comb(depth+1, d, i+1);
			plate[i] = temp.clone();
		}
		
	}
	
	public static boolean check() {
		for (int i = 0; i < W; i++) {
			int stack = 1;
			boolean flag = false;
			String token = plate[0][i];
			for (int j = 1; j < D; j++) {
				if (token.equals(plate[j][i]))
					stack++;
				else {
					token = plate[j][i];
					stack = 1;
				}
				if (stack == K) {
					flag = true;
					break;
				}
			}
			if (!flag)
				return false;
		}
		return true;
	}
}
