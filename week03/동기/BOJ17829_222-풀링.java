import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(dc(0, 0, n));
    }

    static int dc(int x, int y, int depth) {
        if (depth == 1)
            return map[y][x];

        ArrayList<Integer> list = new ArrayList();
        int q1 = dc(x, y, depth / 2);
        int q2 = dc(x + (depth / 2), y, depth / 2);
        int q3 = dc(x, y + (depth / 2), depth / 2);
        int q4 = dc(x + (depth / 2), y + (depth / 2), depth / 2);

        list.add(q1);
        list.add(q2);
        list.add(q3);
        list.add(q4);
        Collections.sort(list);
        return list.get(2);
    }
}
// 언어 : Java8 / 시간 : 1464 ms