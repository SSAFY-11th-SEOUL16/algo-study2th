import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[] parentArray;
    static int[] apples;
    static int n;
    static int k;
    static ArrayList<Info> infoList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        parentArray = new int[n];
        infoList = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            parentArray[c] = p;
        }
        apples = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(1, list, apples[0]);

        Collections.sort(infoList);
        for (Info info : infoList) {
            if (isAbleSubset(info)) {
                System.out.println(info.apple);
                break;
            }
        }
    }

    static void dfs(int depth, ArrayList<Integer> list, int appleCnt) {
        if (list.size() + n - depth < k) return;

        if (list.size() == k) {
            ArrayList<Integer> newList = new ArrayList<>();
            for (int i : list) newList.add(i);
            infoList.add(new Info(appleCnt, newList));
            return;
        }
        list.add(depth);
        dfs(depth + 1, list, appleCnt + apples[depth]);
        list.remove(list.size() - 1);
        dfs(depth + 1, list, appleCnt);
    }

    static boolean isAbleSubset(Info info) {
        ArrayList<Integer> list = info.list;
        boolean chk = true;
        for (int i = 1; i < list.size(); i++) {
            if (!list.contains(parentArray[list.get(i)])) {
                chk = false;
                break;
            }
        }
        return chk;
    }


    // 트리 부분집합과 얻을 수 있는 사과 수
    static class Info implements Comparable<Info> {
        int apple;
        ArrayList<Integer> list;

        Info(int apple, ArrayList<Integer> list) {
            this.apple = apple;
            this.list = list;
        }

        @Override
        public int compareTo(Info o) {
            return o.apple - this.apple;
        }
    }
}