import java.io.*;
import java.util.*;

/*
 * 63560KB, 612ms
 * ts.ceiling에서 Problem의 idx를 잘못 설정해 오답
 * 덕분에 TreeSet 공부 더 됐다
 */

public class BOJ21044_문제추천시스템Version2 {
    static class Problem implements Comparable<Problem> {
        int idx, level, algo;
        Problem(int idx, int level, int algo) {
            this.idx = idx;
            this.level = level;
            this.algo = algo;
        }
        @Override
        public int compareTo(Problem o) {
            if (this.level == o.level) return Integer.compare(o.idx, this.idx);
            return Integer.compare(o.level, this.level);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        TreeSet<Problem> ts = new TreeSet<>();
        Map<Integer, TreeSet<Problem>> treeSetMap = new HashMap<>();
        Problem[] problems = new Problem[100001];
        while (N-->0) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken()), G = Integer.parseInt(st.nextToken());
            Problem prob = new Problem(P, L, G);
            ts.add(prob);
            treeSetMap.putIfAbsent(G, new TreeSet<>());
            treeSetMap.get(G).add(prob);
            problems[P] = prob;
        }
        int M = Integer.parseInt(br.readLine());
        while (M-->0) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "recommend": {
                    int G = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
                    sb.append(x == 1 ? treeSetMap.get(G).first().idx : treeSetMap.get(G).last().idx).append('\n');
                    break;
                }
                case "recommend2": {
                    int x = Integer.parseInt(st.nextToken());
                    sb.append(x == 1 ? ts.first().idx : ts.last().idx).append('\n');
                    break;
                }
                case "recommend3": {
                    int x = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken());
                    if (x == 1) {
                        Problem floor = ts.floor(new Problem(0, L, 0));
                        sb.append(floor == null ? -1 : floor.idx);
                    } else {
                        Problem ceiling = ts.ceiling(new Problem(0, L, 0));
                        sb.append(ceiling == null ? -1 : ceiling.idx);
                    }
                    sb.append('\n');
                    break;
                }
                case "add": {
                    int P = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken()), G = Integer.parseInt(st.nextToken());
                    Problem prob = new Problem(P, L, G);
                    ts.add(prob);
                    treeSetMap.putIfAbsent(G, new TreeSet<>());
                    treeSetMap.get(G).add(prob);
                    problems[P] = prob;
                    break;
                }
                case "solved": {
                    int P = Integer.parseInt(st.nextToken()), L = problems[P].level , G = problems[P].algo;
                    Problem prob = new Problem(P, L, G);
                    ts.remove(prob);
                    treeSetMap.get(G).remove(prob);
                    problems[P] = null;
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}