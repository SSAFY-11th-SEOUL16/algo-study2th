import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 212 ms
 */

public class Solution {
    static int n;
    static int x;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                map[i] = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            sb.append("#").append(tc).append(" ").append(simulation()).append("\n");
        }

        System.out.println(sb);
    }

    static int simulation() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (validCheck(map[i].clone())) cnt++;
        }
        int[] arr;
        for (int j = 0; j < n; j++) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = map[i][j];
            }
            if (validCheck(arr)) cnt++;
        }
        return cnt;
    }

    // 한줄씩 유효한 지형인지 체크
    static boolean validCheck(int[] arr) {
        int curHeight = arr[0];
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > curHeight) { // 이전 지형보다 현재 탐색한 지형이 더 높을 경우
                if (arr[i] > curHeight + 1) { // 2이상 차이나면 false
                    return false;
                } else {
                    int cnt = 0;
                    for (int j = i - 1; j >= 0; j--) { // 이전 지형들을 탐색하며 경사로 건설이 가능한지 체크
                        if (cnt == x) break;
                        if (arr[j] == curHeight) cnt++;
                        else break;
                    }
                    if (cnt == x) // 경사로 건설이 가능한 길이므로 현재 높이를 갱신
                        curHeight = arr[i];
                    else return false;

                }
            } else if (arr[i] < curHeight) { // 이전 지형보다 현재 탐색한 지형이 더 낮을 경우
                if (arr[i] + 1 < curHeight) { // 2이상 차이나면 false
                    return false;
                } else {
                    int cnt = 0;
                    int newHeight = arr[i];
                    int j = i;
                    for (; j < arr.length; j++) {
                        if (cnt == x) break;
                        if (arr[j] == newHeight) {
                            cnt++;
                            arr[j] = curHeight; // 경사로 겹치기를 방지하기 위해 경사로 설치했다는 의미로 해당 지형의 높이를 높은 위치로 수정
                        } else break;
                    }
                    if (cnt == x) { // 경사로 건설이 가능한 길이므로 현재 높이를 갱신하고, 탐색 위치 갱신
                        curHeight = newHeight;
                        i = j - 1;
                    } else return false;
                }
            }
        }
        return true;
    }
}