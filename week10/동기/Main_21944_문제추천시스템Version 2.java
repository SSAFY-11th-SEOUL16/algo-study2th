import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;
/*
* 688ms
*/

public class Main {
    static int[] diffArr = new int[100001];
    static int[] algoArr = new int[100001];
    static TreeSet<Problem>[] algoSetArr = new TreeSet[101];
    static TreeSet<Problem> allProblem = new TreeSet<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 101; i++) algoSetArr[i] = new TreeSet<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            add(st);
        }
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "recommend": {
                    int g = Integer.parseInt(st.nextToken());
                    int x = Integer.parseInt(st.nextToken());
                    if (x == 1)
                        sb.append(algoSetArr[g].first().num).append("\n");
                    else
                        sb.append(algoSetArr[g].last().num).append("\n");

                    break;
                }
                case "recommend2": {
                    int x = Integer.parseInt(st.nextToken());
                    if (x == 1)
                        sb.append(allProblem.first().num).append("\n");
                    else
                        sb.append(allProblem.last().num).append("\n");

                    break;
                }
                case "recommend3": {
                    int x = Integer.parseInt(st.nextToken());
                    int l = Integer.parseInt(st.nextToken());
                    Problem problem = new Problem(0, l);
                    int s;
                    if (x == 1)
                        s = allProblem.floor(problem) == null ? -1 : allProblem.floor(problem).num;
                    else
                        s = allProblem.ceiling(problem) == null ? -1 : allProblem.ceiling(problem).num;

                    sb.append(s).append("\n");
                    break;
                }
                case "add": {
                    add(st);
                    break;
                }
                case "solved": {
                    int p = Integer.parseInt(st.nextToken());
                    Problem problem = new Problem(p, diffArr[p]);
                    algoSetArr[algoArr[p]].remove(problem);
                    allProblem.remove(problem);
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    static void add(StringTokenizer st) {
        int p = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        Problem problem = new Problem(p, l);

        diffArr[p] = l;
        algoArr[p] = g;
        algoSetArr[g].add(problem);
        allProblem.add(problem);
    }

    static class Problem implements Comparable<Problem> {
        int num;
        int difficulty;

        Problem(int num, int difficulty) {
            this.num = num;
            this.difficulty = difficulty;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.difficulty == o.difficulty) return o.num - this.num;
            return o.difficulty - this.difficulty;
        }
    }
}
