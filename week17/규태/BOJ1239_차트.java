import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1239_차트 {
	/*
	 * 72ms
	 * 원순열 (n-1)! 개수만큼 순열배열을 생성한뒤 
	 * 시작점부터 50이상이 되는 구간까지만 50이 가능한지 확인하고 50이 정확하게 만들어지는 선 개수 카운팅 
	 */
	static int n, max, percent[], ordered[];
	static boolean used[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		percent = new int[n]; ordered = new int[n]; used = new boolean[n]; 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++)
			percent[i] = Integer.parseInt(st.nextToken());
		
		ordered[0]=percent[0];
		perm(1);
		System.out.println(max);
	}
	static void perm(int cnt) {
		if(cnt==n) {
			check();
			return;
		}
		for(int i=1; i<n; i++) {
			if(used[i]) continue;
			used[i]=true;
			ordered[cnt]=percent[i];
			perm(cnt+1);
			used[i]=false;
		}
	}
	static void check() {
		int sum=0,halfidx=0;
		while(sum<50)
			sum+=ordered[halfidx++];
		int cnt=0;
		for(int i=0; i<halfidx; i++) {
			sum=0; int idx=i;
			while(sum<50 && idx<n)
				sum+=ordered[idx++];
			if(sum==50) cnt++;
		}
		if(max<cnt) max=cnt;
	}
}
