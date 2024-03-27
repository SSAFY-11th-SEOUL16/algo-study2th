import java.io.*;
import java.util.*;
 
public class Solution {
    static int[][] graph;
    static int N, X;
     
    static boolean checkRow(int idx) {
        int flatCount = 1;
        for(int i = 1; i < N; i++) {
            int tmp = graph[idx][i] - graph[idx][i - 1];
            if(tmp == 0) flatCount++;
            else if(tmp > 1 || tmp < -1) return false;
            else if(tmp == 1) {
                if(flatCount < X) return false;
                flatCount = 1;
            } else {
                for(int j = i + 1; j < i + X; j++) {
                    if(j >= N) return false;
                    if(graph[idx][j] != graph[idx][j - 1]) return false;
                }
                i += X - 1;
                flatCount = 0;
            }
        }
         
        return true;
    }
     
    static boolean checkCol(int idx) {
        int flatCount = 1;
        for(int i = 1; i < N; i++) {
            int tmp = graph[i][idx] - graph[i - 1][idx];
            if(tmp == 0) flatCount++;
            else if(tmp > 1 || tmp < -1) return false;
            else if(tmp == 1) {
                if(flatCount < X) return false;
                flatCount = 1;
            } else {
                for(int j = i + 1; j < i + X; j++) {
                    if(j >= N) return false;
                    if(graph[j][idx] != graph[j - 1][idx]) return false;
                }
                i += X - 1;
                flatCount = 0;
            }
        }
         
        return true;
    }
     
    public static void main(String args[]) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
         
        int T = Integer.parseInt(input.readLine());
        StringTokenizer tokens;
        for(int t = 1; t <= T; t++) {
            tokens = new StringTokenizer(input.readLine());
            int answer = 0;
            N = Integer.parseInt(tokens.nextToken());
            X = Integer.parseInt(tokens.nextToken());
            graph = new int[N][N];
            for(int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine());
                for(int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }
             
            for(int i = 0; i < N; i++) {
                if(checkRow(i)) answer++;
                if(checkCol(i)) answer++;
            }
            output.write("#"+t+" "+answer+"\n");
        }
        output.close();
    }
}