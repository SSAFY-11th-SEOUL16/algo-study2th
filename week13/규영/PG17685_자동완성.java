import java.util.*;

class PG17685_자동완성 {
    String[] words;
    public int solution(String[] words) {
        Arrays.sort(words);
        int N = words.length;
        int[] cnt = new int[N]; // 입력해야 하는 문자 수 저장
        for (int i = 0; i < N-1; i++) {
            String now = words[i], next = words[i+1];
            int len = Math.min(now.length(), next.length());
            int prefixLen = getPrefixLen(now, next, len);

            // len == prefixLen : 짧은 문자열이 긴 문자열에 완벽히 포함 -> 짧은 문자열만큼 치면 됨
            // len != prefixLen : 짧은 문자열을 구별하기 위해 한 글자를 더 쳐야함
            if (len == prefixLen) cnt[i] = Math.max(cnt[i], prefixLen);
            else cnt[i] = Math.max(cnt[i], prefixLen+1);

            // 다음 문자열 초기화. 최소 prefixLen+1만큼 쳐야 한다
            cnt[i+1] = prefixLen+1;
        }
        // int ans = 0;
        // for (int i : cnt) ans += i;
        // return ans;
        return Arrays.stream(cnt).sum();
    }

    int getPrefixLen(String now, String next, int len) {
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if (now.charAt(i) == next.charAt(i)) cnt++;
            else return cnt;
        }
        return cnt;
    }
}

/*
    테스트 1 〉	통과 (1.50ms, 77.1MB)
    테스트 2 〉	통과 (1.15ms, 76.5MB)
    테스트 3 〉	통과 (0.88ms, 77.8MB)
    테스트 4 〉	통과 (0.94ms, 76.6MB)
    테스트 5 〉	통과 (0.87ms, 72MB)
    테스트 6 〉	통과 (126.03ms, 127MB)
    테스트 7 〉	통과 (0.84ms, 77.8MB)
    테스트 8 〉	통과 (136.76ms, 128MB)
    테스트 9 〉	통과 (0.87ms, 78.2MB)
    테스트 10 〉	통과 (0.88ms, 75.5MB)
    테스트 11 〉	통과 (0.96ms, 74.5MB)
    테스트 12 〉	통과 (198.57ms, 132MB)
    테스트 13 〉	통과 (209.76ms, 126MB)
    테스트 14 〉	통과 (1.46ms, 82.7MB)
    테스트 15 〉	통과 (1.11ms, 75MB)
    테스트 16 〉	통과 (204.39ms, 122MB)
    테스트 17 〉	통과 (147.51ms, 142MB)
    테스트 18 〉	통과 (0.88ms, 71.3MB)
    테스트 19 〉	통과 (1.51ms, 90MB)
    테스트 20 〉	통과 (135.82ms, 121MB)
    테스트 21 〉	통과 (1.80ms, 72MB)
    테스트 22 〉	통과 (1.36ms, 88MB)
 */