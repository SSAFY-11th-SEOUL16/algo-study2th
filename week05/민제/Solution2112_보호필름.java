import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution2112_보호필름 {
 
    static int d, w, k;
    static int[][] graph;
    static int result;
    static int[] select;
 
    static boolean check() {
 
        for (int j = 0; j < w; j++) {
 
            int continuous_0 = 0;
            int continuous_1 = 0;
            boolean temp_result = false;
 
            for (int i = 0; i < d; i++) {
                if (graph[i][j] == 0) {
                    continuous_0++;
                    continuous_1 = 0;
                } else {
                    continuous_1++;
                    continuous_0 = 0;
                }
                if (continuous_0 >= k || continuous_1 >= k)
                    temp_result = true;
            }
             
            if (!temp_result)
                return false;
        }
        return true;
    }
 
    static void comb(int depth, int start, int count) {
 
        if (result != -1) return;
 
        if (depth == count) {
            if (check()) result = count;
            return;
        }
 
        for (int i = start; i < d; i++) {
            select[depth] = i;
             
            int[] before = new int[w];
            for(int j=0; j<w; j++) {
                before[j] = graph[i][j];
            }
             
            //A로 바꾸기 (0)
             
            for(int j=0; j<w; j++) {
                graph[i][j] = 0;
            }
             
            comb(depth + 1, i + 1, count);
             
            //B로 바꾸기 (1)
             
            for(int j=0; j<w; j++) {
                graph[i][j] = 1;
            }
             
            comb(depth + 1, i + 1, count);
             
            //선택한줄 색 바꾼거 되돌리기
             
            for(int j=0; j<w; j++) {
                graph[i][j] = before[j];
            }
             
        }
 
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        StringBuilder sb = new StringBuilder();
         
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            graph = new int[d][w];
            result = -1;
             
            for(int i=0; i<d; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<w; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            for (int i = 0; i <= d; i++) {
                select = new int[i];
                comb(0, 0, i);
                if (result != -1)
                    break;
            }
             
            sb.append('#').append(testCase).append(' ').append(result).append('\n');
        }
         
        System.out.println(sb);
 
    }
 
}