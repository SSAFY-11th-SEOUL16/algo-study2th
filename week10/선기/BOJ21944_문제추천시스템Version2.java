import java.io.*;
import java.util.*;

/**
 * 67396KB
 * 820ms
 */
public class BOJ21944_문제추천시스템Version2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m;

    static Comparator<Problem> plg = (e1, e2) -> {
        int compare1 = e1.g - e2.g;
        if (compare1 == 0) {
            int compare2 = e1.l - e2.l;
            if (compare2 == 0) {
                return e1.p - e2.p;
            }
            return compare2;
        }
        return compare1;

    };

    static Comparator<Problem> lg = (e1, e2) -> {
        int compare1 = e1.l - e2.l;
        if (compare1 == 0) {
            return e1.p - e2.p;
        }
        return compare1;
    };

    static Comparator<Problem> p = (e1, e2) -> {
        return e1.p - e2.p;
    };

    static TreeSet<Problem> problemsWithPLG = new TreeSet<>(plg);
    static TreeSet<Problem> problemsWithPL = new TreeSet<>(lg);
    static TreeSet<Problem> problemsWithP = new TreeSet<>(p);

    public static void main(String[] args) throws IOException {
        n = nextInt();
        for (int i = 0; i < n; ++i) {
            int p = nextInt();
            int l = nextInt();
            int g = nextInt();
            Problem problem = new Problem(p, l, g);
            problemsWithPLG.add(problem);
            problemsWithPL.add(problem);
            problemsWithP.add(problem);
        }
        m = nextInt();
        for (int i = 0; i < m; ++i) {
            String cmd = next();
            if ("recommend".equals(cmd)) {
                int g = nextInt();
                int x = nextInt();
                Problem ret = null;
                if (x == 1) {
                    ret = problemsWithPLG.lower(new Problem(1000001, 101, g));
                } else {
                    ret = problemsWithPLG.higher(new Problem(0, 0, g));
                }
                sb.append(ret.p).append("\n");
            } else if ("recommend2".equals(cmd)) {
                int x = nextInt();
                Problem ret = null;
                if (x == 1) {
                    ret = problemsWithPL.last();
                } else {
                    ret = problemsWithPL.first();
                }
                sb.append(ret.p).append("\n");
            } else if ("recommend3".equals(cmd)) {
                int x = nextInt();
                int l = nextInt();
                Problem ret = null;
                if (x == 1) {
                    ret = problemsWithPL.ceiling(new Problem(0, l, 0));
                } else {
                    ret = problemsWithPL.lower(new Problem(0, l, 0));
                }
                if (ret == null) {
                    sb.append("-1\n");
                } else {
                    sb.append(ret.p).append("\n");
                }

            } else if ("add".equals(cmd)) {
                int p = nextInt();
                int l = nextInt();
                int g = nextInt();
                Problem problem = new Problem(p, l, g);
                problemsWithPLG.add(problem);
                problemsWithPL.add(problem);
                problemsWithP.add(problem);
            } else if ("solved".equals(cmd)) {
                int p = nextInt();
                Problem problem = problemsWithP.floor(new Problem(p, 0, 0));
                problemsWithP.remove(problem);
                problemsWithPL.remove(problem);
                problemsWithPLG.remove(problem);
            }
        }
        System.out.println(sb);

    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static class Problem {
        int p;
        int l;
        int g;

        Problem(int p, int l, int g) {
            this.p = p;
            this.l = l;
            this.g = g;
        }
    }

}
