import java.io.*;
import java.util.*;


class Pair {
	int x, y;
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution
{
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[][] graph;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] changeDir = {{},
								{2, 3, 1, 0},
								{1, 3, 0, 2},
								{3, 2, 0, 1},
								{2, 0, 3, 1},
								{2, 3, 0, 1}};
	static HashMap<Integer, Pair> wormholes;
	
	static void init() throws IOException {
		StringTokenizer tokens;
		N = Integer.parseInt(input.readLine().trim());
		wormholes = new HashMap<>();
		ArrayList<Pair> worms = new ArrayList<Pair>();
		graph = new int[N + 2][N + 2];
		for(int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(input.readLine().trim());
			for(int j = 1; j <= N; j++) {
				graph[i][j] = Integer.parseInt(tokens.nextToken());
				if(graph[i][j] > 5) {
					worms.add(new Pair(i, j));
				}
			}
		}
		
		for(int i = 0; i < worms.size(); i++) {
			Pair p1 = worms.get(i);
			for(int j = 0; j < worms.size(); j++) {
				if(i == j) continue;
				Pair p2 = worms.get(j);
				if(graph[p1.x][p1.y] == graph[p2.x][p2.y]) {
					wormholes.put(p1.x * (N + 2) + p1.y, p2);
					break;
				}
			}
		}
		
		for(int i = 0; i < N + 2; i++) {
			graph[0][i] = 5;
			graph[i][0] = 5;
			graph[N + 1][i] = 5;
			graph[i][N + 1] = 5;
		}
	}
	
	static int simulate() {
		int maxScore = 0;
		for(int startx = 1; startx <= N; startx++) {
			for(int starty = 1; starty <= N; starty++) {
				if(graph[startx][starty] != 0) continue;
				for(int d = 0; d < 4; d++) {
					
					int score = 0;
					int x = startx, y = starty;
					int dir = d;
					while(true) {
						x += dx[dir];
						y += dy[dir];
						if(x == startx && y == starty) break;
						if(graph[x][y] == -1) break;
						if(graph[x][y] > 5) {
							Pair dest = wormholes.get(x * (N + 2) + y);
							x = dest.x;
							y = dest.y;
						}
						else if(graph[x][y] != 0) {
							dir = changeDir[graph[x][y]][dir];
							score++;
						}
					}
					if(score > maxScore) maxScore = score;
				}
			}
		}
		return maxScore;
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(input.readLine().trim());
		for(int t = 1; t <= T; t++) {
			init();
			output.write("#" + t + " " + simulate() + "\n");
		}
		output.close();
	}
}