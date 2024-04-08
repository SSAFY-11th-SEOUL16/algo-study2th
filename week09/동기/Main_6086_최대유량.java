import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
 * 88ms
 * 참고 설명 및 코드 https://loosie.tistory.com/632
 */
public class Main {
    static int n;
    static int[][] flow = new int[52][52];
    static int[][] capacity = new int[52][52];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = atoi(st.nextToken().charAt(0));
            int b = atoi(st.nextToken().charAt(0));
            int cost = Integer.parseInt(st.nextToken());

            capacity[a][b] += cost;
            capacity[b][a] += cost;
        }
        bfs();
    }

    static void bfs() {
        int answer = 0;
        int[] parent = new int[52];
        while (true){

            Arrays.fill(parent,-1);
            Queue<Integer> queue = new LinkedList<>();

            parent[0] = 0;
            queue.add(0);

            while (!queue.isEmpty() && parent[25] == -1) {
                int cur = queue.poll();
                for(int next =0; next<52; next++) {
                    if(capacity[cur][next] - flow[cur][next] > 0 && parent[next] == -1){
                        queue.add(next);
                        parent[next] = cur;
                    }
                }
            }
            if(parent[25] == -1) break;
            int amount = Integer.MAX_VALUE;
            for(int i=25; i>0; i=parent[i]) {
                amount = Math.min(capacity[parent[i]][i] - flow[parent[i]][i], amount);
            }

            for(int i=25; i>0; i=parent[i]) {
                flow[parent[i]][i] += amount;
                flow[i][parent[i]] -= amount;
            }

            answer += amount;

        }
        System.out.println(answer);
    }

    static int atoi(char c) {
        if(c<91){ //대문자
            return c - 65;
        }else{ //소문자
            return c - 71;
        }
    }

}
