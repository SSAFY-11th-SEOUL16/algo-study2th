import java.io.*;
import java.util.*;

public class BOJ30407_나비의간식을훔쳐먹은춘배 {
	
	/*
	 *  - 84ms
	 * 
	 *  - memoization & bfs
	 */
	
	static class Chunbae{
		
		int turn;
		int hp;
		int dist;
		boolean surprise;
		
		Chunbae(int turn, int hp, int dist, boolean surprise){
			this.turn=turn;
			this.hp=hp;
			this.dist=dist;
			this.surprise=surprise;
		}
		
		Chunbae defense(int damage) {
			return new Chunbae(turn+1, hp-(Math.max(0, damage-dist)/2), dist ,surprise);
		}
		
		Chunbae move(int damage) {
			return new Chunbae(turn+1, hp-(Math.max(0, damage-dist-mvDist)), dist+mvDist, surprise);
		}
		
		Chunbae surprise(int damage) {
			return new Chunbae(turn+2, hp-(Math.max(0, damage-dist)), dist+mvDist, true);
		}

	}
	
	// n번째 펀치에서 Dist N일떄 최대 체력 memoization
	static int[][][] memo;
	static int[] punch;
	static int mvDist;
	static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		memo = new int[n][200][2];
		punch = new int[n];
		
		st = new StringTokenizer(br.readLine());
		Chunbae init = new Chunbae(0, Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()), false);
		
		mvDist = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			punch[i] = Integer.parseInt(br.readLine());
		}
		
		Queue<Chunbae> q = new LinkedList<>();
		q.add(init);
		max = -1;
		
		while(!q.isEmpty()) {
			Chunbae cur = q.poll();
			
			if(cur.turn>=n) {
				if(max<cur.hp) {
					max = cur.hp;
				}
				continue;
			}
			
			if(memo[cur.turn][cur.dist][cur.surprise ? 1:0] >= cur.hp) continue;
			
			memo[cur.turn][cur.dist][cur.surprise ? 1:0] = cur.hp;
			
			int damage = punch[cur.turn];
			
			q.add(cur.defense(damage));
			q.add(cur.move(damage));
			
			if(!cur.surprise) {
				q.add(cur.surprise(damage));
			}
		}
		
		if(max>=0) {
			System.out.println(max);
		} else {
			System.out.println(-1);
		}
		
	}

}
