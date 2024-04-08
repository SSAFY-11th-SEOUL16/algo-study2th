import java.util.*;
import java.io.*;
import java.math.*;
/*
 * 232ms
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		String answer="";
		if(n==1) {
			answer = "A";
		}else if(n==2) {
			answer = arr[0] == arr[1] ? arr[0] +"" : "A";
		}else if(n>=3) {
			if(arr[0] == arr[1]) {
				boolean isB = false;
				for(int i=1; i<n; i++) {
					if(arr[i] != arr[i-1]) {
						isB = true;
						break;
					}
				}
				answer = isB ? "B" : arr[0]+"" ;
			}else {
				if((arr[1]-arr[2])%(arr[0] - arr[1])==0) { // a,b가 정수일 경우
					int a = (arr[1]-arr[2])/(arr[0] - arr[1]);
					int b = arr[1] - (a*arr[0]);
					
					boolean isB = false;
					for(int i=1; i<n; i++) {
						int v = (arr[i-1]*a) +b;
						if(arr[i] != v) {
							isB = true;
							break;
						}
					}
					answer = isB ? "B" : ((arr[n-1]*a)+b)+"" ;
				}else { //a,b가 정수가 아닐 경우
					answer = "B";
				}
				
			}
			
		}
		System.out.println(answer);
		
	}
}

/*
	1 항상 A  

	2-1) 1,2를 확인하여 두 값이 같으면 값 그대로 출력
	2-2) 아니면 A 출력

	3-1) 1,2,3을 통해 점화식 도출한 이후, a,b값을 구한다
	3-2) a,b값이 정수가 아니라면 b 정수라면 다른값들도 점화식대로 가는지 확인
	3-3) 모든 값들이 점화식대로 증가,감소 한다면 다음 값을 계산하여 출력
	3-4) 1개 이상의 값이 점화식에 맞지 않는다면 B 출력
	
	3-예외) arr[0] == arr[1] 이면 나누기 0 되어버리니까 모든 v가 arr[0]과 같으면 arr[0] 출력 /아니면 B
		   
	
	
	arr[1] = arr[0]*a + b
	arr[2] = arr[1]*a + b

	arr[1] - arr[2] 
	---------------  = a
	arr[0] - arr[1]

	b = arr[1] - arr[0]*a

*/