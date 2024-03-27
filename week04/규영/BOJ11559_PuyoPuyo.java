import java.io.*;
import java.util.*;

public class BOJ11559_PuyoPuyo {
    static class Node {
        int r, c;
        Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static char[][] arr;
    static ArrayList<Node> puyos;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new char[12][6];
        for (int i = 0; i < 12; i++) arr[i] = br.readLine().toCharArray();
        int combo = 0;
        // 최대 콤보 수는 12*6/4 = 18
        // 루프 돌릴 조건이 생각이 안 나서 이렇게 했는데.. 다른 분들은 어떻게 하셨는지 보고 싶음!
        for (int t = 0; t < 18; t++) {
            moveDown();
            boolean flag = false; // 연쇄가 일어났는지 확인할 flag
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    // 뿌요임 && 연쇄 가능함 -> 터짐
                    if (arr[i][j] != '.' && canRemove(i, j, arr[i][j])) {
                        remove(puyos);
                        flag = true;
                    }
                }
            }
            if (flag) combo++; // 여러 그룹이 터지더라도 한 번의 연쇄만 추가
            else break; // 연쇄가 더 일어날 수 없다면 더 볼 필요가 없음
        }
        System.out.println(combo);
    }
    
    // 같은 색상의 뿌요가 4개 이상 상하좌우로 연결되어 있는지 확인
    static boolean canRemove(int r, int c, char puyo) {
        boolean[][] visit = new boolean[12][6];
        visit[r][c] = true;
        Queue<Node> q = new LinkedList<>();
        puyos = new ArrayList<>();
        q.offer(new Node(r, c));
        puyos.add(new Node(r, c));
        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.r+dir[d][0], nc = now.c+dir[d][1];
                if (nr < 0 || 12 <= nr || nc < 0 || 6 <= nc || visit[nr][nc] || arr[nr][nc] != puyo) continue;
                visit[nr][nc] = true;
                q.offer(new Node(nr, nc));
                puyos.add(new Node(nr, nc));
            }
        }
        return puyos.size() >= 4; // 4개 이상이면 없어질 수 있음
    }

    static void remove(ArrayList<Node> puyos) {
        for (Node puyo : puyos) arr[puyo.r][puyo.c] = '.';
    }

    // 뿌요의 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어트린다
    static void moveDown() {
        char[][] temp = new char[12][6];
        for (char[] cArr : temp) Arrays.fill(cArr, '.');
        for (int i = 0; i < 6; i++) {
            int idxR = 11;
            for (int j = 11; j >= 0; j--) {
                if (arr[j][i] != '.') {
                    temp[idxR--][i] = arr[j][i];
                }
            }
        }
        arr = temp;
    }
}