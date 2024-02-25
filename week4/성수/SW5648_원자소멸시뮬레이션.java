import java.io.*;
import java.util.*;

class Atom {
	int x, y, dir, energy;

	public Atom(int x, int y, int dir, int energy) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.energy = energy;
	}
}

public class SW5648_원자소멸시뮬레이션 {
	
	
	static int[][] offsets = {{0, 1},{0, -1},{-1, 0},{1, 0}};
	static HashMap<Integer, Deque<Atom>> collider= new HashMap<>(); // 충돌 확인용 해시맵
	static Deque<Atom> atoms = new LinkedList<>(); // 아직 남아있는 원자들
	static final int MAX_POS = 4000;

	public static int getKey(int x, int y) { // 2^12=4096
		return (x<<12)|y;
	}
	
	public static void add(int key, Atom atom) { // collider에 원자 넣기
		if(!collider.containsKey(key)) collider.put(key, new LinkedList<Atom>());
		collider.get(key).offer(atom);
	}
	
	public static void simulate() { // 남아있는 원자들을 움직인 뒤 collider에 넣는다.
		while(!atoms.isEmpty()) {
			Atom cur = atoms.poll();
			cur.x += offsets[cur.dir][0];
			cur.y += offsets[cur.dir][1];
			if(cur.x < 0 || cur.y < 0 || cur.x > MAX_POS || cur.y > MAX_POS) continue;
			add(getKey(cur.x, cur.y), cur);
		}
	}
	
	public static int collide() { // 충돌이 있는지 확인 후 남아있는 원자들 atoms에 다시 넣어줌
		int emitted = 0;
		for(int key: collider.keySet()) {
			Deque<Atom> queue = collider.get(key);
			if(queue.size() == 1) {
				atoms.offer(queue.poll());
				continue;
			}
			while(!queue.isEmpty())
				emitted += queue.poll().energy;
		}
		collider.clear();
		return emitted;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(input.readLine());
		StringTokenizer tokens;
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(input.readLine());
			int total = 0;
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(input.readLine());
				int x, y, d, e;
				x = (Integer.parseInt(tokens.nextToken()) + 1000) * 2;
				y = (Integer.parseInt(tokens.nextToken()) + 1000) * 2;
				d = Integer.parseInt(tokens.nextToken());
				e = Integer.parseInt(tokens.nextToken());
				atoms.offer(new Atom(x, y, d, e));
			}
			for(int i = 0; i < MAX_POS; i++) {
				simulate(); // 원자 움직이고
				total += collide(); // 충돌확인
				if(atoms.isEmpty()) break; // 남은 원자가 없다면
			}
			atoms.clear();
			output.write(String.format("#%d %d\n", t, total));
		}
		output.close();
	}

}
