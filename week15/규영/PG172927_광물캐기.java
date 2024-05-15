import java.util.*;

/*
    [0, 0, 1]
    ["stone", "stone", "stone", "stone", "stone", "diamond"]
    5

    8번 테케와 위 테케를 통과하지 못해 질문 게시판을 일부 참고했습니다.
 */

class PG172927_광물캐기 {
    static class Mineral implements Comparable<Mineral>{
        int diamond, iron, stone, sum;
        Mineral (int diamond, int iron, int stone, int sum) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
            this.sum = sum;
        }
        @Override
        public int compareTo(Mineral o) {
            if (this.sum == o.sum) {
                if (this.diamond == o.diamond) {
                    return Integer.compare(o.iron, this.iron);
                }
                return Integer.compare(o.diamond, this.diamond);
            }
            return Integer.compare(o.sum, this.sum);
        }
    }
    public int solution(int[] picks, String[] minerals) {
        int ans = 0, len = minerals.length, canDig = 0;
        // '곡괭이로 채굴 가능한 수 < 광물의 수'인 경우를 고려하기 위함
        for (int i : picks) canDig += i*5;
        Mineral[] arr = new Mineral[len%5 == 0 ? len/5 : len/5+1];
        if (canDig < len) arr = new Mineral[canDig/5];
        Mineral m = new Mineral(0, 0, 0, 0);
        for (int i = 0; i < Math.min(canDig, len); i++) {
            if (minerals[i].equals("diamond")) {
                m.diamond++;
                m.sum += 25;
            } else if (minerals[i].equals("iron")) {
                m.iron++;
                m.sum += 5;
            } else {
                m.stone++;
                m.sum++;
            }
            if ((i+1)%5 == 0 || i == len-1) {
                arr[i/5] = m;
                m = new Mineral(0, 0, 0, 0);
            }
        }
        Arrays.sort(arr);
        for (Mineral mineral : arr) {
            if (0 < picks[0]) {
                picks[0]--;
                ans += mineral.diamond+mineral.iron+mineral.stone;
            } else if (0 < picks[1]) {
                picks[1]--;
                ans += mineral.diamond*5+mineral.iron+mineral.stone;
            } else if (0 < picks[2]) {
                picks[2]--;
                ans += mineral.sum;
            }
        }
        return ans;
    }
}

/*
    테스트 1 〉	통과 (0.38ms, 76.1MB)
    테스트 2 〉	통과 (0.38ms, 78MB)
    테스트 3 〉	통과 (0.39ms, 72.1MB)
    테스트 4 〉	통과 (0.55ms, 81MB)
    테스트 5 〉	통과 (0.42ms, 73.2MB)
    테스트 6 〉	통과 (0.58ms, 79.1MB)
    테스트 7 〉	통과 (0.50ms, 72.8MB)
    테스트 8 〉	통과 (0.40ms, 76.7MB)
    테스트 9 〉	통과 (0.37ms, 71.7MB)
    테스트 10 〉	통과 (0.44ms, 75.1MB)
    테스트 11 〉	통과 (0.42ms, 71.3MB)
    테스트 12 〉	통과 (0.52ms, 79.1MB)
    테스트 13 〉	통과 (0.43ms, 72.6MB)
    테스트 14 〉	통과 (0.44ms, 66.4MB)
    테스트 15 〉	통과 (0.58ms, 77.3MB)
    테스트 16 〉	통과 (0.35ms, 81.9MB)
    테스트 17 〉	통과 (0.53ms, 74.8MB)
    테스트 18 〉	통과 (0.52ms, 76.3MB)
    테스트 19 〉	통과 (0.57ms, 73.6MB)
    테스트 20 〉	통과 (0.59ms, 80MB)
    테스트 21 〉	통과 (0.55ms, 78.8MB)
    테스트 22 〉	통과 (0.51ms, 73.1MB)
    테스트 23 〉	통과 (0.54ms, 74.4MB)
    테스트 24 〉	통과 (0.43ms, 73.9MB)
    테스트 25 〉	통과 (0.55ms, 78.6MB)
    테스트 26 〉	통과 (1.27ms, 69.2MB)
    테스트 27 〉	통과 (0.54ms, 76.3MB)
    테스트 28 〉	통과 (0.57ms, 77.8MB)
    테스트 29 〉	통과 (0.56ms, 84.1MB)
    테스트 30 〉	통과 (0.45ms, 81.8MB)
    테스트 31 〉	통과 (0.48ms, 78.2MB)
    테스트 32 〉	통과 (0.36ms, 76MB)
    테스트 33 〉	통과 (0.40ms, 75.2MB)
    테스트 34 〉	통과 (0.41ms, 77.2MB)
    테스트 35 〉	통과 (0.50ms, 76.4MB)
 */