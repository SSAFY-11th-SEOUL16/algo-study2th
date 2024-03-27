import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ20926_얼음미로 { // 936ms
    // 미로 크기
    static int W, H;
    // 결과
    static int[][] result;
    // 미로
    static String[][] maze;
    // 방향
    static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    // 테라 위치
    static int tx, ty;
    // 탈출 위치
    static int ex, ey;

    static class Info implements Comparable<Info> {
        int x;
        int y;
        int time;

        Info(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Info o) {
            // TODO Auto-generated method stub
            return this.time - o.time;
        }

        @Override
        public String toString() {
            return "Info [x=" + x + ", y=" + y + ", time=" + time + "]";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        maze = new String[H][W];
        result = new int[H][W];

        for (int i = 0; i < H; i++) {
            maze[i] = br.readLine().split("");
            for (int j = 0; j < W; j++) {
                if (maze[i][j].equals("T")) {
                    tx = i;
                    ty = j;
                    maze[i][j] = "0";
                } else if (maze[i][j].equals("E")) {
                    ex = i;
                    ey = j;
                }
            }
        }
        // result 전체 Integer.MAX_VALUE로 초기화
        for (int i = 0; i < H; i++)
            Arrays.fill(result[i], Integer.MAX_VALUE);

        getResult();
        // E 위치까지 도달하지 못했으면 -1, 아니면 갱신된 값 출력
        if (result[ex][ey] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result[ex][ey]);
        }
    }

    public static void getResult() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        // 시작 정보 pq에 넣고 돌린다. 
        pq.offer(new Info(tx, ty, 0));
        
        while (!pq.isEmpty()) {
            Info now = pq.poll();
            for (int i = 0; i < 4; i++) {
                int sum = now.time;
                // 현재 시간이 해당 위치의 result 보다 크면 탐색할 필요가 없음 
                if (sum >= result[ex][ey])
                    continue;
                // 가로방향
                if (i == 0 || i == 1) {
                    for (int j = 1; j < W; j++) {
                        int ny = now.y + j * dirs[i][1];
                        // 벗어나는 경우나 구멍인 경우 break
                        if (ny < 0 || ny >= W || maze[now.x][ny].equals("H"))
                            break;
                        // 출구인 경우 값 갱신해주고 break;
                        if (maze[now.x][ny].equals("E")) {
                            if (sum < result[ex][ey]) {
                                result[ex][ey] = sum;
                            }
                            break;
                        }
                        // 돌인 경우 이전까지 합을 정보로 pq에 넣어줌
                        else if (maze[now.x][ny].equals("R")) {
                        	// sum이 result보다 크면 갱신필요 없음, j가 1이면 바로 없에 돌이므로 필요없음
                            if (sum >= result[now.x][ny - dirs[i][1]] || j == 1) {
                                break;
                            }
                            // 작은 경우는 갱신하고 pq에 넣어줌
                            else {
                                result[now.x][ny - dirs[i][1]] = sum;
                                pq.offer(new Info(now.x, ny - dirs[i][1], sum));
                                break;
                            }
                         
                        }
                        // 평지인 경우 sum에 해당 미끌시간 값 더해줌
                        else {
                            sum += Integer.parseInt(maze[now.x][ny]);
                        }
                    }
                }
                // 세로방향 동일하게 진행
                else {
                    for (int j = 1; j < H; j++) {
                        int nx = now.x + j * dirs[i][0];
                        
                        if (nx < 0 || nx >= H || maze[nx][now.y].equals("H"))
                            break;
                        
                        if (maze[nx][now.y].equals("E")) {
                            if (sum < result[ex][ey]) {
                                result[ex][ey] = sum;
                            }
                            break;
                        }
                        
                        else if (maze[nx][now.y].equals("R")) {
                            if (sum >= result[nx - dirs[i][0]][now.y] || j == 1) {
                                break;
                            }
                            else {
                                result[nx - dirs[i][0]][now.y] = sum;
                                pq.offer(new Info(nx - dirs[i][0], now.y, sum));
                                break;
                            }
                        }
                        
                        else {
                            sum += Integer.parseInt(maze[nx][now.y]);
                        }
                    }
                }
            }
        }
    }
}