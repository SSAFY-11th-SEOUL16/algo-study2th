package 규영;

import java.io.*;
import java.util.*;

public class BOJ1068_트리 {
    static int totalCnt, targetCnt;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> al;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), root = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        al = new ArrayList<>();
        for (int i = 0; i < N; i++) al.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == -1) {
                root = i; // 루트가 0번이 아닐 수 있으므로 루트의 번호를 저장
                continue;
            }
            al.get(num).add(i); // 부모 아래에 현재 번호를 저장
        }
        int target = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        totalCnt = 0;
        targetCnt = 0;
        dfs(root, true); // 루트부터의 리프 노드 개수를 구하는 과정
        dfs(target, false); // target부터의 리프 노드 개수를 구하는 과정
        // target을 지운 후 target의 부모가 리프 노드가 될 수 있으므로 아래 과정 진행
        for (int i = 0; i < N; i++) {
            if (al.get(i).contains(target) && al.get(i).size() == 1) {
                targetCnt--;
                break;
            }
        }
        System.out.println(totalCnt-targetCnt);
    }
    static void dfs(int num, boolean flag) {
        if (al.get(num).isEmpty()) {
            if (flag) totalCnt++;
            else targetCnt++;
            return;
        }
        for (int i : al.get(num)) {
            dfs(i, flag);
        }
    }
}