import java.io.*;
import java.util.*;

// 1628ms

public class BOJ20056_마법사상어와파이어볼 {
    static class Fireball {
        // 파이어볼의 위치 (r, c), m : 질량, s : 속력, d : 방향
        int r, c, m, s, d;
        Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int N, M;
    static ArrayList<Fireball> fireballs;
    static int[][] arr, dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}}; // 0번 방향~7번 방향
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new int[N][N]; // 격자. 각 위치에 몇 개의 파이어볼이 존재하는 지 저장
        fireballs = new ArrayList<>(); // 모든 파이어볼의 정보를 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 행과 열의 번호가 1~N번이라 1씩 빼줌
            int r = Integer.parseInt(st.nextToken())-1, c = Integer.parseInt(st.nextToken())-1;
            fireballs.add(new Fireball(r, c, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            arr[r][c]++;
        }
        for (int k = 0; k < K; k++) {
            // 1번 과정 : 각 파이어볼의 d 방향으로 s만큼 이동
            for (Fireball fb : fireballs) move(fb, arr);
            // 2번 과정 : 같은 칸에 존재하는 파이어볼은 하나로 합쳐지고, 넷으로 나누어짐
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] >= 2) sum(i, j);
                }
            }
            // 3-4번 과정 : 질량이 0인 파이어볼은 소멸되어 없어짐
            remove();
        }
        int ans = 0;
        // fireballs에는 현재 격자에 존재하는 파이어볼만 있으므로 해당 파이어볼의 질량을 더해준다
        for (Fireball fb : fireballs) ans += fb.m;
        System.out.println(ans);
    }

    static void move(Fireball fb, int[][] arr) {
        // 파이어볼의 위치에서 d 방향, s의 속력으로 이동한 값
        int nr = fb.r+dir[fb.d][0]*fb.s, nc = fb.c+dir[fb.d][1]*fb.s;
        // 1번 행과 N번 행이 연결, 1번 열과 N번 열이 연결되어 있으므로 아래와 같이 처리한다
        if (nr < 0) {
            while (nr < 0) nr += N;
        }
        if (nc < 0) {
            while (nc < 0) nc += N;
        }
        nr %= N;
        nc %= N;
        // 격자 상 파이어볼의 이동, 정보 재설정
        arr[fb.r][fb.c]--;
        arr[nr][nc]++;
        fb.r = nr;
        fb.c = nc;
    }

    // 2-1 과정 : 같은 칸에 있는 파이어볼은 하나로 합쳐짐
    static void sum(int r, int c) {
        ArrayList<Fireball> al = new ArrayList<>();
        for (int i = 0; i < fireballs.size(); i++) {
            if (r == fireballs.get(i).r && c == fireballs.get(i).c) {
                al.add(fireballs.get(i));
            }
        }
        Fireball first = al.get(0);
        int[] dirArr = new int[al.size()]; // 합쳐지는 파이어볼의 방향에 따라 결정되는 것이 있으므로 저장한다
        dirArr[0] = al.get(0).d;
        for (int i = 1; i < al.size(); i++) {
            first.m += al.get(i).m;
            first.s += al.get(i).s;
            dirArr[i] = al.get(i).d;
            fireballs.remove(al.get(i));
        }
        separate(first, isAllOddOrEven(dirArr), al.size());
    }
    
    // 2-2 과정 : 4개의 파이어볼로 나누어지며, 나누어진 각 파이어볼의 m, s, d는 문제 참고
    static void separate(Fireball fb, boolean flag, int size) {
        int startDir = flag ? 0 : 1;
        for (int i = 0; i < 4; i++) {
            fireballs.add(new Fireball(fb.r, fb.c, fb.m/5, fb.s/size, startDir+i*2));
        }
        fireballs.remove(fb);
        arr[fb.r][fb.c] = 4;
    }

    // 3-4 과정 : 파이어볼의 질량이 0이라면 삭제, 격자에서 해당 파이어볼의 위치에서 개수를 1개 빼줌
    static void remove() {
        for (int i = 0; i < fireballs.size(); i++) {
            Fireball now = fireballs.get(i);
            if (now.m == 0) {
                arr[now.r][now.c]--;
                fireballs.remove(i--); // index가 당겨지기 때문에 i-- 해줘야 한다
            }
        }
    }

    // 2-3 과정 : 합쳐지는 파이어볼들의 방향이 모두 홀수거나 짝수인지 확인
    static boolean isAllOddOrEven(int[] arr) {
        int first = arr[0]%2;
        for (int i : arr) {
            if (i%2 != first) return false;
        }
        return true;
    }
}