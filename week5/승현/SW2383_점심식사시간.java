import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class SW2383_점심식사시간 {
    static int result;
    static int exit;
    static int N;
    static int[][] room;
    static boolean[] visited;
    static ArrayList<People> people;
    static ArrayList<Stairs> stairs;
 
    static class People implements Comparable<People> {
        int x;
        int y;
        int dist;
        int inTime;
        boolean sIn;
 
        People(int x, int y) {
            this.x = x;
            this.y = y;
            this.sIn = false;
            this.inTime = 0;
        }

		@Override
		public int compareTo(People o) {
			return this.dist - o.dist;
		}
    }
 
    static class Stairs {
        int x;
        int y;
        int val;
 
        Stairs(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            room = new int[N][N];
            people = new ArrayList<>();
            stairs = new ArrayList<>();
            result = 31;
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    room[i][j] = Integer.parseInt(st.nextToken());
                    if (room[i][j] == 1) {
                        people.add(new People(i, j));
                    }
 
                    else if (room[i][j] >= 2 && room[i][j] <= 10) {
                        stairs.add(new Stairs(i, j, room[i][j]));
                    }
                }
            }
            visited = new boolean[people.size()];

            subsets(0);
             
            sb.append("#").append(tc).append(" ").append(result);
            if (tc < T)
                sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
    public static void subsets(int depth) {
    	if (depth == people.size()) {
    		int r = getResult();
    		if (r < result)
    			result = r;
    		return;
    	}
    	visited[depth] = false;
    	subsets(depth+1);
    	visited[depth] = true;
    	subsets(depth+1);
    }
    
    public static int getResult() {
    	PriorityQueue<People> q1 = new PriorityQueue<People>();
    	PriorityQueue<People> q2 = new PriorityQueue<People>();
        Deque<People> d1 = new ArrayDeque<>();
        Deque<People> d2 = new ArrayDeque<>();
        exit = 0;
        
        for (int i = 0; i < people.size(); i++) {
        	if (visited[i]) {
        		people.get(i).dist = getDist(i, 0);
        		q1.offer(people.get(i));
        	}
        	else {
        		people.get(i).dist = getDist(i, 1);
        		q2.offer(people.get(i));
        	}
        }
        
        for (int i = 1; i <= 31; i++) {
        	while (!d1.isEmpty() && (i-d1.peek().inTime) == stairs.get(0).val) {
        		d1.poll();
        		exit++;
        		while (d1.size() < 3 && !q1.isEmpty() && q1.peek().dist < i) {
        			People p1 = q1.poll();
                	p1.inTime = i;
                	d1.offer(p1);
        		}
        	}
        	while (!d2.isEmpty() && (i-d2.peek().inTime) == stairs.get(1).val) {
        		d2.poll();
        		exit++;
        		while (d2.size() < 3 && !q2.isEmpty() && q2.peek().dist < i) {
        			People p2 = q2.poll();
                	p2.inTime = i;
                	d2.offer(p2);
        		}
        	}
            while (!q1.isEmpty() && q1.peek().dist == i && d1.size() < 3) {
            	People p1 = q1.poll();
            	p1.inTime = i+1;
            	d1.offer(p1);
            }
            while (!q2.isEmpty() && q2.peek().dist == i && d2.size() < 3) {
            	People p2 = q2.poll();
            	p2.inTime = i+1;
            	d2.offer(p2);
            }
            
        	if (exit == people.size()) {
        		return i;
        	}
        }
		return 31;
    }
 
    public static int getDist(int i, int sn) {
        return Math.abs(people.get(i).x - stairs.get(sn).x) + Math.abs(people.get(i).y - stairs.get(sn).y);
    }
 
}