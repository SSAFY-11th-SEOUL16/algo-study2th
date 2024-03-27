import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static int[][] abilities;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n =Integer.parseInt(br.readLine());
        abilities = new int[n][n];
        min = Integer.MAX_VALUE;
        for(int i=0; i<n;i++) 
            abilities[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(0, new ArrayList(), new ArrayList());
        System.out.println(min);
    }

    // 2^N
    static void dfs(int depth, ArrayList<Integer> list1, ArrayList<Integer> list2) {
        if(depth == n) {
            min = Math.min(min, Math.abs(getResult(list1) - getResult(list2)));
            return;
        }

        list1.add(depth);
        dfs(depth+1,list1,list2);
        list1.remove(list1.size()-1);

        list2.add(depth);
        dfs(depth+1,list1,list2);
        list2.remove(list2.size()-1);
    }

    // N(N-R)
    static int getResult(ArrayList<Integer> list) {
        int sum = 0;
        for(int i =0; i<list.size(); i++) {
            for(int j =i+1; j<list.size(); j++) {
                sum+= abilities[list.get(i)][list.get(j)] + abilities[list.get(j)][list.get(i)];
            }
        }
        return sum;
    }
}