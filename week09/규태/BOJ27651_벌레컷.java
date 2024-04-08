import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ27651_벌레컷 {
	/* 
	 * 472ms
	 * 가슴/배 먼저 뒤쪽에서 자르고, 
	 * 가능한 머리/가슴 위치 이분탐색으로 탐색해서 가능한 가장 큰 idx를 찾고 답에 추가
	 * 가슴/배 앞으로 한칸 당기고 이분 탐색의 과정 반복
	 * 머리<배<가슴 이므로 배가 전체의 절반을 넘길수 없고, 절반이 되기 전까지만 반복
  	 */
	static int n,bellycut;
	static long ans=0;
	static long sum[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		sum = new long[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++)
			sum[i] = sum[i-1]+Integer.parseInt(st.nextToken());
		
		bellycut=n;
		while(sum[n]-sum[--bellycut]<sum[n]/2) {
			int start = 1;
			int end = bellycut;
			int count=0;
			while(start<end) {
				int mid = (start+end)/2;
				long head=sum[mid];
				long body=sum[bellycut]-sum[mid];
				long belly=sum[n]-sum[bellycut];
				
				if(head<belly && belly<body) {
					start=mid+1;					
//					System.out.println(bellycut+" "+mid);
					count=Math.max(count, mid);
				}
				else end=mid;
			}
			ans+=count;
		}
		
		System.out.println(ans);
	}
}
