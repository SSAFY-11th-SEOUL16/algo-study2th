import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23309_철도공사 {
    static class Station {
        int prev;
        int next;
        public Station(int prev, int next) {
            this.prev = prev;
            this.next = next;
        }
    }
    static int N; // 공사 시작 전, 역의 개수
    static int M; // 공사 횟수
    static int INF = 1_000_000;
    static Station[] stations;
    static StringBuilder sb;

    private static void calc(String command, int i, int j) {
        if(command.equals("BN") && stations[j] == null) {
            int next = stations[i].next;
            stations[i].next = j;
            stations[j] = new Station(i, next);
            stations[next].prev = j;
            sb.append(next).append("\n");
        }
        else if(command.equals("BP") && stations[j] == null) {
            int prev = stations[i].prev;
            stations[i].prev = j;
            stations[j] = new Station(prev, i);
            stations[prev].next = j;
            sb.append(prev).append("\n");
        }
        else if(command.equals("CP")) {
            int curr = stations[i].prev;
            int prev = stations[curr].prev;
            int next = stations[curr].next;
            stations[next].prev = prev;
            stations[prev].next = next;
            stations[curr] = null;
            sb.append(curr).append("\n");
        }
        else if(command.equals("CN")) {
            int curr = stations[i].next;
            int prev = stations[curr].prev;
            int next = stations[curr].next;
            stations[next].prev = prev;
            stations[prev].next = next;
            stations[curr] = null;
            sb.append(curr).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        stations = new Station[INF + 1];
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i < N - 1; i++) {
            stations[arr[i]] = new Station(arr[i - 1], arr[i + 1]);
        }
        stations[arr[0]] = new Station(arr[N - 1], arr[1]);
        stations[arr[N - 1]] =  new Station(arr[N - 2], arr[0]);

        sb = new StringBuilder();
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int i = Integer.parseInt(st.nextToken());
            int j = -1;
            if(st.hasMoreTokens()) j = Integer.parseInt(st.nextToken());
            calc(command, i, j);
        }
        System.out.println(sb);
    }
}
