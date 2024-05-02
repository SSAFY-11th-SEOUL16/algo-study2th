import java.util.*;
import java.io.*;

class Main {
    static String[][] map;
    static int n, m, t;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new String[n][m];
        
        for (int i = 0; i < n; i++) 
            map[i] = br.readLine().trim().split(" ");
        
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(0, 0, 0));
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        boolean isAble = false;
        Pos gram = null;
        int answer = 10001;
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();
            if(pos.x == m-1 && pos.y == n-1){
                answer = pos.depth;
                isAble = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int x1 = pos.x + dx[i];
                int y1 = pos.y + dy[i];
                if(isNullCheck(x1,y1) && !visited[y1][x1] && !map[y1][x1].equals("1")){
                    if(map[y1][x1].equals("2")){
                        gram = new Pos(x1,y1, pos.depth+1);
                    }
                    queue.add(new Pos(x1,y1, pos.depth+1));
                    visited[y1][x1] = true;
                }
            }
        }

        if (gram != null) {
            int withGram = m + n - 2 - gram.x - gram.y + gram.depth;
            answer = isAble ? Math.min(answer, withGram) : withGram;
        }
        if(answer > t)
            System.out.println("Fail");
        else
            System.out.println(answer);

    }

    static boolean isNullCheck(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }

    static class Pos {
        int x;
        int y;
        int depth;

        Pos(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}