import java.io.*;
import java.util.*;

public class SW5650_핀볼게임 {
    static class Node {
        int r, c, d;
        Node (int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    static int N, ans, score, sr, sc;
    static ArrayList<Node> wormholes;
    static int[][] arr, dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            arr = new int[N][N];
            wormholes = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    arr[i][j] = num;
                    if (6 <= num && num <= 10) {
                        wormholes.add(new Node(i, j, num));
                    }
                }
            }
            ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] != 0) continue;
                    for (int d = 0; d < 4; d++) {
                        score = 0;
                        sr = i;
                        sc = j;
                        play(new Node(sr, sc, d));
                        ans = Math.max(ans, score);
                    }
                }
            }
            sb.append('#').append(t).append(' ').append(ans).append('\n');
        }
        System.out.print(sb);
    }

    static void play(Node n) {
        while (true) {
            n.r += dir[n.d][0];
            n.c += dir[n.d][1];
            int now;
            if (!isInRange(n.r, n.c) || (now = arr[n.r][n.c]) == 5) {
                score = score*2+1;
                break;
            }
            if ((n.r == sr && n.c == sc) || now == -1) break;
            if (6 <= now) wormhole(n, now);
            else if (1 <= now) {
                if (now == 1) {
                    if (n.d == 2) n.d = 1;
                    else if (n.d == 3) n.d = 0;
                    else n.d = (n.d+2)%4;
                }
                else if (now == 2) {
                    if (n.d == 0) n.d = 1;
                    else if (n.d == 3) n.d = 2;
                    else n.d = (n.d+2)%4;
                }
                else if (now == 3) {
                    if (n.d == 1) n.d = 2;
                    else if (n.d == 0) n.d = 3;
                    else n.d = (n.d+2)%4;
                }
                else if (now == 4) {
                    if (n.d == 2) n.d = 3;
                    else if (n.d == 1) n.d = 0;
                    else n.d = (n.d+2)%4;
                }
                score++;
            }
        }
    }

    static void wormhole(Node n, int num) {
        for (Node wormhole : wormholes) {
            if (wormhole.d == num && (wormhole.r != n.r || wormhole.c != n.c)) {
                n.r = wormhole.r;
                n.c = wormhole.c;
                break;
            }
        }
    }

    static boolean isInRange(int nr, int nc) {
        return 0 <= nr && nr < N && 0 <= nc && nc < N;
    }
}