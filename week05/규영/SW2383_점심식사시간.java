import java.io.*;
import java.util.*;

// 137ms

public class SW2383_점심식사시간 {

    static class Person implements Comparable<Person> {
        int r, c, dist, num;
        public Person(int r, int c) {
            this.r = r;
            this.c = c;
        }
        @Override
        public int compareTo(Person o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    static int N, ans;
    static int[][] arr, stair;
    static ArrayList<Person> people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            people = new ArrayList<>();
            arr = new int[N][N];
            int idx = 0;
            stair = new int[2][3];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1) people.add(new Person(i, j));
                    else if (arr[i][j] != 0) {
                        stair[idx][0] = i;
                        stair[idx][1] = j;
                        stair[idx++][2] = arr[i][j];
                    }
                }
            }
            ans = Integer.MAX_VALUE;
            choice(0);
            sb.append('#').append(t).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
    }
    static void choice(int idx) {
        if (idx == people.size()) {
            simulate();
            return;
        }
        Person p = people.get(idx);
        p.dist = Math.abs(p.r-stair[0][0])+Math.abs(p.c-stair[0][1])+1;
        p.num = 0;
        choice(idx+1);
        p.dist = Math.abs(p.r-stair[1][0])+Math.abs(p.c-stair[1][1])+1;
        p.num = 1;
        choice(idx+1);
    }
    static void simulate() {
        int max = 0;
        for (int i = 0; i < 2; i++) {
            PriorityQueue<Person> pq = new PriorityQueue<>();
            int time[] = new int[100];
            for (Person p : people) {
                if (p.num == i) pq.add(p);
            }
            int end;
            while (!pq.isEmpty()) {
                Person now = pq.poll();
                int start = now.dist;
                end = start+stair[now.num][2];
                for (int j = start; j < end; j++) {
                    if (time[j] == 3) {
                        end++;
                        continue;
                    }
                    time[j]++;
                }
                max = Math.max(max, end);
            }
        }
        ans = Math.min(ans, max);
    }
}