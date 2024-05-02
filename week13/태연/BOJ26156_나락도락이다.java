import java.io.*;

public class BOJ26156_나락도락이다 {
	
	/*
	 * - 220ms 
	 * 
	 *  현재 R이 마지막 R이라고 가정 -> OCK가 가진 경우의수 * pow[i]
	 *
	 *	OCK가 가진 경우의수 -> 모든 O에 대해서 CK가 가진 경우의수의 합
	 *
	 *	CK가 가진 경우의수 -> 모든 C에 대해서 뒤쪽 K개수의 합
	 *
	 *	=> 뒤에서부터 연산
	 *
	 *	K를 만났을때 -> K 개수 업데이트
	 *
	 *	C를 만났을때 -> 현재시점 K 개수에 따라서 C 누적합에 K 추가
	 *
	 *	O를 만났을때 -> 현재 시점 C의 누적합에 대해서 O 누적합에 sumC 추가
	 *
	 *	R을 만났을때 -> 현재시점 O의 누적합에 대해서 pow[i] * sumO 연산
	 */
	
	static final int MOD = 1000000007;
		
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		String st = br.readLine();
		
		int[] pow = new int[n];
		pow[0] = 1;
		for(int i=1; i<n; i++) {
			pow[i] = (pow[i-1]<<1)%MOD;
		}
		
		int result = 0;
		
		long sumO = 0;
		long sumC = 0;
		int cntK = 0;
		
		char c;
		
		for(int i=n-1; i>=0; i--) {
			
			c = st.charAt(i);
			
			if(c == 'K') {
				cntK++;
			} else if (c =='C') {
				sumC += cntK;
				sumC %= MOD;
			} else if(c == 'O') {
				sumO += sumC;
				sumO %= MOD;
			} else if(c == 'R') {
				result += (pow[i]*sumO)%MOD;
				result %= MOD;
			}
		}
		
		System.out.print(result);
	}
}
