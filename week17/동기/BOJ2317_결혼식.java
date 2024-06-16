import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 92ms
 */
public class Main {
    static long maxValue = 1024L * 1024 * 1024 * 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long smallLion = maxValue;
        long bigLion = 0;
        long firstLion = 0;
        long lastLion = 0;

        long prevLion = 0;
        long answer = 0;
        for (int i = 0; i < k; i++) {
            long lion = Long.parseLong(br.readLine());
            smallLion = Math.min(lion, smallLion);
            bigLion = Math.max(lion, bigLion);
            if (i == 0) firstLion = lion;
            if (i == k - 1) lastLion = lion;

            if (i != 0) answer += (Math.abs(prevLion - lion));
            prevLion = lion;
        }

        long smallPeople = maxValue;
        long bigPeople = 0;

        for (int i = k; i < n; i++) {
            long people = Long.parseLong(br.readLine());
            smallPeople = Math.min(smallPeople, people);
            bigPeople = Math.max(bigPeople, people);
        }
        long minDiff = Math.min(Math.abs(smallPeople - lastLion), Math.min(Math.abs(smallPeople - firstLion), (2 * Math.abs(smallLion - smallPeople))));
        long maxDiff = Math.min(Math.abs(bigPeople - lastLion), Math.min(Math.abs(bigPeople - firstLion), (2 * Math.abs(bigLion - bigPeople))));

        if (smallPeople > smallLion) minDiff = 0;
        if (bigPeople < bigLion) maxDiff = 0;

        System.out.println(answer + minDiff + maxDiff);
    }
}

/*
1. 가장 큰 사자와 작은 사자 사이에 있는 인간들은 취급하지 않는다.
(어차피 사자와 사자 사이에 가는 길에 있기 때문에
ex : 1사자 3 인간 5사자 (3-1) + (5-3) = 4 = (5-1) )
2. 그럼 남는게 가장 작은 사자보다 작거나, 가장 큰 사자보다 큰 사람들만 남음
3. 근데 사람들도 사람들끼리 정렬을 하면 가장 작거나 큰 사람만 필요함
(ex: 사람 : 1,2,3 가장 작은 사자 : 4 이러면 사람2,3은 지나가는 길에 있으니까 무시하고
작은사자 - 작은사람 = 4-1 = 3만큼의 차이를 더해주면 됨, 큰사람도 동일함)

-- 이제부터 작은거로 예를 들겠음 (큰것도 같아)
작은사람을 배치하는 세 가지 경우가 있음
1. 마지막 사자 뒤에 배치할 경우
- |마지막 사자 - 작은사람|
2. 첫번째 사자 앞에 배치할 경우
- |첫번째 사자 - 작은사람|
3. 작은 사자가 중간에 껴있는 경우
- 이 경우는 내려갔다가 다시 올라오는 경우를 더해줘야 하므로 2* |작은사자 - 작은사람|을 더해줘야함

이 세 경우를 각각 비교해서 최소값을 구해서 기존 사자끼리 키차이값에 더해주면 됨
 */