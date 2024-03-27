import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static HashMap<Character, Node> map;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        answer = 0;

        for (int i = 0; i < n; i++)
            map.put((char) (65 + i), new Node((char) (65 + i)));


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char child = st.nextToken().charAt(0);

            map.get(parent).cAdd(child);
            map.get(child).pAdd(parent);
        }

        ArrayList<Character> rootList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Node node = map.get((char) (65 + i));
            if (node.parentList.size() == 0 && node.childList.size() > 0)
                rootList.add((char) (65 + i));
        }

        st = new StringTokenizer(br.readLine());
        int removeCnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < removeCnt; i++) {
            char remove = st.nextToken().charAt(0);
            for (char rParent : map.get(remove).parentList)
                map.get(rParent).childList.remove((Character) remove);

            map.get(remove).childList.clear();
        }
        for (char root : rootList) {
            for (char child : map.get(root).childList)
                dfs(child);
        }
        System.out.println(answer);

    }

    static void dfs(char k) {
        answer++;
        for (char child : map.get(k).childList) {
            dfs(child);
        }
    }

    static class Node {
        Character id;
        ArrayList<Character> parentList;
        ArrayList<Character> childList;

        Node(Character id) {
            this.id = id;
            parentList = new ArrayList<>();
            childList = new ArrayList<>();
        }

        public void cAdd(char child) {
            childList.add(child);
        }

        public void pAdd(char parent) {
            parentList.add(parent);
        }
    }
}
// 언어 : Java8 / 시간 : 80 ms