import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1068_트리 {
    static int N;
    static List<List<Integer>> trees;

    private static int countLeaf(int root, int rNode) {
        if(root == rNode) return 0;
        if(trees.get(root).size() == 0) return 1;

        int cnt = 0;
        for(int child : trees.get(root)) {
            cnt += countLeaf(child, rNode);
        }
        return cnt == 0 ? 1 : cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        trees = new ArrayList<>();
        for(int i = 0; i < N; i++) trees.add(new ArrayList<>());

        int root = -1;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if(p == -1) root = i;
            else trees.get(p).add(i);
        }

        st = new StringTokenizer(br.readLine());
        int rNode = Integer.parseInt(st.nextToken());

        System.out.println(countLeaf(root, rNode));
    }
}
