// 128ms

import java.io.*;
import java.util.*;

public class BOJ17220_마약수사대 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		int isChild = 0;
		ArrayList<Integer>[] graph = new ArrayList[N];
		for(int i = 0; i < N; i++) graph[i] = new ArrayList<Integer>();
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int p = tokens.nextToken().charAt(0) - 'A';
			int c = tokens.nextToken().charAt(0) - 'A';
			graph[p].add(c);
			isChild |= 1<<c;
		}
		// 공급책이 2개 이상일 수 있다니... 상상도 못한 정체
		int visited = 0;
		Deque<Integer> queue = new ArrayDeque<>();
		tokens = new StringTokenizer(input.readLine());
		int K = Integer.parseInt(tokens.nextToken());
		for(int i = 0; i < K; i++) {
			visited |= 1<<(tokens.nextToken().charAt(0) - 'A');
		}
		for(int i = 0; i < N; i++) {
			if((isChild & 1<<i) == 0 && (visited & 1<<i) == 0) {
				queue.add(i);
				visited |= 1 << i;
			}
		}
		int answer = 0;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for(int next: graph[current]) {
				if((1<<next & visited) != 0) continue;
				queue.offer(next);
				visited |= 1<<next;
				answer += 1;
			}
		}
		System.out.println(answer);
	}
}
