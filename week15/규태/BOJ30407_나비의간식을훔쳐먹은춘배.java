import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ30407_나비의간식을훔쳐먹은춘배 {
	/*
	 * 80ms
	 * dp[공격 순서][춘배-나비 거리][깜짝 놀라게 하기 사용여부]로 구성
	 */
	static int n,hp,dist,catwalk,r[],dp[][][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		hp = Integer.parseInt(st.nextToken());
		dist = Integer.parseInt(st.nextToken());
		catwalk = Integer.parseInt(st.nextToken());
		r = new int[19]; dp = new int[19][101][2];
		for(int i=1; i<=n; i++)
			r[i] = Integer.parseInt(br.readLine());
		
		int ans = hp-filldp(0,dist,0);
		if(ans>0)	System.out.println(ans);
		else		System.out.println(-1);
	}
	static int filldp(int turn,int dist, int shock) {
		if(turn==n) return 0;
		if(dp[turn][dist][shock]!=0) return dp[turn][dist][shock];
		
		dp[turn][dist][shock]=987654321;
		int damage;
		// 웅크리기
		damage = r[turn+1]-dist>=0? r[turn+1]-dist:0;
		dp[turn][dist][shock]=Math.min(dp[turn][dist][shock], filldp(turn+1,dist,shock)+damage/2);
		// 네발로 걷기
		damage = r[turn+1]-dist-catwalk>=0? r[turn+1]-dist-catwalk:0;
		dp[turn][dist][shock]=Math.min(dp[turn][dist][shock], filldp(turn+1,dist+catwalk,shock)+damage);
		// 깜짝 놀라게하기
		if(shock==0 && turn<n-1) {
			damage = r[turn+1]-dist>=0? r[turn+1]-dist:0;
			dp[turn][dist][shock]=Math.min(dp[turn][dist][shock], filldp(turn+2,dist+catwalk,1)+damage);
		}
		return dp[turn][dist][shock];
	}
}