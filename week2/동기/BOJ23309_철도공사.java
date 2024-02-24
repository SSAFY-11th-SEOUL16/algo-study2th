import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] prevStations = new int[1000001];
        int[] nextStations = new int[1000001];

        prevStations[arr[0]] = arr[n-1];
        nextStations[arr[0]] = arr[1];

        prevStations[arr[n-1]] = arr[n-2];
        nextStations[arr[n-1]] = arr[0];

        for (int i = 1; i < n - 1; i++) {
            prevStations[arr[i]] = arr[i-1];
            nextStations[arr[i]] = arr[i+1];
        }

        StringBuilder sb = new StringBuilder();

        int j;
        int i;
        int nextIndex;
        int prevIndex;
        int removeIndex;
        int removeNextIndex;
        int removePrevIndex;
        for (int x = 0; x < m; x++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {

                case "BN": {
                    i = Integer.parseInt(st.nextToken());
                    j = Integer.parseInt(st.nextToken());

                    nextIndex = nextStations[i];

                    prevStations[j] = i;
                    nextStations[j] = nextIndex;

                    nextStations[i] = j;
                    prevStations[nextIndex] = j;

                    sb.append(nextIndex).append("\n");
                    break;
                }
                case "BP": {
                    i = Integer.parseInt(st.nextToken());
                    j = Integer.parseInt(st.nextToken());

                    prevIndex = prevStations[i];

                    prevStations[j] = prevIndex;
                    nextStations[j] = i;

                    nextStations[prevIndex] = j;
                    prevStations[i] = j;
                    sb.append(prevIndex).append("\n");
                    break;

                }
                case "CN": {
                    i = Integer.parseInt(st.nextToken());

                    removeIndex = nextStations[i];

                    removeNextIndex = nextStations[removeIndex];

                    nextStations[i]= removeNextIndex;
                    prevStations[removeNextIndex] = i;

                    sb.append(removeIndex).append("\n");
                    break;

                }
                case "CP": {
                    i = Integer.parseInt(st.nextToken());

                    removeIndex = prevStations[i];
                    removePrevIndex = prevStations[removeIndex];

                    prevStations[i]= removePrevIndex;
                    nextStations[removePrevIndex] = i;

                    sb.append(removeIndex).append("\n");
                    break;
                }
            }
        }

        System.out.println(sb);
        br.close();
    }
}
