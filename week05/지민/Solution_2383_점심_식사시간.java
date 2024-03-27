import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시간: 149ms
 */
public class Solution_2383_점심_식사시간 {
	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static int N;
	static int[][] dist;
	static int resTime;
	static int[] stairTime;
	
	private static void initDist(List<Point> pPoints, List<Point> sPoints) {
		dist = new int[sPoints.size()][pPoints.size()];
		for(int i = 0; i < sPoints.size(); i++) {
			for(int j = 0; j < pPoints.size(); j++) {
				dist[i][j] = Math.abs(sPoints.get(i).y - pPoints.get(j).y) 
						+ Math.abs(sPoints.get(i).x - pPoints.get(j).x);
			}
		}
	}

	private static int move(Queue<Integer> moveTime, int stair) {
		Queue<Integer> downTime = new LinkedList<>(); // 계단을 내려가는 시간
		int tmpPeopleCnt = moveTime.size();
		
		int ready = 0;
		int time = 0;
		while(tmpPeopleCnt > 0) {
			int size = moveTime.size();
			for(int i = 0; i < size; i++) {
				int curr = moveTime.poll();
				if(curr == 0) ready += 1;
				else moveTime.add(curr - 1);
			}
			
			size = downTime.size();
			for(int i = 0; i < size; i++) {
				int curr = downTime.poll();
				if(curr == 0) tmpPeopleCnt -= 1;
				else downTime.add(curr - 1);
			}
			
			while(downTime.size() < 3 && ready > 0) {
				ready -= 1;
				downTime.add(stairTime[stair] - 1);
			}

			time += 1;
		}
		
		return time;
	}
	
	private static int solve(List<Point> pPoints, List<Point> sPoints) {
		initDist(pPoints, sPoints);
		
		resTime = Integer.MAX_VALUE;
		for(int i = 0; i < (1 << pPoints.size()); i++) {
			Queue<Integer> moveTime1 = new LinkedList<>(); // 0번 계단까지 가는 사람들의 이동 시간
			Queue<Integer> moveTime2 = new LinkedList<>(); // 1번 계단까지 가는 사람들의 이동 시간
			for(int j = 0; j < pPoints.size(); j++) {
				if((i & (1 << j)) == 0) moveTime1.offer(dist[0][j]);
				else moveTime2.offer(dist[1][j]);
			}
			int tmpTime = Math.max(move(moveTime1, 0), move(moveTime2, 1));
			resTime = Math.min(resTime, tmpTime);
		}
		
		return resTime;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			List<Point> pPoints = new ArrayList<>();
			List<Point> sPoints = new ArrayList<>();
			stairTime = new int[2];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					int x = Integer.parseInt(st.nextToken());
					if(x == 1) pPoints.add(new Point(i, j));
					else if(x > 1) {
						sPoints.add(new Point(i, j));
						if(stairTime[0] == 0) stairTime[0] = x;
						else stairTime[1] = x;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(solve(pPoints, sPoints)).append("\n");
		}
		System.out.println(sb);

	}
}
