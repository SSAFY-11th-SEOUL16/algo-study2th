import java.io.*;
import java.util.*;

public class BOJ15922_아우으_우아으이야 {
	/*
	 * 424ms
	 * 곡선자르기 문제를 연상시키며 문제 해결
	 * 시작점을 기준으로 정렬
	 * 스택 사용하여 겹치면서 길이 연장이 있을경우 스택에 쌓기
	 * 앞의 선분그룹과 불연속인 선분이 나타난경우 스택에 쌓인 선분그룹의 길이를 답에 더하고 스택 비우기 반복 
	 */
	static int n,total;
	static ArrayList<int[]> line = new ArrayList<>();
	static Stack<int[]> stack = new Stack<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			line.add(new int[] {start,end});
		}

		stack.push(line.get(0));
		for(int i=1; i<n; i++) {
			int[] next = line.get(i);
			if(stack.peek()[1]>=next[0]) {
				if(stack.peek()[1]<next[1])
					stack.push(next);
			}
			else {
				addLength();
				stack.push(next);
			}
		}
		addLength(); // 마지막에 스택에 남아있지 않게 비워주기
		System.out.println(total);
	}
	static void addLength() {
		total+=stack.peek()[1];
		int start=Integer.MAX_VALUE;
		while(!stack.isEmpty()) 
			start = stack.pop()[0];
		total-=start;
	}
}
