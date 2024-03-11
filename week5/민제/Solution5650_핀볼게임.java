import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution5650_핀볼게임 {

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static boolean[][][] visited;
    static List<Pos>[] wormhole;

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static int change_dir(int x, int y, int dir, int[][] board) {

        if (board[x][y] == 1) {
            if (dir == UP) return DOWN;
            else if (dir == DOWN) return RIGHT;
            else if (dir == LEFT) return UP;
            else if (dir == RIGHT) return LEFT;
        } else if (board[x][y] == 2) {
            if (dir == UP) return RIGHT;
            else if (dir == DOWN) return UP;
            else if (dir == LEFT) return DOWN;
            else if (dir == RIGHT) return LEFT;
        } else if (board[x][y] == 3) {
            if (dir == UP) return LEFT;
            else if (dir == DOWN) return UP;
            else if (dir == LEFT) return RIGHT;
            else if (dir == RIGHT) return DOWN;
        } else if (board[x][y] == 4) {
            if (dir == UP) return DOWN;
            else if (dir == DOWN) return LEFT;
            else if (dir == LEFT) return RIGHT;
            else if (dir == RIGHT) return UP;
        } else if (board[x][y] == 5) {
            if (dir == UP) return DOWN;
            else if (dir == DOWN) return UP;
            else if (dir == LEFT) return RIGHT;
            else if (dir == RIGHT) return LEFT;
        }

        return dir;
    }

    static int play(int x, int y, int dir, int[][] board) {

        int[] dxs = {-1, 1, 0, 0};
        int[] dys = {0, 0, -1, 1};

        int start_x = x;
        int start_y = y;
        int start_dir = dir;

        int score = 0;

        List<Pos> change = new ArrayList<>();
        boolean rollback = false;

        while (board[x][y] != -1 && !(score >= 1 && x == start_x && y == start_y)) {

            if (score == 0) {
                if (visited[dir][x][y]) break;
                visited[dir][x][y] = true;
                change.add(new Pos(x,y));
            }

            if (change.size() >= 2 && score >= 1 && x == change.get(1).x && y == change.get(1).y) rollback = true;

            x = x + dxs[dir];
            y = y + dys[dir];

            if (1 <= board[x][y] && board[x][y] <= 5) {
                dir = change_dir(x, y, dir, board);
                score++;
            } else if (6 <= board[x][y] && board[x][y] <= 10) {
                //웜홀로 이동
                List<Pos> pos = wormhole[board[x][y] - 6];
                if (pos.get(0).x == x && pos.get(0).y == y) {
                    x = pos.get(1).x;
                    y = pos.get(1).y;
                } else {
                    x = pos.get(0).x;
                    y = pos.get(0).y;
                }
            }
        }

        if (!rollback) {
            for(int i=0; i<change.size(); i++) {
                visited[start_dir][change.get(i).x][change.get(i).y] = false;
            }
        }

        return score;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine().trim());
            int[][] board = new int[n + 2][n + 2];
            wormhole = new List[5];
            for (int i = 0; i < 5; i++) {
                wormhole[i] = new ArrayList<>();
            }

            visited = new boolean[4][n + 2][n + 2];

            for (int i = 0; i < n + 2; i++) {
                board[i][0] = 5;
                board[i][n + 1] = 5;
                board[0][i] = 5;
                board[n + 1][i] = 5;
            }

            for (int i = 1; i < n + 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 1; j < n + 1; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (6 <= board[i][j] && board[i][j] <= 10) {
                        wormhole[board[i][j] - 6].add(new Pos(i, j));
                    }
                }
            }

            int result = 0;

            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (board[i][j] == 0) {
                        for (int dir = 0; dir < 4; dir++) {
                            int score = play(i, j, dir, board);
                            if (result < score) {
                                result = score;
                            }
                        }
                    }
                }
            }

            sb.append('#').append(testCase).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }

}