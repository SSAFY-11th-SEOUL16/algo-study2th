import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {

    static int N;
    static boolean[] visited;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[(N*100000) +1];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dfs(0, new ArrayList<Integer>());
        for(int i=1; i<visited.length; i++) {
            if(!visited[i]) {
                System.out.println(i);
                break;
            }
        }
    }

    static void dfs(int depth, ArrayList<Integer> list) {
        if(depth == N) {
            int sum = list.stream().mapToInt(Integer::intValue).sum();
            visited[sum] = true;
            return;
        }

        dfs(depth+1,list);
        list.add(arr[depth]);
        dfs(depth+1, list);
        list.remove(list.size()-1);
    }
}