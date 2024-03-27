import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 언어 : Java / 시간 : 788ms
 */
public class Main {

    static int[][] dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    static int n;
    static int m;
    static int[][] map;
    static int allIcebergCnt = 0;

    // 현재 존재하는 빙하만을 저장
    static ArrayList<Pos> posList = new ArrayList();
    ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < m; j++) {
                if (arr[j] != 0) {
                    posList.add(new Pos(j, i));
                    allIcebergCnt++;
                }

            }
            map[i] = arr;
        }

        int t = 0;
        while (true) {
            simulation();
            t++;
            if (posList.size() == 0) { // 빙하를 다 녹일때까지 찾지 못했다면 0
                t = 0;
                break;
            }
            if (!bfs(posList.get(0))) { // 두덩이로 나뉘어졌다면 탈출
                break;
            }
        }

        System.out.println(t);
    }

    static void simulation() {
        // 이미 존재하는 map으로 최신화할 경우, 다른 결과가 나오므로 newMap 배열로 갱신하고 모든 작업 후, map에 매핑
        int[][] newMap = new int[n][m];
        ArrayList<Pos> newPosList = new ArrayList();

        for (int i = 0; i < n; i++) {
            newMap[i] = map[i].clone();
        }

        for (Pos pos : posList) {
            int removeCnt = 0;
            for (int[] d : dir) {
                int x1 = pos.x + d[0];
                int y1 = pos.y + d[1];
                if (isNullCheck(x1, y1) && map[y1][x1] == 0) {
                    removeCnt++;
                }
            }

            int curBingCnt = newMap[pos.y][pos.x];

            // 빙하가 아예 녹았다면 전체빙하수 줄여주기
            if (curBingCnt - removeCnt <= 0) {
                newMap[pos.y][pos.x] = 0;
                allIcebergCnt--;
            } else {
                newMap[pos.y][pos.x] -= removeCnt;
                newPosList.add(pos);
            }
        }

        for (Pos pos : posList) {
            map[pos.y][pos.x] = newMap[pos.y][pos.x];
        }
        posList = newPosList;
    }

    // start 지점에서부터 너비우선탐색을 통해 붙어있는 빙하의 개수를 카운트하고, 전체 빙하개수랑 같다면 true, 다르면 false
    static boolean bfs(Pos pos) {
        Queue<Pos> queue = new LinkedList();
        boolean[][] visited = new boolean[n][m];
        visited[pos.y][pos.x] = true;
        queue.add(pos);

        int curBingCnt = 0;

        while (!queue.isEmpty()) {
            Pos p1 = queue.poll();
            curBingCnt++;
            for (int[] d : dir) {
                int x1 = p1.x + d[0];
                int y1 = p1.y + d[1];

                if (isNullCheck(x1, y1) && !visited[y1][x1] && map[y1][x1] != 0) {
                    visited[y1][x1] = true;
                    queue.add(new Pos(x1, y1));
                }
            }
        }

        return curBingCnt == allIcebergCnt;
    }

    static boolean isNullCheck(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}