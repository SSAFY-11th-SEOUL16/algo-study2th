import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    static HashMap<Integer, ArrayList<Integer>> map;
    static int answer;
    static int r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        r = Integer.parseInt(br.readLine());
        map = new HashMap();
        answer = 0;

        // find root
        int root = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == -1)
                root = i;
        }

        if (root == r) {
            System.out.println(answer);
            return;
        }

        // set hasMap
        for (int i = 0; i < n; i++) {
            int parent = arr[i];
            if (map.containsKey(parent)) {
                map.get(parent).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList();
                list.add(i);
                map.put(parent, list);
            }
        }

        if (map.get(root).size() == 1 && map.get(root).get(0) == r) {
            System.out.println(1);
            return;
        }

        removeDfs(r);
        dfs(root);

        System.out.println(answer);

    }

    static void dfs(int index) {
        ArrayList<Integer> list = map.get(index);

        if (list == null) {
            answer++;
            return;
        }

        for (int child : list) {
            if (child != r)
                dfs(child);
            else if (list.size() == 1) {
                answer++;
            }
        }
    }

    static void removeDfs(int index) {
        ArrayList<Integer> list = map.get(index);
        map.remove(index);
        if (list != null) {
            for (int child : list) {
                removeDfs(child);
            }
        }
    }
}