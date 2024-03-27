import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SW4014_활주로건설 {
    static int N, X;
    static int result;
    static int[][] graph;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            graph = new int[N][N];
            result = 0;
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            getResult();
             
            sb.append("#").append(tc).append(" ").append(result);
            if (tc < T)
                sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    public static void getResult() {
        for (int i = 0; i < N; i++) {
            boolean flag = true;
            boolean[] visited = new boolean[N];
            for (int j = 1; j < N; j++) {
                if (Math.abs(graph[i][j] - graph[i][j-1]) == 0) {
                    continue;
                }
                else if (Math.abs(graph[i][j] - graph[i][j-1]) == 1)  {
                    if (graph[i][j] - graph[i][j-1] == 1) {
                        for (int x = 1; x <= X; x++) {
                            if (j-x < 0) {
                                flag = false;
                                break;
                            }
                            if (graph[i][j-x] != graph[i][j]-1 || visited[j-x]) {
                                flag = false;
                                break;
                            }
                            visited[j-x] = true;
                        }
                    }
                    else if (graph[i][j-1] - graph[i][j] == 1) {
                        for (int x = 1; x <= X; x++) {
                            if (j-1+x >= N) {
                                flag = false;
                                break;
                            }   
                            if (graph[i][j-1+x] != graph[i][j-1]-1 || visited[j-1+x]) {
                                flag = false;
                                break;
                            }
                            visited[j-1+x] = true;
                        }
                    }
                }
                else {
                    flag = false;
                    break;
                }
                if (!flag) {
                    break;
                }
            }
            if (flag) {
//              System.out.println("row " + i + " " + result);
                result++;
            }
        }
         
         
        for (int i = 0; i < N; i++) {
            boolean flag = true;
            boolean[] visited = new boolean[N];
            for (int j = 1; j < N; j++) {
                if (Math.abs(graph[j][i] - graph[j-1][i]) == 0) {
                    continue;
                }
                else if (Math.abs(graph[j][i] - graph[j-1][i]) == 1)  {
                    if (graph[j][i] - graph[j-1][i] == 1) {
                        for (int x = 1; x <= X; x++) {
                            if (j-x < 0) {
                                flag = false;
                                break;
                            }
                            if (graph[j-x][i] != graph[j][i]-1 || visited[j-x]) {
                                flag = false;
                                break;
                            }
                            visited[j-x] = true;
                        }
                    }
                    else if (graph[j-1][i] - graph[j][i] == 1) {
                        for (int x = 1; x <= X; x++) {
                            if (j-1+x >= N) {
                                flag = false;
                                break;
                            }   
                            if (graph[j-1+x][i] != graph[j-1][i]-1 || visited[j-1+x]) {
                                flag = false;
                                break;
                            }
                            visited[j-1+x] = true;
                        }
                    }
                }
                else {
                    flag = false;
                    break;
                }
                if (!flag) {
                    break;
                }
            }
            if (flag) {
//              System.out.println("col " + i + " " + result);
                result++;
            }
        }
    }
}