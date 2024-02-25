import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
 
public class SW5678_원자소멸시뮬레이션 {
 
    static int[] dys = { 1, -1, 0, 0 };
    static int[] dxs = { 0, 0, -1, 1 };
    static int total_energy;
    static List<Atom> atoms;
    static int[][] map = new int[4001][4001];;
 
    static class Atom {
        int x, y, dir, k;
        public Atom(int x, int y, int dir, int k) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.k = k;
        }
 
        @Override
        public String toString() {
            return "Atom{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    ", k=" + k +
                    '}';
        }
    }
 
    static boolean in_range(int x, int y) {
        return 0 <= x && x <= 4000 && 0 <= y && y <= 4000;
    }
 
    static void move() {
 
        for (int i = atoms.size() - 1; i >= 0; i--) {
            Atom atom = atoms.get(i);
            int nx = atom.x + dxs[atom.dir];
            int ny = atom.y + dys[atom.dir];
            map[atom.x][atom.y] -= atom.k;
            if (in_range(nx, ny)) {
                atoms.get(i).x = nx;
                atoms.get(i).y = ny;
                map[nx][ny] += atom.k;
            } else {
                atoms.remove(i);
            }
        }
 
    }
 
    static void after() {
        for(int i=atoms.size()-1; i>=0; i--) {
            Atom atom = atoms.get(i);
            if (map[atom.x][atom.y] != atom.k) {
                total_energy += map[atom.x][atom.y];
                atoms.remove(i);
                map[atom.x][atom.y] = 0;
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
 
        for (int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            total_energy = 0;
            atoms = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) * 2) + 2000;
                int y = (Integer.parseInt(st.nextToken()) * 2) + 2000;
                int dir = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                map[x][y] = k;
                atoms.add(new Atom(x, y, dir, k));
            }
 
            int time = 4001;
 
            while (time --> 0) {
                move();
                after();
            }
 
            sb.append('#').append(testCase).append(' ').append(total_energy).append('\n');
        }
 
        System.out.println(sb.toString());
 
    }
 
}