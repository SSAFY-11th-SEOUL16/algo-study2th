import java.io.*;
import java.util.*;
 
public class Solution {
    static int[][] film;
    static int[] zeros;
    static int[] ones;
    static int D, W, K;
     
    static boolean checkCol(int idx) {
        int tmp = 1;
        if(K == 1) return true;
        for(int i = 1; i < D; i++) {
            if(film[i][idx] == film[i - 1][idx]) tmp ++;
            else tmp = 1;
            if(tmp >= K) return true;
        }
        return false;
    }
     
    static boolean isValid() {
        for(int i = 0; i < W; i++) {
            if(!checkCol(i)) return false;
        }
        return true;
    }
     
    static boolean combination(int depth, int N, int start, int visited) {
        if(depth == N) {
            return isValid();
        }
        for(int i = start; i < D; i++) {
            if((visited & 1<<i) == 0) {
                int[] tmp = film[i];
                film[i] = ones;
                boolean found = combination(depth + 1, N, i + 1, visited | 1<<i);
                if(found) return true;
                film[i] = zeros;
                found = combination(depth + 1, N, i + 1, visited | 1 << i);
                if(found) return true;
                film[i] = tmp;
            }
        }
        return false;
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(input.readLine());
        zeros = new int[20];
        ones = new int[20];
        Arrays.fill(ones, 1);
        StringTokenizer tokens;
        for(int t = 1; t <= T; t++) {
            tokens = new StringTokenizer(input.readLine());
            D = Integer.parseInt(tokens.nextToken()); 
            W = Integer.parseInt(tokens.nextToken());
            K = Integer.parseInt(tokens.nextToken());
            film = new int[D][W];
            for(int i = 0; i < D; i++) {
                tokens = new StringTokenizer(input.readLine());
                for(int j = 0; j < W; j++) {
                    film[i][j] = tokens.nextToken().charAt(0) - '0';
                }
            }
            int l;
            for(l = 0; l <= D; l++) {
                if(combination(0, l, 0, 0)) break;
            }
            output.write("#" + t + " " + l + "\n");
        }
        output.close();
    }
 
}