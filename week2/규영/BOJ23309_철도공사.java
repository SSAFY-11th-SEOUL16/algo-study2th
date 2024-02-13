package 규영;

import java.io.*;
import java.util.*;

public class BOJ23309_철도공사 {
    static int[] prev, next;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        /*
         시간 초과가 많이 난다고 해서.. 1차원 배열 2개로 구현 시도
         고유 번호를 index로 사용
         prev는 해당 고유 번호 다음 역의 고유 번호를 저장
         next는 해당 고유 번호 이전 역의 고유 번호를 저장
         */
        prev = new int[1000001];
        next = new int[1000001];
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        // prev와 next에 저장하는 과정
        prev[arr[0]] = arr[N-1];
        next[arr[0]] = arr[1];
        for (int i = 1; i < N; i++) {
            prev[arr[i]] = arr[i-1];
            next[arr[i]] = arr[(i+1)%N];
        }
        // command 시작
        while (M-->0) {
            st = new StringTokenizer(br.readLine());
            char[] command = st.nextToken().toCharArray();
            int num = Integer.parseInt(st.nextToken());
            // C일 경우 st.nextToken()할 필요가 없음
            if (command[0] == 'B') B(num, command[1] == 'N' ? true : false, Integer.parseInt(st.nextToken()));
            else C(num, command[1] == 'N' ? true : false);
            sb.append('\n');
        }
        System.out.println(sb);
    }

    /*
     B로 시작하는 명령.
     num : 역의 고유 번호
     flag일 경우 BN, !flag일 경우 BP으로 받아 들인다
     newNum : 새로 설립할 역의 고유 번호
     */
    static void B(int num, boolean flag, int newNum) {
        // target : BN일 경우 num의 다음 역 고유 번호, BP일 경우 이전 역 고유 번호
        int target = flag ? next[num] : prev[num];
        sb.append(target);
        // newNum의 앞뒷역을 정해준 뒤 나머지를 변경해야 한다
        if (flag) {
            prev[newNum] = num;
            next[newNum] = target;
            prev[target] = newNum;
            next[num] = newNum;
        } else {
            next[newNum] = num;
            prev[newNum] = target;
            next[target] = newNum;
            prev[num] = newNum;
        }
    }

    /*
     C로 시작하는 명령
     num : 역의 고유 번호
     flag일 경우 BN, !flag일 경우 BP으로 받아 들인다
     */
    static void C(int num, boolean flag) {
        // target : CN일 경우 num의 다음 역 고유 번호, CP일 경우 이전 역 고유 번호. 즉, 폐쇄할 역의 고유 번호
        int target = flag ? next[num] : prev[num];
        sb.append(target);
        if (flag) {
            prev[next[target]] = num; // target 다음 역의 이전 역은 target 대신 현재 num이 되어야 함
            next[num] = next[target]; // target 다음 역은 num의 다음 역으로 변경
        } else {
            next[prev[target]] = num; // target 이전 역의 다음 역은 target 대신 현재 num이 되어야 함
            prev[num] = prev[target]; // target 이전 역은 num의 이전 역으로 변경
        }
        next[target] = prev[target] = 0; // 폐쇄 되었으므로 target의 앞뒷역 정보 없앰 (연결을 끊어줌)
    }
}