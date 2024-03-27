import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW5650_핀볼게임 {
	static int n,map[][],maxpoint=0;
	static int move[][] = {{1,0},{0,1},{-1,0},{0,-1}}; // 하0 우1 상2 좌3
	//changedir -> 맵위의 0-> 그대로, 1~4와 부딪힐때 장애물과 현재방향 idx(0~3)에 해당되는 방향으로 전환 
	static int changedir[][] = {{0,1,2,3},{1,3,0,2},{2,3,1,0},{2,0,3,1},{3,2,0,1},{2,3,0,1}}; 
	static ArrayList<int[]>[] wormhole = new ArrayList[5]; //6~10 -> 0~4번 wormhole arraylist
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		for(int test=1; test<=t; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			map = new int[n+2][n+2];
			
			for(int i=0; i<5; i++) {
				wormhole[i] = new ArrayList<>();
			}
			
			for(int i=0; i<n+2; i++) {
				map[i][0] = 5;
				map[i][n+1] = 5;
			}
			for(int j=0; j<n+2; j++) {
				map[0][j] = 5;
				map[n+1][j] = 5;
			}
			
			for(int i=1; i<=n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>=6) {
						wormhole[map[i][j]-6].add(new int[] {i,j});
					}
				}
			}
			
			maxpoint=0;
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(map[i][j]==0) {
						play(i,j);
					}
				}
			}
			
			sb.append("#").append(test).append(" ").append(maxpoint).append("\n");
        }
        System.out.println(sb);
	}
	static void play(int sr, int sc) {
		for(int dir=0; dir<4; dir++) { // 현위치에서 상하좌우 방향으로 play 시도 
			int hit=0; int d=dir;
			int r=sr+move[d][0],c=sc+move[d][1];
			boolean flag=true;
			while(flag) {
//				System.out.println("row - "+r+"\t col - "+c+"\t dir - "+d);
				if(map[r][c]==0) {
					if(r==sr && c==sc) {
						flag=false;
					}
					r+=move[d][0]; c+=move[d][1];
				}
				else if(map[r][c]==-1) {
					flag=false;
				}
				else if(map[r][c]<=5) {//현재 위치의 장애물 정보에 따른 방향 변화, 다음 위치 계산
					d=changedir[map[r][c]][d];
					r+=move[d][0]; c+=move[d][1];
					hit++;
				}
				else { //6~10
					int[] hole1 = wormhole[map[r][c]-6].get(0);
					int[] hole2 = wormhole[map[r][c]-6].get(1);
					if(r==hole1[0] && c==hole1[1]) {
						r=hole2[0]+move[d][0]; c=hole2[1]+move[d][1];
					}
					else {
						r=hole1[0]+move[d][0]; c=hole1[1]+move[d][1];
					}
				}
			}
			if(maxpoint<hit)
				maxpoint=hit;
//			System.out.println(hit);
		}
	}
}
