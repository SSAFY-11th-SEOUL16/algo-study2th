import java.io.*;
import java.util.*;

// 13744KB, 140ms

public class BOJ14725_개미굴 {
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        TreeMap<String, TreeMap> map = new TreeMap<>();
        while (N-->0) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            TreeMap now = map;
            while (K-->0) {
                String str = st.nextToken();
                now.put(str, now.getOrDefault(str, new TreeMap<>()));
                now = (TreeMap)now.get(str);
            }
        }
        build(map, 0);
        System.out.print(sb);
    }
    static void build(TreeMap map, int depth) {
        for (Object o : map.keySet()) {
            for (int d = 0; d < depth; d++) sb.append("--");
            sb.append(o).append('\n');
            build((TreeMap)map.get(o), depth+1);
        }
    }
}