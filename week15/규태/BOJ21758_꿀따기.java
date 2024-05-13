import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21758_꿀따기 {
	/*
	 * 236ms
	 * 누적합 활용
	 * 꿀벌 0 i, 벌통 n-1일때 
	 * 벌통 0, 꿀벌 i n-1일때
	 * 꿀벌 0, 벌통 i, 꿀벌 n-1 일때
	 * 세가지 경우로 그리디하게 고려 
	 */
	static int n,honey[],forward[];
	static long ans=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		honey = new int[n]; forward = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			honey[i] = Integer.parseInt(st.nextToken());
		
		forward[0]=honey[0];
		for(int i=1; i<n; i++) {
			forward[i] = forward[i-1]+honey[i];
		}
		for(int i=1; i<n-1; i++) {
			ans=Math.max(ans, forward[i]-forward[0]+forward[n-2]-forward[i-1]);
			ans=Math.max(ans, forward[n-1]-forward[0]-honey[i]+forward[n-1]-forward[i]);
			ans=Math.max(ans, forward[i-1]+forward[n-2]-honey[i]);
		}			
		System.out.println(ans);
	}
}