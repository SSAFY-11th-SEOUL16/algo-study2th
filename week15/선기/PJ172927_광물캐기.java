import java.util.*;

/*
 * 테스트 35 〉	통과 (0.74ms, 72.2MB)
 */
class Solution {
    static int ans = Integer.MAX_VALUE;
    static int compressedLen;
    static int[] picks;
    static int[][] costOf5minerials;
    public int solution(int[] picks, String[] minerals) {
        Solution.picks = picks;
        compressedLen = minerals.length / 5 + 1;
        costOf5minerials = new int[compressedLen][3];
        for (int i = 0; i < minerals.length; ++i) {
            if ("diamond".equals(minerals[i])) {
                costOf5minerials[i / 5][0] += 1;
                costOf5minerials[i / 5][1] += 5;
                costOf5minerials[i / 5][2] += 25;
            } else if ("iron".equals(minerals[i])) {
                costOf5minerials[i / 5][0] += 1;
                costOf5minerials[i / 5][1] += 1;
                costOf5minerials[i / 5][2] += 5;
            } else {
                costOf5minerials[i / 5][0] += 1;
                costOf5minerials[i / 5][1] += 1;
                costOf5minerials[i / 5][2] += 1;
            }
        }
        dfs(0, 0);
        return ans;
    }

    void dfs(int cost, int idx) {
        if (idx == compressedLen) {
            ans = Math.min(ans, cost);
            return;
        }
        if (picks[0] == 0 && picks[1] == 0 && picks[2] == 0) {
            ans = Math.min(ans, cost);
            return;
        }
        for (int i = 0; i < 3; ++i) {
            if (picks[i] != 0) {
                picks[i]--;
                dfs(cost + costOf5minerials[idx][i], idx + 1);
                picks[i]++;
            }
        }
    }
}