import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/172927?language=java
 */
class PG172927_광물캐기 {
    static class Mineral implements Comparable<Mineral> {
        int dia, iron, stone;
        public Mineral(int dia, int iron, int stone) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
        }

        @Override
        public int compareTo(Mineral o) {
            if(o.dia == this.dia) {
                if(o.iron == this.iron) {
                    return o.stone - this.stone;
                }
                return o.iron - this.iron;
            }
            return o.dia - this.dia;
        }
    }
    static int[][] SCORE = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

    public static List<Mineral> makeGroups(String[] minerals, int possibleLen) {
        int len = minerals.length;
        List<Mineral> groups = new ArrayList<>();
        int dia = 0;
        int iron = 0;
        int stone = 0;
        for (int i = 0; i < len; i++) {
            if(i >= possibleLen) break;
            if(minerals[i].equals("diamond")) dia += 1;
            else if(minerals[i].equals("iron")) iron += 1;
            else stone += 1;

            if((i + 1) % 5 == 0) {
                groups.add(new Mineral(dia, iron, stone));
                dia = iron = stone = 0;
            }
        }
        if(len % 5 != 0 && len <= possibleLen) groups.add(new Mineral(dia, iron, stone));
        return groups;
    }

    public static int calc(int pick, Mineral mineral) {
        int sum = 0;
        sum += SCORE[pick][0] * mineral.dia;
        sum += SCORE[pick][1] * mineral.iron;
        sum += SCORE[pick][2] * mineral.stone;
        return sum;
    }

    public static int solution(int[] picks, String[] minerals) {
        int possibleMineralLen = Arrays.stream(picks).sum() * 5;
        List<Mineral> groups = makeGroups(minerals, possibleMineralLen);
        Collections.sort(groups);

        int idx = 0;
        int totalSum = 0;
        for (int pick = 0; pick < picks.length; pick++) {
            for (int i = 1; i <= picks[pick]; i++) {
                totalSum += calc(pick, groups.get(idx++));
                if(idx == groups.size()) return totalSum;
            }
        }

        return totalSum;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(solution(
            new int[]{1, 3, 2},
            new String[] {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"}
        )); // 12
        System.out.println(solution(
            new int[]{0, 1, 1},
            new String[] {"diamond", "diamond", "diamond", "diamond", "diamond",
                "iron", "iron", "iron", "iron", "iron", "diamond"}
        )); // 50
        System.out.println(solution(
            new int[]{0, 0, 1},
            new String[] {"stone", "stone", "stone", "stone", "stone", "diamond"}
        )); // 5
    }
}
