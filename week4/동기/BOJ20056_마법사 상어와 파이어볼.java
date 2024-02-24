import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 언어 : Java8 / 512ms
 */

public class Main {

    static int[][] dir = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};

    static int n;
    static int m;
    static int k;

    static HashSet<Integer> checkMapList = new HashSet(); //파이어볼 위치 갱신할때마다 완탐할 수는 없으니까 갱신할 위치만 저장
    static ArrayList<FireBall> fbList = new ArrayList(); // 현재 존재하는 모든 파이어볼들의 리스트
    static ArrayList<FireBall>[][] map; // 맵의 위치에 따라 존재하는 파이어볼들의 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<FireBall>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fbList.add(new FireBall(x, y, m, s, d));
        }

        for (int i = 0; i < k; i++)
            simulation();

        int sum = 0;
        for (FireBall fb : fbList) {
            sum += fb.m;
        }
        System.out.println(sum);
    }

    static void simulation() {
        for (FireBall fb : fbList) {
            fb.x = convertPos(fb.x + (dir[fb.d][0] * fb.s));
            fb.y = convertPos(fb.y + (dir[fb.d][1] * fb.s));
            map[fb.y][fb.x].add(fb);
            checkMapList.add(posToInt(fb.x, fb.y));
        }
        fbList.clear();

        for (int posi : checkMapList) {
            Pos pos = intToPos(posi);
            ArrayList<FireBall> list = map[pos.y][pos.x];
            if (list.size() == 1) {
                fbList.add(list.get(0));
            } else if (list.size() >= 2) { // 해당 위치에 파이어볼이 두개 이상이면 결합과 분해 과정 진행
                int mSum = 0; // 무게 합
                int sSum = 0; // 속도 합
                ArrayList<Integer> dirList = new ArrayList<Integer>(); // 방향 담는 리스트

                for (FireBall fb : list) {
                    mSum += fb.m;
                    sSum += fb.s;
                    dirList.add(fb.d);
                }
                mSum = (int) Math.floor((double) mSum / 5);
                sSum = (int) Math.floor((double) sSum / list.size());
                int addDir = isOnlyOddOrEven(dirList);

                if (mSum > 0) { // 무게 0보다 클때만 파이어볼 생성
                    for (int i = 0; i < 4; i++)
                        fbList.add(new FireBall(pos.x, pos.y, mSum, sSum, (i * 2) + addDir));
                }

            }
            map[pos.y][pos.x].clear();
        }
        checkMapList.clear();
    }

    // 오직 홀수거나 짝수인 경우를 판단하는 함수
    static int isOnlyOddOrEven(ArrayList<Integer> dirList) {
        boolean isEven = dirList.get(0) % 2 == 0;
        for (int dir : dirList) {
            if ((dir % 2 == 0) ^ isEven)
                return 1;
        }
        return 0;
    }

    // 맵 바깥으로 나갈 경우 반대로 돌아야하므로 만든 위치 변경 함수
    static int convertPos(int x) {
        x %= n;
        if (x < 0)
            x += n;
        return x;
    }

    static int posToInt(int x, int y) {
        return (x * n) + y;
    }

    static Pos intToPos(int pos) {
        return new Pos(pos / n, pos % n);
    }

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class FireBall {
        int x;
        int y;
        int m;
        int s;
        int d;

        FireBall(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

}