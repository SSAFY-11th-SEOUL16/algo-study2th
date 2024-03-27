import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 언어 : Java / 시간 2183ms
 */

public class Solution {
    static int[][] map = new int[4002][4002]; // 시뮬레이션 중 원자들의 위치를 저장 , 0.5초 해결하려고 맵 두배로 늘림
    static HashMap<Integer, Node> nodeList; // 현재 살아있는 원자들을 저장
    static Node[] nodeArray; // 원자 번호에 따른 원자 노드의 정보를 저장
    static int cnt;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            nodeList = new HashMap();
            nodeArray = new Node[n + 1];
            cnt = 0;
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = 2 * (Integer.parseInt(st.nextToken()) + 1000);
                int y = 2 * (Integer.parseInt(st.nextToken()) + 1000);
                int dir = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                Node node = new Node(i, x, y, dir, k);

                nodeList.put(i, node);
                nodeArray[i] = node;
            }
            simulation();
            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static void simulation() {

        ArrayList<Integer> outList = new ArrayList<>(); // 맵 밖에 나가간 원자 번호 넣는 리스트
        ArrayList<Node> removeMapList = new ArrayList<>(); // 맵 초기화가 필요한 원자 위치를 넣는 리스트
        HashSet<Integer> removeSet = new HashSet<>(); //충돌한 원자들 담는 set

        for (int i = 0; i < 4002; i++) {
            if (nodeList.size() < 2)
                break;

            outList.clear();
            removeMapList.clear();
            removeSet.clear();

            for (Integer key : nodeList.keySet()) { // 생존한 원자들 탐색
                Node node = nodeList.get(key);
                int x = node.x + dx[node.dir];
                int y = node.y + dy[node.dir];

                if (x >= 0 && y >= 0 && x < 4002 && y < 4002) {
                    node.x = x;
                    node.y = y;

                    if (map[y][x] != 0) { // 충돌하는 원자가 있다면
                        removeSet.add(map[y][x]);
                        removeSet.add(node.vertex);
                    } else { // 충돌하는 원자가 없다면
                        map[y][x] = node.vertex;
                        removeMapList.add(node);
                    }
                } else { // 맵 밖으로 나갔다면
                    outList.add(node.vertex);
                }
            }
            for (int vertex : outList) // 맵 밖으로 나간 원자들 삭제
                nodeList.remove(vertex);

            for (int vertex : removeSet) { // 충돌한 원자들 삭제
                cnt += nodeArray[vertex].power;
                nodeList.remove(vertex);
            }
            for (Node removeMap : removeMapList) // 노드들의 위치를 저장한 map 초기화
                map[removeMap.y][removeMap.x] = 0;

        }
    }

    static class Node {
        int vertex;
        int x;
        int y;
        int dir;
        int power;

        Node(int vertex, int x, int y, int dir, int power) {
            this.vertex = vertex;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.power = power;
        }

    }
}
