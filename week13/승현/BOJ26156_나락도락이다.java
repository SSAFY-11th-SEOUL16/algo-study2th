import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ26156_나락도락이다 {

	static int n;
	static String s;
	static long[] pot;
	static long result;
	static int divNum = 1000000007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		s = br.readLine();
		pot = new long[s.length()];
		initPOT();
		solution();
//		System.out.println(Arrays.toString(dp));
		System.out.println(result);
	}
	
	public static void solution() {
		long[] cnt = new long[4];
		
		for (int i = s.length()-1; i >= 0; i--) {
			if (s.charAt(i) == 'K') {
				cnt[3]++;
			}
			else if (s.charAt(i) == 'C') {
				cnt[2] += cnt[3];
				cnt[2] = cnt[2] % divNum;
			}
			else if (s.charAt(i) == 'O') {
				cnt[1] += cnt[2];
				cnt[1] = cnt[1] % divNum;
			}
			else if (s.charAt(i) == 'R') {
				result += cnt[1] * pot[i];
				result = result % divNum;
			}
		}
	}
	
	public static void initPOT() {
		int t = 1;
		for (int i = 0; i < s.length(); i++) {
			pot[i] = t;
			t = t * 2;
			t = t % divNum;
		}
	}
}
