import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW5650_핀볼게임 {
	static int N;
	static int result;
	static int dirX, dirY;
	static int idxX, idxY;
	static int startX, startY;
	static int[][] board;
	static int[][] wh;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];
			ArrayList<int[]> start = new ArrayList<>();
			wh = new int[11][5];
			result = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 0)
						start.add(new int[] { i, j });
					if (board[i][j] >= 6 && board[i][j] <= 10) {
						if (wh[board[i][j]][4] == 0) {
							wh[board[i][j]][0] = i;
							wh[board[i][j]][1] = j;
							wh[board[i][j]][4] = 1;
						} else {
							wh[board[i][j]][2] = i;
							wh[board[i][j]][3] = j;
						}
					}
				}
			}
			for (int k = 0; k < start.size(); k++) {
				startX = start.get(k)[0];
				startY = start.get(k)[1];
				for (int[] dir : dirs) {
					idxX = startX;
					idxY = startY;
					dirX = dir[0];
					dirY = dir[1];
					if (startX + dirX < 0 || startX + dirX >= N || startY + dirY < 0 || startY + dirY >= N) {
						result = Math.max(result, 1);
						continue;
					}
					simulate();
				}
			}
			sb.append("#").append(tc).append(" ").append(result);
			if (tc < T)
				sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void simulate() {
		int score = 0;
		while (true) {
			int nx = idxX + dirX;
			int ny = idxY + dirY;

			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				uTurn();
				if (board[idxX][idxY] == 0) {
					score++;
					continue;
				}
				else if (board[idxX][idxY] >= 6 && board[idxX][idxY] <= 10) {
					warp();
					score++;
					continue;
				}
				else {
					if (board[idxX][idxY] == 1 || board[idxX][idxY] == 2 || board[idxX][idxY] == 3
							|| board[idxX][idxY] == 4) {
						if (board[idxX][idxY] == 1 || board[idxX][idxY] == 3) {
							swap();
							score+=2;
							continue;
						} else {
							swapTurn();
							score+=2;
							continue;
						}
					}
				}
			}

			idxX = nx;
			idxY = ny;

			if (board[idxX][idxY] == 1 || board[idxX][idxY] == 2 || board[idxX][idxY] == 3 || board[idxX][idxY] == 4) {
				if (isFlat()) {
					uTurn();
					score++;
				} 
				else {
					if (board[idxX][idxY] == 1 || board[idxX][idxY] == 3) {
						swap();
						score++;
					} else {
						swapTurn();
						score++;
					}
				}
			} 
			else if (board[idxX][idxY] == 5) {
				uTurn();
				score++;
			}
			else if (board[idxX][idxY] >= 6 && board[idxX][idxY] <= 10) {
				warp();
			}
			if (board[idxX][idxY] == -1 || (idxX == startX && idxY == startY)) {
				result = Math.max(result, score);
				return;
			}
		}
	}

	public static void swap() {
		int temp = dirX;
		dirX = dirY;
		dirY = temp;
	}

	public static void uTurn() {
		dirX = -dirX;
		dirY = -dirY;
	}

	public static void swapTurn() {
		int temp = -dirX;
		dirX = -dirY;
		dirY = temp;
	}
	
	public static void warp() {
		if (wh[board[idxX][idxY]][0] == idxX && wh[board[idxX][idxY]][1] == idxY) {
			int whNum = board[idxX][idxY];
			idxX = wh[whNum][2];
			idxY = wh[whNum][3];
		} else if (wh[board[idxX][idxY]][2] == idxX && wh[board[idxX][idxY]][3] == idxY) {
			int whNum = board[idxX][idxY];
			idxX = wh[whNum][0];
			idxY = wh[whNum][1];
		}
	}
	
	public static boolean isFlat() {
		if (board[idxX][idxY] == 1) {
			if ((dirX == -1 && dirY == 0) || (dirX == 0 && dirY == 1))
				return true;
			else {
				return false;
			}
		} else if (board[idxX][idxY] == 2) {
			if ((dirX == 1 && dirY == 0) || (dirX == 0 && dirY == 1))
				return true;
			else {
				return false;
			}
		} else if (board[idxX][idxY] == 3) {
			if ((dirX == 1 && dirY == 0) || (dirX == 0 && dirY == -1))
				return true;
			else {
				return false;
			}
		} else if (board[idxX][idxY] == 4) {
			if ((dirX == -1 && dirY == 0) || (dirX == 0 && dirY == -1))
				return true;
			else {
				return false;
			}
		} else
			return false;
	}
}
