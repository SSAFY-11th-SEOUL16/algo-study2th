import java.io.*;
import java.util.*;

public class BOJ20056_마법사상어와파이어볼 {
	
	static int N;
	
	public static boolean inRange(int x, int y, int N) {
		return x > 0 && y > 0 && x <= N && y <= N;
	}
	
	public static int modulus(int x) {
		int coef = Math.abs(x) / N + 1;
		
		return (x + coef * N - 1) % N + 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int M, K;
		int[][] offsets = {{-1, 0},{-1, 1},{0, 1},{1, 1},{1, 0},{1, -1},{0, -1},{-1, -1},};
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		ArrayList<Integer[]> fireballs = new ArrayList<Integer[]>();
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(input.readLine());
			Integer[] fire = new Integer[5];
			for(int j = 0; j < 5; j++) {
				fire[j] = Integer.parseInt(tokens.nextToken());
			}
			fireballs.add(fire);
		}
		for(int i = 0; i < K; i++) {
			ArrayList<Integer[]> tmp = new ArrayList<Integer[]>();
			for(Integer[] fire: fireballs) {
				fire[0] = modulus(fire[0] + offsets[fire[4]][0] * fire[3]);
				fire[1] = modulus(fire[1] + offsets[fire[4]][1] * fire[3]);
				tmp.add(fire);
			}
			tmp.sort((o1, o2)->{
				int diff = o1[0] - o2[0];
				if(diff != 0) return diff;
				return o1[1] - o2[1];
			});
			fireballs.clear();
			LinkedList<Integer[]> list = new LinkedList<>();
			int ones = 0;
			int mass = 0;
			int speed = 0;
			for(Integer[] fire: tmp) {
				if(list.isEmpty() || (list.peek()[0] == fire[0] && list.peek()[1] == fire[1])) {
					
				} else if(list.size() == 1){
					fireballs.add(list.poll());
					ones = 0;
					mass = 0;
					speed = 0;
				} else {
					int start = 1;
					if(ones == 0 || ones == list.size()) {
						start = 0;
					}
					int x = list.peek()[0];
					int y = list.peek()[1];
					if(mass / 5 > 0) {
						for(int d = start; d < 8; d += 2) {
							fireballs.add(new Integer[] {x, y, mass / 5, speed / list.size(), d});
						}
					}
					list.clear();
					ones = 0;
					mass = 0;
					speed = 0;
				}
				list.add(fire);
				ones += fire[4] % 2;
				mass += fire[2];
				speed += fire[3];
			}
			if(list.isEmpty()) continue;
			if(list.size() == 1){
				fireballs.add(list.poll());
				ones = 0;
				mass = 0;
				speed = 0;
			}
			else {
				int start = 1;
				if(ones == 0 || ones == list.size()) {
					start = 0;
				}
				int x = list.peek()[0];
				int y = list.peek()[1];
				if(mass / 5 > 0) {
					for(int d = start; d < 8; d += 2) {
						fireballs.add(new Integer[] {x, y, mass / 5, speed / list.size(), d});
					}
				}
				list.clear();
				ones = 0;
				mass = 0;
				speed = 0;
			}
		}
		int answer = 0;
		for(Integer[] fire: fireballs) {
			answer += fire[2];
		}
		System.out.println(answer);
	}

}
