import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2560_짚신벌레 {
	/*
	 * 108ms
	 * 태어나는 마리수를 저장하는 bug와 태어난 마리수의 누적합을 저장하는 sum 배열로 dp를 구성해
	 * 현재 태어날 마리 수  = (현재-a)~(현재-b)에 태어난 개체 = 번식가능한 개체 수의 합을 저장하며 dp채우기
	 */
	static final int DIV=1000;
	static int a,b,d,n,bug[],sum[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken()); // 성체
		b = Integer.parseInt(st.nextToken()); // 번식x
		d = Integer.parseInt(st.nextToken()); // 죽음
		n = Integer.parseInt(st.nextToken());
		
		bug = new int[n+d]; sum = new int[n+d];
		bug[d-1]=1;			sum[d-1]=1;
		
		for(int time=d; time<n+d; time++) {
			bug[time]=(sum[time-a]-sum[time-b]+1000)%1000;
			sum[time]=(sum[time-1]+bug[time])%1000;
			bug[time]%=1000;
		}
		System.out.println((sum[n+d-1]-sum[n-1]+1000)%1000);
	}
}
