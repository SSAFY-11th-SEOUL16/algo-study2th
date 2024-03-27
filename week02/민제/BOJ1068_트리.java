import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1068_트리 {

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nodes = new int[n];
        List<Integer>[] childs = new List[n];
        for (int i = 0; i < n; i++) {
            childs[i] = new ArrayList<>();
        }
        int root = 0;

        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(st.nextToken());
            nodes[i] = input;
            if (input == -1) {
                root = i;
                continue;
            }
            childs[input].add(i);
        }

        int deleteIndex = Integer.parseInt(br.readLine());

        boolean[] check = new boolean[n];
        check[deleteIndex] = true;

        Queue<Integer> q = new LinkedList<>();
        q.add(deleteIndex);

        while (!q.isEmpty()) {
            Integer poll = q.poll();

            check[poll] = true;
            for (Integer i : childs[poll]) {
                q.add(i);
            }
        }

        int leafs = 0;

        q.add(root);

        while (!q.isEmpty()) {
            Integer poll = q.poll();

            //리프 노드는 자식이 없음
            if (!check[poll] && childs[poll].size() == 0) {
                leafs++;
                continue;
            }

            //자식이 하나인데 버려서 리프노드가 될때
            if (childs[poll].size() == 1 && childs[poll].get(0) == deleteIndex) {
                leafs++;
                continue;
            }

            for (Integer i : childs[poll]) {
                if (!check[i]) q.add(i);
            }
        }

        System.out.println(leafs);

    }
}
