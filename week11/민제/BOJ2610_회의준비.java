import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2610_회의준비 {
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[][] graph = new int[n+1][n+1];
        int INF = 1000001;
        for(int i=0; i<n+1; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        
        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
        
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
        
        boolean[] check = new boolean[n+1];
        List<Integer> results = new ArrayList<>();

        for(int i=1; i<=n; i++) {
            
            if (check[i]) continue;
            
            ArrayList<Integer> group = new ArrayList<>();
            for(int j=1; j<=n; j++) {
                if (graph[i][j] != INF) group.add(j);
            }
            
            int min_dist = INF;
            int select_person = i;
            
            for (Integer person : group) {
                check[person] = true;
                int person_maxdist = 0;
                for(int j=1; j<=n; j++) {
                    if (graph[person][j] == INF) continue;
                    if (person_maxdist < graph[person][j]) {
                        person_maxdist = graph[person][j];
                    }
                }

                if (min_dist > person_maxdist) {
                    min_dist = person_maxdist;
                    select_person = person;
                }
            }
            results.add(select_person);
        }

        Collections.sort(results);

        StringBuilder sb = new StringBuilder();

        sb.append(results.size()).append('\n');
        for (Integer result : results) {
            sb.append(result).append('\n');
        }
        System.out.println(sb);


    }

}