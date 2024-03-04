import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;

public class BOJ11559_PuyoPuyo {
	static int combo=0;
	static char[][] map;
	static boolean[][] visit;
	static boolean flag=true;
	static int[][] move = {{1,0},{-1,0},{0,1},{0,-1}};
	static Deque<Puyo> q = new ArrayDeque<Puyo>();
	static ArrayList<Puyo> temp = new ArrayList<>();
	static ArrayList<Puyo> pop = new ArrayList<>();
	static HashSet<Character> set = new HashSet<>(Arrays.asList('R','G','B','P','Y'));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		
		for(int i=0; i<12; i++) {
			String line = br.readLine();
			for(int j=0; j<6; j++) {
				map[i][j]=line.charAt(j);
			}
		}
		
		while(flag) { // 현재 상황에서 터질수 있는 모든게 다 터지고 내려감
			
			flag = false; // 초기화
//			show();
//			System.out.println();
			visit = new boolean[12][6]; // 초기화
			for(int i=11; i>=0; i--) { 	// 굳이 하단부터 체크할 필요는 없는듯
				for(int j=0; j<6; j++) {
					if(map[i][j]!='.' && !visit[i][j]) {
						visit[i][j]=true;
						q.offer(new Puyo(i,j));
						bfs(map[i][j]);
					}
				}
			}
			setNext();
		}
		
		System.out.println(combo);
	}
	static void bfs(char c) {
		int count = 0;
		while(!q.isEmpty()) {
			Puyo cur = q.poll();
			temp.add(cur);
			count++;
			for(int m=0; m<4; m++) {
				int ni = cur.i+move[m][0];
				int nj = cur.j+move[m][1];
				if(inRange(ni,nj) && !visit[ni][nj] && map[ni][nj]==c) {
					visit[ni][nj]=true;
					q.offer(new Puyo(ni,nj));
				}
			}
		}
		if(count>=4) {  // 4개 이상 붙은 경우
			pop.addAll(pop.size(), temp);
			flag=true;	// 계속 진행해도 됨
		}
		temp.clear();
	}
	static void setNext() { // 위쪽 뿌요부터 제거하고 한칸씩 내림
		Collections.sort(pop);
		for(int n=0; n<pop.size(); n++) {
			Puyo cur = pop.get(n);
			for(int i=cur.i; i>0; i--) {
				map[i][cur.j]=map[i-1][cur.j];
			}
			map[0][cur.j]='.';
		}
		if(pop.size()>0)
			combo++;
		pop.clear();
	}
	static boolean inRange(int i, int j) {
		if(i<0 || i>=12 || j<0 || j>=6)
			return false;
		return true;
	}
	static void show() {
		for(int i=0; i<12; i++) {
			for(int j=0; j<6; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
class Puyo implements Comparable<Puyo>{
	int i,j;

	public Puyo(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	@Override
	public int compareTo(Puyo o) {
		if(i==o.i)	return j-o.j;
		return i-o.i;
	}
	
}
