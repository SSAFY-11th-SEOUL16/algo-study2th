// 656ms
import java.io.*;
import java.util.*;

public class Main {
	/*
	 * 각 사람 별로 친구집합 ally와 적 집합 enemy를 기록해야 한다.
	 * getParent(ally[i]) - i번 사람의 동맹 집합 대표
	 * getParrent(enemy[i]) - i번 사람의 적 집합 대표
	 * 
	 * 만약 i와 j가 적이라면
	 * 1. i의 적을 j의 동맹에 포함시킨다
	 * 2. j의 적을 i의 동맹에 포함시킨다
	 * 3. 이 때 j의 동맹 대표는 i의 적 대표가 된다
	 * 4. 마찬가지로 i의 동맹 대표는 j의 적 대표가 된다
	 * 성립하지 않는 경우
	 * - i와 j가 적이라고 주어졌는데 동맹인 경우
	 * 
	 * 결론 - 집합 배열은 동맹집합을 표현하는 배열 하나만 있어도 된다
	 */
	
	static int[] parents, enemies;
	static int N, M;
	
	static int getParent(int x) {
		if(parents[x] != x) {
			parents[x] = getParent(parents[x]);
		}
		return parents[x];
	}
	
	static int union(int x, int y) {
		x = getParent(x);
		y = getParent(y);
		if(x < y) {
			parents[y] = x;
			return x;
		} else {
			parents[x] = y;
			return y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		parents = new int[N + 1];
		enemies = new int[N + 1];
		for(int i = 1; i <= N; i++) parents[i] = i;
		int flag = 1;
		
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(input.readLine());
			int u = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			u = getParent(u); v = getParent(v);
			if(u == v) {
				flag = 0;
				break;
			}
			if(enemies[u] == 0) enemies[u] = v;
			if(enemies[v] == 0) enemies[v] = u;
			enemies[v] = union(u, enemies[v]);
			enemies[u] = union(v, enemies[u]);
		}
		System.out.println(flag);
	}

}
