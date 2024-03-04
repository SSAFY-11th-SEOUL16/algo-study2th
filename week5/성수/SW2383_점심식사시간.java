import java.io.*;
import java.util.*;
 
public class Solution {
    static int N, answer = Integer.MAX_VALUE;
    static List<Pair> people = new ArrayList<>();
    static List<int[]> exits = new ArrayList<>();
    static Queue<Integer>[] queues = new ArrayDeque[2];
    static int[] chosen;
     
    static class Pair implements Comparable<Pair> {
        int x, y;
        public Pair() {}
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
         
        @Override
        public int compareTo(Pair others) {
            return y - others.y;
        }
    }
     
    static void clear() {
        people.clear();
        exits.clear();
        queues[0].clear();
        queues[1].clear();
        chosen = null;
        answer = Integer.MAX_VALUE;
    }
     
    public static int simulate() {
        Pair[] times = new Pair[people.size()];
        for(int i = 0; i < people.size(); i++) {
            Pair p1 = people.get(i);
            int[] p2 = exits.get(chosen[i]);
            times[i] = new Pair(chosen[i], getDistance(p1.x, p1.y, p2[0], p2[1]));
        }
        Arrays.sort(times);
        int idx = 0;
        int curTime = times[idx].y - 1;
        int[] waitCount = {0, 0};
        while(idx < times.length || !queues[0].isEmpty() || !queues[1].isEmpty()) {
            curTime++;
            while(idx < times.length && curTime >= times[idx].y) {
                waitCount[times[idx++].x]++;
            }
            for(int i = 0; i < 2; i++) {
                while(!queues[i].isEmpty() && queues[i].peek() <= curTime) {
                    queues[i].poll();
                }
                while(queues[i].size() < 3 && waitCount[i] > 0) {
                    queues[i].offer(curTime + exits.get(i)[2]);
                    waitCount[i]--;
                }
            }
        }
         
        return curTime;
    }
     
    public static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) + 1;
    }
     
    public static void permutation(int depth) {
        if(depth == people.size()) {
            int tmp = simulate();
            if(tmp < answer) answer = tmp;
            return;
        }
        for(int i = 0; i < 2; i++) {
            chosen[depth] = i;
            permutation(depth + 1);
        }
    }
     
     
 
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        queues[0] = new ArrayDeque<>();
        queues[1] = new ArrayDeque<>();
        int T = Integer.parseInt(input.readLine());
        for(int t = 1; t <= T; t++) {
            int N = Integer.parseInt(input.readLine());
            StringTokenizer tokens;
            for(int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine());
                for(int j = 0; j < N; j++) {
                    int val = Integer.parseInt(tokens.nextToken());
                    if(val == 1) people.add(new Pair(i, j));
                    else if(val > 1) exits.add(new int[] {i, j, val});
                }
            }
            chosen = new int[people.size()];
            permutation(0);
            output.write(String.format("#%d %d\n", t, answer));
             
            clear();
        }
        output.close();
    }
}