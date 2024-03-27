import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ20926_얼음미로 {
	/*
	 * 구슬탈출1~4 문제와 유사한 방식으로 해결
	 */
	static int w,h,exit[],time=Integer.MAX_VALUE;
	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	static char[][] miro;
	static int[][] visit;
	static PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
		public int compare(int[] o1, int[] o2) {
			return o1[2]-o2[2];
		}
	});
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		miro = new char[h+2][w+2];
		visit = new int[h+2][w+2];
		exit = new int[2];
		
		for(int i=0; i<h+2; i++) {
			miro[i][0]='H';
			miro[i][w+1]='H';
			Arrays.fill(visit[i], Integer.MAX_VALUE);
		}
		for(int j=0; j<w+2; j++) {
			miro[0][j]='H';
			miro[h+1][j]='H';
		}
		
		for(int i=1; i<=h; i++) {
			String line = br.readLine();
			for(int j=1; j<=w; j++) {
				miro[i][j]=line.charAt(j-1);
				if(miro[i][j]=='T') {
					pq.offer(new int[] {i,j,0});
					miro[i][j]='0';
				}
				else if(miro[i][j]=='E') {
					exit[0]=i; exit[1]=j;
				}
			}
		}
		
		bfs();
		if(time!=Integer.MAX_VALUE)
			System.out.println(time);
		else 
			System.out.println(-1);
	}
	static void bfs() {
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
//			System.out.println(Arrays.toString(cur));
			if(cur[0]==exit[0] && cur[1]==exit[1]) {
				if(time>cur[2])
					time=cur[2];
				return;
			}
			for(int[] d:dir) {
				int ni=cur[0],nj=cur[1],move=1,tmp=0;
				while(true) {
					if(miro[ni+d[0]*move][nj+d[1]*move]=='H')
						break;
					else if(miro[ni+d[0]*move][nj+d[1]*move]=='R') {
						move--; 
						if(visit[ni+d[0]*move][nj+d[1]*move]>cur[2]+tmp) {
							visit[ni+d[0]*move][nj+d[1]*move]=cur[2]+tmp;
							pq.offer(new int[] {ni+d[0]*move,nj+d[1]*move,cur[2]+tmp});
						}
						break;
					}
					else if(miro[ni+d[0]*move][nj+d[1]*move]=='E') {
						if(visit[ni+d[0]*move][nj+d[1]*move]>cur[2]+tmp) {
							visit[ni+d[0]*move][nj+d[1]*move]=cur[2]+tmp;
							pq.offer(new int[] {ni+d[0]*move,nj+d[1]*move,cur[2]+tmp});
						}
						break;
					}
					else {
						tmp+=miro[ni+d[0]*move][nj+d[1]*move]-'0';
						move++;
					}
//					System.out.println((ni+d[0]*move)+"   "+(nj+d[1]*move)+"   "+move+"   "+tmp);
				}
			}
		}
	}
}

