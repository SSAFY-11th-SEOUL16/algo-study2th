import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * 256ms
 */
public class Main {
    static int[] arr;
    static int n;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        permutation(0,new ArrayList<Integer>(),new boolean[n]);
        System.out.println(max);
    }

    static void permutation(int depth, ArrayList<Integer> list, boolean[] visited) {
        if(depth == n){
            max = Math.max(max, solution(list));
            return;
        }

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                list.add(i);
                visited[i] = true;
                permutation(depth+1,list,visited);
                list.remove(list.size()-1);
                visited[i] = false;
            }
        }
    }

    static int solution(ArrayList<Integer> order) {
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        int cnt =0;
        int percent = 0;
        for(int i=0; i< order.size()-1; i++) {
            percent += arr[order.get(i)];
            set.add(percent);
            if(set.contains(percent -50))
                cnt++;
        }
        return cnt;
    }
}
