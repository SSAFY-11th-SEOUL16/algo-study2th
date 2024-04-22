import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23295_스터디시간정하기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[] times = new int[100010];
        int lastTime = 0;
        for(int i=0; i<n; i++) {
            int k = Integer.parseInt(br.readLine());
            for(int j=0; j<k; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                lastTime = Math.max(b, lastTime);
                times[a]++;
                times[b]--;
            }
        }

        for(int i=1; i<=lastTime; i++) {
            times[i] += times[i-1];
        }

        int nowTime = 0;
        int startTime = 0;
        for(int i=0; i<t; i++) {
            nowTime += times[i];
        }

        int maxTime = nowTime;

        for(int i=t; i<=lastTime; i++) {
            int prevStart = i - t;
            nowTime -= times[prevStart];
            nowTime += times[i];

            if (nowTime > maxTime) {
                startTime = prevStart + 1;
                maxTime = nowTime;
            }
        }

        System.out.println(startTime + " " + (startTime + t));

    }

}
