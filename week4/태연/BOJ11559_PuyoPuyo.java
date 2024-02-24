import java.io.*;
import java.util.*;

public class BOJ11559_PuyoPuyo {
	/*
	 *  - 224ms
	 *  
	 *  - 한번에 여러개내려보려다가 잘 안돼서 시간 포기하고 하나씩 내리는방식으로 변경
	 * 
	 * 
	 */
	
	static boolean verbose = false;
	
	static char[][] map = new char [12][6];
	static boolean[][] v;
	static boolean end=false;
	static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
	
	static boolean inRange(int x, int y) {
		if(x>=0 && x<12 && y>=0 && y<6) return true;
		return false;
	}
	
	static void delete(List<int[]> arr) {
		arr.sort((int[] o1, int[] o2)->o1[0]-o2[0]);	// 가능하면 맨 위에서부터 터트림
		for(int[] a: arr) {
			pang(a);
		}
	}
	
	static void pang(int[] a) {
		int x = a[0];
		int y = a[1];
		for(int i=x; i>0; i--) {
			map[i][y] = map[i-1][y];					// 한칸씩 다 내리고
			if(map[i][y]=='.') break;
		}
		map[0][y]='.';									// 맨 위를 빈칸으로 
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<12; i++) map[i] = br.readLine().toCharArray();
		
		int strike=0;
		
		while(!end) {
			if(verbose)
			for(int i=0; i<12; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			if(verbose)
			System.out.println("--");
			List<int[]> toDelete = new ArrayList<>();
			end = true;
			v = new boolean[12][6];
			
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(map[i][j]=='.' || v[i][j]) continue;
					
					char puyo = map[i][j];
					Queue<int[]> q = new LinkedList<>();
					v[i][j]=true;
					q.add(new int[]{i,j});
					int cnt=0;
					List<int[]> totoDelete = new ArrayList<>();
					//System.out.println("--");
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						//System.out.println(Arrays.toString(cur));
						cnt++;
						totoDelete.add(new int[] {cur[0],cur[1]});
						
						for(int d=0; d<4; d++) {
							int nx = cur[0]+dir[d][0];
							int ny = cur[1]+dir[d][1];
							if(!inRange(nx,ny)) continue;
							
							if(!v[nx][ny] && map[nx][ny]==puyo) {
								v[nx][ny] = true;
								q.add(new int[] {nx,ny});
							}
						}
					}
					
					if (cnt>=4) {
						for(int[] a : totoDelete) {
							toDelete.add(a);
						}
						end = false;
					}
					
				}
			}
			
			if(!end) strike++;
			delete(toDelete);
		}
		if(verbose)
		for(int i=0; i<12; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println(strike);
	}

}
