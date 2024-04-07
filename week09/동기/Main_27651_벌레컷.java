import java.util.*;
import java.io.*;
/*
 * 772ms
 */
public class Main {
	static int n;
	static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		for (int i = 1; i < arr.length; i++) 
			arr[i] += arr[i - 1];

		long answer = 0;
		for (int i = 0; i < n - 2; i++) { // 가슴,배는 남겨야함
			if ((arr[i] * 2) + 3 > arr[n - 1] - arr[i]) // 머리크기 상한선을 넘으면 탈출
				break;
			int cnt = bs(i);
			if (cnt == 0) // 0을 반환할 경우 끝났다고 판단하고 탈출
				break;
			answer += cnt;
		}

		System.out.println(answer);
	}

	// O(1)
	// midline까지가 가슴
	static boolean checkMidLine(int headIndex, int midLine) {
		long belly = arr[n - 1] - arr[midLine];
		long chest = arr[midLine] - arr[headIndex];
		return chest > belly;

	}

	// O(log(n))
	// 머리보다 큰 배의 최소 인덱스 구하기
	static int findminMidLine(int headIndex) {
		int start = headIndex + 1;
		int end = n - 2; // midline이 가슴을 포함하는 인덱스이기 때문

		// mid 구할때 +1 해주는 이유는 end값 기준으로 범위를 줄이므로 start=홀수, end=짝수면 무한루프 돌기 때문! 
		while (start < end) {
			int mid = (start + end + 1) / 2; 
			if (arr[headIndex] < arr[n - 1] - arr[mid]) {
				start = mid;
			} else {
				end = mid - 1;
			}
		}

		if (arr[headIndex] < arr[n - 1] - arr[start]) {
			return start;
		} else {// 최소 인텍스 구했는데 얘도 안되는 경우
			return 0;

		}

	}

	// O(log(n))
	static int bs(int headIndex) {
		int start = headIndex + 1;
		int end = findminMidLine(headIndex); // O(log(n))

		if (end == 0)
			return 0; // findminMidLine이 0반환하면 경우가 없는겨

		int endTemp = end;

		while (start < end) { // O(log(n))
			int mid = (start + end) / 2;
			if (checkMidLine(headIndex, mid))
				end = mid;
			else
				start = mid + 1;
		}
		// start가 실패 = 그냥 아예 이 헤드인덱스 상태로는 안되는 경우
		if (!checkMidLine(headIndex, start)) {
			return 0;
		} else { // start가 성공이면 하던거 계속 해
			return endTemp + 1 - start;
		}
	}

}
