import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution2383_점심식사시간 {
 
    static int[][] graph;
 
    static Point[] stairs;
    static List<Point> people;
    static int result;
 
    static class Point {
        int x, y;
 
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
 
        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }
    }
 
    static int getDist(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
 
    static int goLunch(List<Point> group, Point stair) {
 
        int time = 0;
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> dists = new ArrayList<>();
        for (int i = 0; i < group.size(); i++) {
            dists.add(getDist(group.get(i), stair));
        }
        Collections.sort(dists);
 
        while (!dists.isEmpty() || !q.isEmpty()) {
            time++;
 
            // 계단 탈출
            while (!q.isEmpty()) {
                if (q.peek() + graph[stair.x][stair.y] <= time)
                    q.poll();
                else
                    break;
            }
 
            // dists가 남아있을때
            // 들어갈 자리 있을때
            while (!dists.isEmpty() && q.size() < 3) {
                if (dists.get(0) <= time) {
                    q.add(time);
                    dists.remove(0);
                } else
                    break;
            }
 
        }
 
        return time + 1;
    }
 
    static void subset(int depth, List<Point> group1, List<Point> group2) {
 
        if (depth == people.size()) {
 
            int group1_time = goLunch(group1, stairs[0]);
            int group2_time = goLunch(group2, stairs[1]);
            int now_time = Math.max(group1_time, group2_time);
 
            result = Math.min(result, now_time);
            return;
        }
 
        // group1에 추가
        group1.add(people.get(depth));
        subset(depth + 1, group1, group2);
        group1.remove(group1.size() - 1);
 
        // group2에 추가
        group2.add(people.get(depth));
        subset(depth + 1, group1, group2);
        group2.remove(group2.size() - 1);
 
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        StringBuilder sb = new StringBuilder();
 
        for (int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            graph = new int[n][n];
            stairs = new Point[2];
            people = new ArrayList<>();
            int stair_idx = 0;
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    if (graph[i][j] == 1)
                        people.add(new Point(i, j));
                    if (graph[i][j] >= 2)
                        stairs[stair_idx++] = new Point(i, j);
                }
            }
            result = Integer.MAX_VALUE;
 
            subset(0, new ArrayList<Point>(), new ArrayList<Point>());
 
            sb.append('#').append(testCase).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }
 
}