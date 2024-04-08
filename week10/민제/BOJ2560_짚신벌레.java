import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2560_짚신벌레 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int adultTerm = b - a;
		int oldTerm = d - b;
		
		int[] babyBug = new int[n+1];
		int[] adultBug = new int[n+1];
		int[] oldBug = new int[n+1];
		int[] nowBug = new int[n+1];
		int[] nowadult = new int[n+1];
		
		babyBug[0] = 1;
		nowBug[0] = 1;
		
		for(int i=1; i<=n; i++) {
			
			nowBug[i] = nowBug[i-1];
			nowadult[i] = nowadult[i-1];
			
			//baby 성체로 진화
			if (i >= a) {
				adultBug[i] = babyBug[i-a];
				nowadult[i] += babyBug[i-a];
			}
			
			//adult -> old 진화? 퇴화?
			if (i >= adultTerm) {
				oldBug[i] = adultBug[i-adultTerm];
				nowadult[i] -= adultBug[i - adultTerm];
			}
			
			//새로운 자식 생성
			babyBug[i] = (babyBug[i] + nowadult[i]) % 1000;
			nowBug[i] = nowBug[i] + nowadult[i];
			
			//늙은 자식 죽음
			if (i >= oldTerm) {
				nowBug[i] -= oldBug[i - oldTerm];
			}
			
			nowBug[i] %= 1000;
		}
		
		System.out.println(nowBug[n]);
		
	}

}
