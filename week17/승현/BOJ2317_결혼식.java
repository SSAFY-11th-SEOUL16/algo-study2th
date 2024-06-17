import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2317_결혼식 { // 100ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int minLionHeight = Integer.MAX_VALUE;
        int maxLionHeight = 0;
        int leftLionHeight = 0;
        int rightLionHeight = 0;
        int minPeopleHeight = Integer.MAX_VALUE;
        int maxPeopleHeight = 0;
        int result = 0;
        int prev = 0;

        for (int i = 0; i < k; i++) {
            int lionHeight = Integer.parseInt(br.readLine());

            //아 여기네
            if (i != 0) {
                result += Math.abs(lionHeight - prev);
            }
            if (i == 0) {
                leftLionHeight = lionHeight;
            } else if (i == k - 1) {
                rightLionHeight = lionHeight;
            }

            if (lionHeight > maxLionHeight) {
                maxLionHeight = lionHeight;
            }
            if (lionHeight < minLionHeight) {
                minLionHeight = lionHeight;
            }
            prev = lionHeight;
        }

        //3퍼테케뭐지? 또 3퍼에서걸림
        for (int i = 0; i < n - k; i++) {
            int peopleHeight = Integer.parseInt(br.readLine());
            minPeopleHeight = Math.min(minPeopleHeight, peopleHeight);
            maxPeopleHeight = Math.max(maxPeopleHeight, peopleHeight);
        }

        if (minLionHeight > minPeopleHeight) {
            result += Math.min(Math.abs(minPeopleHeight - minLionHeight) * 2,
                    Math.abs(minPeopleHeight - Math.min(leftLionHeight, rightLionHeight)));
        }

        if (maxPeopleHeight > maxLionHeight) {
            result += Math.min(Math.abs(maxPeopleHeight - maxLionHeight) * 2,
                    Math.abs(maxPeopleHeight - Math.max(leftLionHeight, rightLionHeight)));
        }

        System.out.println(result);
    }
}
