import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 2352ms
 */
public class Main {

    static int dp[][]; // 0 = 어답터 1 = 일반인
    static int depth[];
    static ArrayList<Integer>[] friendList;
    static ArrayList<Integer>[] depthList;
    static boolean[] visited;
    static int curDepth;
    static int root;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2];
        depth = new int[n + 1];
        friendList = new ArrayList[n + 1];
        depthList = new ArrayList[n+1];
        visited = new boolean[n+1];
        curDepth = 1;
        root=0;
        for (int i = 0; i <= n; i++) {
            friendList[i] = new ArrayList<Integer>();
            depthList[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            if(i==1){
                root = parent;
                depth[parent] = 1;
                depthList[1].add(parent);
            }

            friendList[child].add(parent);
            friendList[parent].add(child);
        }
        set();
        solution();
        System.out.println(Math.min(dp[root][0], dp[root][1]));

    }

    static void solution() {
        curDepth--;
        while (curDepth > 0) {
            for (int parent : depthList[curDepth]) {
                for (int child : friendList[parent]) {
                    if(depth[child] > curDepth){
                        dp[parent][0] += Math.min(dp[child][0], dp[child][1]);
                        dp[parent][1] += dp[child][0];
                    }
                }
            }
            curDepth--;
        }
    }

    static void set() {
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][1] = 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        visited[root] = true;
        while(!queue.isEmpty()) {
            int parent = queue.poll();
            curDepth = Math.max(curDepth, depth[parent]);
            for(int child : friendList[parent]){
                if(!visited[child]){
                    depth[child] = depth[parent]+1;
                    depthList[depth[child]].add(child);
                    queue.add(child);
                    visited[child] =true;
                }
            }
        }
    }

}