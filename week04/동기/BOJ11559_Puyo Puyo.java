import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 언어 : JAVA / 시간 92ms
 */

public class Main {

    static int[][] dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    static int n = 12;
    static int m = 6;
    static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new String[n][m];

        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split("");
            map[i] = arr;
        }

        int t = 0;
        while (true) {
            if (simulation()) {
                t++;
            } else {
                break;
            }
        }

        System.out.println(t);
    }

    // 연쇄되면 true 반환
    static boolean simulation() {
        boolean isPyou = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!map[i][j].equals(".")) {
                    if (bfs(new Pos(j, i))) {
                        isPyou = true;
                    }
                }
            }
        }
        if (isPyou) {
            for (int i = 0; i < m; i++)
                sort(i);

            return true;
        }
        return false;

    }

    // 연쇄 작용 후 공중에 떠 있는 뿌요들 아래로 정렬
    static void sort(int x) {
        ArrayList<String> sortList = new ArrayList();
        for (int i = n - 1; i >= 0; i--) {
            if (!map[i][x].equals("."))
                sortList.add(map[i][x]);

            map[i][x] = ".";
        }

        int sortListIndex = 0;
        for (int i = n - 1; i >= n - sortList.size(); i--) {
            map[i][x] = sortList.get(sortListIndex++);
        }
    }

    // bfs를 통해 시작 색과 붙어있는 같은 색들을 찾아 연쇄가능여부에 따라 처리
    static boolean bfs(Pos pos) {
        Queue<Pos> queue = new LinkedList();
        ArrayList<Pos> removeList = new ArrayList();
        boolean[][] visited = new boolean[n][m];
        visited[pos.y][pos.x] = true;
        queue.add(pos);

        String color = map[pos.y][pos.x];

        while (!queue.isEmpty()) {
            Pos p1 = queue.poll();
            removeList.add(p1);
            for (int[] d : dir) {
                int x1 = p1.x + d[0];
                int y1 = p1.y + d[1];

                if (isNullCheck(x1, y1) && !visited[y1][x1] && map[y1][x1].equals(color)) {
                    visited[y1][x1] = true;
                    queue.add(new Pos(x1, y1));
                }
            }
        }

        // 붙어있는 같은 색이 4 이상일 때만 연쇄작용
        if (removeList.size() >= 4) {
            for (Pos rPos : removeList) {
                map[rPos.y][rPos.x] = ".";
            }
            return true;
        }

        return false;
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