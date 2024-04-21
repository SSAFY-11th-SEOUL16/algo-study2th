import java.io.*;
import java.util.*;

/*
    각 도시 별 효율을 구해서 풀려고 했으나 이 방법으론 풀리지 않는다는 걸 깨달음
    dp로 풀이 시도하다가 정처기가 너무 코앞으로 다가와서 run...
 */

public class BOJ1106_호텔 {
    static class City implements Comparable<City> {
        int cost, guest;
        City (int cost, int guest) {
            this.cost = cost;
            this.guest = guest;
        }
        @Override
        public int compareTo(City o) {
            return Double.compare((double)this.cost/this.guest, (double)o.cost/o.guest);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // C: 늘리고 싶은 고객 수 (1~1000), N: 홍보할 수 있는 도시 수 (최대 20)
        int C = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken()), ans = 0;
        int minCost = 101, maxCost = 0;
        // if 홍보되는 사람 수가 C보다 작으면 -> 가장 효율 좋은 도시에 홍보
        int[] costInfo = new int[101], guestInfo = new int[101];
        City[] cities = new City[N];
        PriorityQueue<City> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken()), guest = Integer.parseInt(st.nextToken());
            if (minCost > cost) minCost = cost;
            if (maxCost < cost) maxCost = cost;
            City c = new City(cost, guest);
            pq.offer(cities[i] = c);
            costInfo[i] = cost;
            guestInfo[i] = guest;
        }
        Arrays.sort(cities);
        int[] costs = new int[C];
        for (int i = 0; i < C; i++) {
            for (int j = minCost; j <= maxCost; j++) {
                // costs[i]

            }
        }
        System.out.print(ans);
    }
}