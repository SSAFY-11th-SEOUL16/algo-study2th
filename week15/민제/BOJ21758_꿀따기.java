import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21758_꿀따기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] sum = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		sum[0] = arr[0];
		for(int i=1; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1] + arr[i];
		}
		
		int result = 0;
		for(int i=1; i<n-1; i++) {
			//오른쪽 끝이 goal
			int rightGoalScore = (sum[n-1] - arr[0] - arr[i]) + (sum[n-1] - sum[i]);
			result = Math.max(result, rightGoalScore);
			//왼쪽 끝이 goal
			int leftGoalScore = (sum[n-1] - arr[n-1] - arr[i]) + sum[i-1];
			result = Math.max(result, leftGoalScore);
			//가운데 goal
			int midGoalScore = (sum[i] - arr[0]) + (sum[n-1] - sum[i-1] - arr[n-1]);
			result = Math.max(result, midGoalScore);
		}
		
		
		System.out.println(result);
	}
	
}
