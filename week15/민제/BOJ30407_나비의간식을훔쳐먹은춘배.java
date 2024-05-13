import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ30407_나비의간식을훔쳐먹은춘배 {

	static int n, k, h, d;
	static int[] damage;
	// 턴 거리 기회
	static int[][][] maxHealth;
	static int result = -1;

	static class Chunbae implements Comparable<Chunbae>{
		int chance, turn, dist, health;

		public Chunbae(int chance, int turn, int dist, int health) {
			this.chance = chance;
			this.turn = turn;
			this.dist = dist;
			this.health = health;
		}

		@Override
		public int compareTo(Chunbae o) {
			return o.health - health;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		damage = new int[n+1];
		for (int i = 1; i <= n; i++) {
			damage[i] = Integer.parseInt(br.readLine());
		}
		maxHealth = new int[n + 3][101][2];
	
		System.out.println(bfs());
	}

	static int bfs() {
		PriorityQueue<Chunbae> pq = new PriorityQueue<>();
		pq.add(new Chunbae(0, 1, d, h));
		maxHealth[0][d][0] = h;
		
		while (!pq.isEmpty()) {
			Chunbae poll = pq.poll();
			
			if (poll.turn >= n+1) {
				return poll.health;
			}
			
			//1.웅크리기
			int calDamage = (damage[poll.turn] - poll.dist) >= 0 ? (damage[poll.turn] - poll.dist) / 2 : 0;
			if (poll.health > calDamage && maxHealth[poll.turn+1][poll.dist][poll.chance] < poll.health - calDamage) {
				maxHealth[poll.turn+1][poll.dist][poll.chance] = poll.health - calDamage;
				pq.add(new Chunbae(poll.chance, poll.turn + 1, poll.dist, poll.health - calDamage));
			}
			
			//2.네발로 걷기
			int nextDist = poll.dist + k;
			calDamage = (damage[poll.turn] - nextDist) >= 0 ? (damage[poll.turn] - nextDist) : 0;
			if (poll.health > calDamage && maxHealth[poll.turn+1][nextDist][poll.chance] < poll.health - calDamage) {
				maxHealth[poll.turn+1][nextDist][poll.chance] = poll.health - calDamage;
				pq.add(new Chunbae(poll.chance, poll.turn + 1, nextDist, poll.health - calDamage));
			}
			
			//3.놀래키기
			/*
			 * 놀래키고 움직이는게 무조건 이득
			 */
			if (poll.chance == 0) {
				calDamage = (damage[poll.turn] - poll.dist) >= 0 ? (damage[poll.turn] - poll.dist) : 0;
				if (poll.health > calDamage && maxHealth[poll.turn+2][nextDist][1] < poll.health - calDamage) {
					maxHealth[poll.turn+2][nextDist][1] = poll.health - calDamage;
					pq.add(new Chunbae(1, poll.turn + 2, nextDist, poll.health - calDamage));
				}
			}
		}
		
		return -1;
	}

}
