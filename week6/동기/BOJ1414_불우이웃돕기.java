import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
 * 244ms
 */
public class Main {
    static int n;
    static int[] parents;
    static ArrayList<Info> edgeList = new ArrayList();
    ;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parents = new int[n + 1];
        int answer = 0;

        for (int start = 0; start < n; start++) {
            String[] arr = br.readLine().split("");
            for (int end = 0; end < n; end++) {
                if (start != end && arr[end].charAt(0) != '0') {
                    Info info = new Info(start + 1, end + 1, charToInt(arr[end].charAt(0)));
                    edgeList.add(info);
                } else if (start == end && arr[end].charAt(0) != '0') {
                    answer += charToInt(arr[end].charAt(0));
                }
            }
        }

        make();
        Collections.sort(edgeList, (o1, o2) -> {
            return o1.value - o2.value;
        });

        int cnt = 0;
        boolean isEnd = false;
        for (Info info : edgeList) {
            if (isEnd) {
                answer += info.value;
                continue;
            }

            if (union(info.start, info.end)) {
                cnt++;
            } else {
                answer += info.value;
            }

            if (cnt == n - 1)
                isEnd = true;
        }
        if (cnt != n - 1)
            answer = -1;

        System.out.println(answer);

    }

    static void make() {
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    static int find(int x) {
        if (x == parents[x])
            return x;

        return parents[x] = find(parents[x]);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y)
            return false;

        if (x < y) {
            parents[y] = x;
        } else {
            parents[x] = y;
        }

        return true;
    }

    static int charToInt(char c) {
        int cValue = c - 0;
        if (cValue > 96) {
            return cValue - 96;
        } else {
            return cValue - 38;
        }
    }

    static class Info {
        int start;
        int end;
        int value;

        Info(int s, int e, int v) {
            this.start = s;
            this.end = e;
            this.value = v;
        }

    }

}
