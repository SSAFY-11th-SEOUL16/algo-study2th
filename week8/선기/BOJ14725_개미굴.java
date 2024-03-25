import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n;
    static Room entryPoint;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        entryPoint = new Room("", new TreeMap<>());
        for (int i = 0; i < n; ++i) {
            int k = nextInt();
            Room pre = entryPoint;
            for (int j = 0; j < k; ++j) {
                String nextFood = next();
                pre.adj.putIfAbsent(nextFood, new Room(nextFood, new TreeMap<>()));
                Room post = pre.adj.get(nextFood);
                pre = post;
            }
        }
        for (Room next : entryPoint.adj.values()) {
            dfs(next, 0);
        }
        System.out.println(sb);
    }

    static class Room implements Comparable<Room> {
        String food;
        TreeMap<String, Room> adj;

        Room(String food) {
            this.food = food;
        }

        Room(String food, TreeMap<String, Room> adj) {
            this.food = food;
            this.adj = adj;
        }

        @Override
        public int compareTo(Room o) {
            return food.compareTo(o.food);
        }
    }

    static void dfs(Room room, int depth) {
        for (int i = 0; i < depth; ++i) {
            sb.append("--");
        }
        sb.append(room.food);
        sb.append("\n");
        for (Room next : room.adj.values()) {
            dfs(next, depth + 1);
        }
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
