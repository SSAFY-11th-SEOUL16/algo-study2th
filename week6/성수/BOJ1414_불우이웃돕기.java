// 224ms

import java.io.*;
import java.util.*;

public class BOJ1414_불우이웃돕기 {
	static int[] parents;
	
	static int getParent(int x) {
		if(x == parents[x]) return x;
		parents[x] = getParent(parents[x]);
		return parents[x];
	}
	
	static boolean union(int x, int y) {
		x = getParent(x);
		y = getParent(y);
		if(x == y) return false;
		if(x < y) parents[y] = x;
		else parents[x] = y;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(input.readLine());
		int total = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[2] - o2[2]);
		parents = new int[N];
		for(int i = 0; i < N; i++) {
			parents[i] = i;
			char[] tmp = input.readLine().toCharArray();
			for(int j = 0; j < N; j++) {
				int length;
				if(tmp[j] == '0') {
					continue;
				} else if(Character.isLowerCase(tmp[j])) {
					length = tmp[j] - 'a' + 1;
				} else {
					length = tmp[j] - 'A' + 27;
				}
				total += length;
				pq.offer(new int[] {i, j, length});
			}
		}
		int edges = 0;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			if(union(cur[0], cur[1])) {
				edges++;
				total -= cur[2];
				if(edges + 1 == N) break;
			}
		}
		if(edges + 1 == N) {
			System.out.println(total);
		} else {
			System.out.println(-1);
		}
	}
}