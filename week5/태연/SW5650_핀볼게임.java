import java.io.*;
import java.util.*;
 
public class SW5650_핀볼게임 {
     
	/*
	 *  - 499ms
	 * 
	 *  - 모든 출발지+방향에 대해서 완탐
	 *  
	 *  - history를 사용하여 중복된 방향을 탐색하는경우 시간을 줄이도록 했음
	 */
	
    static final boolean verbose = false;
     
    static int startX, startY;
     
    static int[][] map;
    static int[] dx = {0,1,0,-1}; // 0 : 우  1 : 하  2 : 좌  3: 상
    static int[] dy = {1,0,-1,0}; // i증가 -> 시계방향   i 감소 -> 반시계방향
     
    static int[][][] history;
     
    static int[][] worm = new int[11][4];
    static int result =0;
    static Ball ball;
     
    static class Ball{
        int x;
        int y; 
        int dir;
         
        Ball(int x, int y, int dir) {
            this.x=x;
            this.y=y;
            this.dir=dir;
        }
         
        void move() {
            x += dx[dir];
            y += dy[dir];
            while(map[x][y] == 0 && (x!=startX || y!=startY)) {
                x += dx[dir];
                y += dy[dir];
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        ball = new Ball(0,0,0);
         
        for(int t=1; t<=tc; t++) {
            result=0;
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            map = new int[n+2][n+2];
            history = new int[n+2][n+2][4];
            boolean[][][] blacked = new boolean[n+2][n+2][4];
            worm = new int[11][4];
            Arrays.fill(map[0], 5);
            Arrays.fill(map[n+1], 5);
             
            for(int i=1; i<n+1; i++) {
                st = new StringTokenizer(br.readLine());
                 
                map[i][0] = 5;
                for(int j=1; j<n+1; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if(num>=6 && num<=10) {
                        if(worm[num][0] == 0) {
                            worm[num][0] = i;
                            worm[num][1] = j;
                        }
                        else {
                            worm[num][2] = i;
                            worm[num][3] = j;
                        }
                    }
                    map[i][j] = num;
                }
                map[i][n+1] = 5;
            }
             
            for(int i=1; i<n+1; i++) {
                for(int j=1; j<n+1; j++) {
                    if(map[i][j] != 0) continue;
                    for(int k=0; k<4; k++) {
                        if(history[i][j][k] !=0) continue;
                        ball.x=i; ball.y=j; ball.dir=k; startX = i; startY = j;
                        boolean first = true;
                        int score = 0;
                        w: while(true) {
                             
                            int op = map[ball.x][ball.y];
                            if((ball.x == startX) && (ball.y == startY) && !first) {
                                break w;
                            }
                            first = false;
                             
                            if(history[ball.x][ball.y][ball.dir]!=0) {
                                if(!blacked[ball.x][ball.y][ball.dir])
                                    score += history[ball.x][ball.y][ball.dir] + score;
                                else {
                                    score += history[ball.x][ball.y][ball.dir];
                                    blacked[i][j][k] = true;
                                }
 
                                break w;
                            }
                             
                            switch(op) {
                            case -1:
                                blacked[i][j][k] = true;
                                break w;
                            case 0:
                                ball.move();
                                break;
                            case 1:
                                if(ball.dir == 0 || ball.dir == 3) {
                                    score = score*2+1;
                                    break w;
                                }
                                else if(ball.dir == 1) {
                                    ball.dir = 0;
                                }
                                else if(ball.dir == 2) {
                                    ball.dir=3;
                                }
                                score++;
                                ball.move();
                                break;
                            case 2:
                                if(ball.dir == 0 || ball.dir == 1) {
                                    score = score*2+1;
                                    break w;
                                }
                                else if(ball.dir == 2) {
                                    ball.dir = 1;
                                }
                                else if(ball.dir == 3) {
                                    ball.dir=0;
                                }
                                score++;
                                ball.move();
                                break;
                            case 3:
                                if(ball.dir == 1 || ball.dir == 2) {
                                    score = score*2+1;
                                    break w;
                                }
                                else if(ball.dir == 3) {
                                    ball.dir = 2;
                                }
                                else if(ball.dir == 0) {
                                    ball.dir=1;
                                }
                                score++;
                                ball.move();
                                break;
                            case 4:
                                if(ball.dir == 2 || ball.dir == 3) {
                                    score = score*2+1;
                                    break w;
                                }
                                else if(ball.dir == 1) {
                                    ball.dir = 2;
                                }
                                else if(ball.dir == 0) {
                                    ball.dir=3;
                                }
                                score++;
                                ball.move();
                                break;
                            case 5:     
                                score = score*2+1;
                                break w;
                            default:    // 웜홀
                                if(ball.x == worm[op][0] && ball.y==worm[op][1]) {
                                    ball.x = worm[op][2];
                                    ball.y = worm[op][3];
                                }
                                else {
                                    ball.x = worm[op][0];
                                    ball.y = worm[op][1];
                                }
                                ball.move();
                                break;
                            }
                             
                        }
                        history[i][j][k] = score;
                        result= Math.max(score, result);
                    }
                }
            }
             
             
            sb.append(result).append("\n");
        }
         
        System.out.println(sb);
    }
 
}