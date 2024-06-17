import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2317_결혼식 {
	/*
	 * 156ms
	 * 사자만 순서를 조정할 수 없으므로 배열로 저장하고 다른사람들의 키는 조정할 수 있으므로 저장x
	 * 사자의 min max값과 다른사람들의 min max를 고려하여 사자의 순서 중간에 넣어보고
	 * 그 중 키차이의 합이 최소인 순간을 답으로 계산
	 */
	static int n, k, lion[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken()); 
		lion = new int[k];
		
		int lmin=Integer.MAX_VALUE;
		int lmax=0;
		for(int i=0; i<k; i++) {
			lion[i] = Integer.parseInt(br.readLine());
			lmin = Math.min(lmin, lion[i]);
			lmax = Math.max(lmax, lion[i]);
		}
		
		int min=Integer.MAX_VALUE;
		int max=0;
		for(int i=0; i<n-k; i++) {
			int next = Integer.parseInt(br.readLine());
			min = Math.min(min, next);
			max = Math.max(max, next);
		}
		
		long tmp=0;
		for(int i=1; i<k; i++)
			tmp+=Math.abs(lion[i]-lion[i-1]);
		
		long ans = Long.MAX_VALUE;
		if(lmin<=min && max<=lmax) {
			ans=tmp;
		}
		else if (lmin<=min && max>lmax) {
			for(int i=0; i<k; i++) 
				ans = Math.min(ans,tmp+Math.abs(max-lion[i])*((i==0 || i==k-1)? 1:2));
		}
		else if (lmin>min && max<=lmax)	{
			for(int i=0; i<k; i++) 
				ans = Math.min(ans,tmp+Math.abs(min-lion[i])*((i==0 || i==k-1)? 1:2));
		}
		else {
			for(int i=0; i<k; i++) {
				long tmp2 = tmp+Math.abs(max-lion[i])*((i==0 || i==k-1)? 1:2);
				for(int j=0; j<k; j++) {
					if(i==j) continue;
					ans = Math.min(ans, tmp2+Math.abs(min-lion[j])*((j==0 || j==k-1)? 1:2));
				}
			}
		}
		System.out.println(ans);
	}
}
