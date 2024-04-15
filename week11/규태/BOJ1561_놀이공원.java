import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1561_놀이공원 {
	/*
	 * 124ms
	 * 이분탐색 활용하여 불가능한 최대소요 시간을 구하고 
	 * 불가능한 최대소요 시간 다음 시간에 몇번 놀이기구가 답인지 도출 
	 */
	static int n,m,rides[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		rides = new int[m]; // 놀이기구 정보
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++)
			rides[i] = Integer.parseInt(st.nextToken());
		
		// 불가능한 최대 시간 구하기
		long start=0,end=2000000000*30L; long notpossible=-1; long count=0;
		while(start<end) {
			long mid = (start+end)/2;
			long avail=0;
			for(int min:rides)
				avail+=mid/min+1;
			if(avail>=n) end=mid;
			else {
				notpossible=mid; count=avail;
				start=mid+1; 
			}
		}
		
		int answer=-1;
		for(int idx=0; idx<m; idx++) {
			if((notpossible+1)%rides[idx]==0) count++;
			if(count==n) {
				answer=idx+1; break;
			}
		}
//		System.out.println(notpossible+1);
		System.out.println(answer);
	}
}
