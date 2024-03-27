import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 *  1262ms
 */
public class Solution {
    // 북동남서
    static int[][] dirArr = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static Pos[][] wormholeArray = new Pos[5][2]; //웜홀 짝궁별로 위치저장
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            map = new int[n][n];
            wormholeArray = new Pos[5][2];

            for (int i = 0; i < n; i++) {
                int[] arr = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

                for (int j = 0; j < n; j++) {
                    if (arr[j] == -1) {
                    } else if (arr[j] > 5) {
                        int wormholeIndex = arr[j] - 6;

                        if (wormholeArray[wormholeIndex][0] == null)
                            wormholeArray[wormholeIndex][0] = new Pos(j, i);
                        else
                            wormholeArray[wormholeIndex][1] = new Pos(j, i);

                    }
                }
                map[i] = arr;
            }

            sb.append("#").append(tc).append(" ").append(simulation()).append("\n");
        }
        System.out.println(sb);
    }

    static int simulation() {
        int answer = 0;

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                if (map[k][j] == 0) {
                    for (int i = 0; i < 4; i++) { // 한 지점에서 갈 수 있는 네가지 방향 탐색
                        int x1 = j;
                        int y1 = k;
                        int dir = i;
                        int cnt = 0;
                        Loop1:
                        while (true) {
                            x1 = x1 + dirArr[dir][0];
                            y1 = y1 + dirArr[dir][1];

                            if (isWallCheck(x1, y1)) { // 벽인지 체크
                                int info = map[y1][x1];

                                if (info == -1 || (x1 == j && y1 == k)) { // 블랙홀 or 초기 위치
                                    answer = Math.max(answer, cnt);
                                    break Loop1;
                                } else if (info > 0 && info < 6) { // 블록
                                    dir = getDir(dir, info); // 블록 종류로 방향 변환
                                    cnt++;
                                } else if (info > 5) { // 웜홀
                                    int index = info - 6;
                                    if (wormholeArray[index][0].x == x1 && wormholeArray[index][0].y == y1) {
                                        x1 = wormholeArray[index][1].x;
                                        y1 = wormholeArray[index][1].y;
                                    } else {
                                        x1 = wormholeArray[index][0].x;
                                        y1 = wormholeArray[index][0].y;
                                    }
                                }
                            } else { //벽이므로 방향 반대와 점수 추가
                                dir = (dir + 2) % 4;
                                cnt++;
                            }

                        }
                    }
                }
            }
        }

        return answer;
    }

    static int getDir(int dir, int wall) {

        switch (wall) {
            case 1: {
                if (dir == 2)
                    dir = 1;
                else if (dir == 3)
                    dir = 0;
                else
                    dir = (dir + 2) % 4;
                break;
            }
            case 2: {
                if (dir == 0)
                    dir = 1;
                else if (dir == 3)
                    dir = 2;
                else
                    dir = (dir + 2) % 4;
                break;
            }
            case 3: {
                if (dir == 0)
                    dir = 3;
                else if (dir == 1)
                    dir = 2;
                else
                    dir = (dir + 2) % 4;
                break;
            }
            case 4: {
                if (dir == 2)
                    dir = 3;
                else if (dir == 1)
                    dir = 0;
                else
                    dir = (dir + 2) % 4;
                break;
            }
            case 5: {
                dir = (dir + 2) % 4;
                break;
            }
        }
        return dir;
    }

    static boolean isWallCheck(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
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
