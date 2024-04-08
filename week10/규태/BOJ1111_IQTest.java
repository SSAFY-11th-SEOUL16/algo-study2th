import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1111_IQTest {
	/*
	 * 80ms
	 * 1개일때 가능한 값 무수히 많음
	 * 2개일때 일차함수의 성질로 두 수가 같으면 계속 같은 값, 다르면 가능한 값 무수히 많음  
	 * 3개 이상일때 연립일차방정식의 풀이로 해결하여 해가 일치하는지 확인하는 과정 반복
	 */
	static final int INF = Integer.MAX_VALUE;
	static int n,num[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) 
			num[i] = Integer.parseInt(st.nextToken());
		
		if(n==1)
			System.out.println("A");
		else if(n==2) {
			if(num[0]==num[1])
				System.out.println(num[0]);
			else
				System.out.println("A");
		}
		else {
			int[] rule = findRule(num[0],num[1],num[2]);

			if(rule[0]==INF)
				System.out.println("B");
			else {
				//처음 이외에도 check 필요
				boolean flag = true;
				for(int i=3; i<n; i++) {
					if(num[i]!=num[i-1]*rule[0]+rule[1]) {
						flag=false; break;
					}
				}
				
				if(!flag)
					System.out.println("B");
				else
					System.out.println(num[n-1]*rule[0]+rule[1]);
			}
				
		}
	}
	static int[] findRule(int p,int q,int r) {
		if(p==q) {
			if(q==r) return new int[] {1,0};
			else return new int[] {INF,INF};
		}
			
		int a = (r-q)%(q-p)==0? (r-q)/(q-p):INF;
		int b = a!=INF? q-p*a:INF;
		return new int[] {a,b};
	}
}
