import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 832ms
 */
public class Main {
    static int n, m;
    static ArrayList<Integer>[] edgeList;
    static int[] relation;
    static final int FRIEND = 1;
    static final int ENEMY = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        relation = new int[n +1];
        Arrays.fill(relation,-1);
        edgeList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) edgeList[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edgeList[a].add(b);
            edgeList[b].add(a);
        }
        int answer = 1;

        for(int i=1; i<=n; i++) {
            if(relation[i] != -1) continue;
            if(!dfs(i,1)){
                answer =0;
                break;
            }
        }
        System.out.println(answer);
    }

    static boolean dfs(int index, int team) {
        if(relation[index] != -1) {
            return relation[index] == team;
        }

        relation[index] = team;
        for(int next : edgeList[index]){
            if(!dfs(next, 1-team)) return false;
        }
        return  true;
    }

}
