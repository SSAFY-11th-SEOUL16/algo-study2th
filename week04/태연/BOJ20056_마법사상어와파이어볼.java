import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class BOJ20056_마법사상어와파이어볼 {
	/*
	 *  - 524ms
	 * 
	 *  - 파이어볼별로 위치를 int로 mapping하여 파이어볼이 존재하는 모든 위치에 대해 탐색
	 *  
	 */
	static final boolean verbose = false;
	
	final static int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	static int n;
	final static int[][] dirSet = {{0,2,4,6},{1,3,5,7}};
	
	static class FB{
		int x;
		int y;
		int m;
		int d;
		int s;
		
		FB(int x, int y, int m, int s, int d){
			if(verbose) System.out.println("new FB " + x + " " + y + " " + m + " " + s + " " + d);

			this.x=x;
			this.y=y;
			
			this.m=m;
			this.d=d;
			this.s=s;
		}
		
		void move() {
			int dx = (x+dir[d][0]*s)%n;
			int dy = (y+dir[d][1]*s)%n;
			if(dx < 0) x = n + dx;
			else x = dx;
			
			if(dy < 0) y = n + dy;
			else y = dy;
			
		}
		
		int getHash() {
			return((x<<6)+y);
		}
	}
	
	static void collide(int loc, List<FB> l) {
		if (verbose) System.out.println("x: "+(loc>>6)+" y: "+(loc%64)+" l size: "+l.size());
		
		if(l.size()==1) {
			if(l.get(0).m==0) return;
			fireballs.add(l.get(0));
		}
		else {
			int newM=0;
			int newS=0;
			int size=0;
			int dir=-1;
			int[] dirs = dirSet[0];
			boolean isEven = true;
			
			for(FB fb : l) {
				size++;
				newM+=fb.m;
				newS+=fb.s;
				if(dir==-1) {
					isEven = (fb.d%2==0) ? true: false;
					dir=0;
				}
				else {
					if(isEven) {
						if(fb.d%2==1) dirs=dirSet[1];
					}
					else {
						if(fb.d%2==0) dirs=dirSet[1];
					}
				}
			}
			if(verbose) System.out.println("MS " + newM+ " " + newS);
			newM /= 5;
			newS /= size;
			int x = loc/64;
			int y = loc%64;
			if(newM==0) return;
			
			for(int i=0; i<4; i++) {
				fireballs.add(new FB(x,y,newM,newS, dirs[i]));
			}
		}
		
	}
	
	static Queue<FB> fireballs = new LinkedList<>();
	static HashMap<Integer, List<FB>> map = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		
		int nFB = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<nFB; i++) {
			st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken())-1;
			int y= Integer.parseInt(st.nextToken())-1;
			int m= Integer.parseInt(st.nextToken());
			int s= Integer.parseInt(st.nextToken());
			int d= Integer.parseInt(st.nextToken());
			if (m==0) continue;
			fireballs.add(new FB(x,y,m,s,d));
		}
		
		for(int i=0; i<k; i++) {
			
			map.clear();
			
			while(!fireballs.isEmpty()) {
				FB cur = fireballs.poll();
				cur.move();
				
				int hash = cur.getHash();
				if (verbose) System.out.println("hash: "+hash + " x :"+cur.x + " y :"+cur.y);
				if(!map.containsKey(hash)) {
					map.put(hash, new ArrayList<>());
				}
				map.get(hash).add(cur);
			}
			
			for(Entry<Integer, List<FB>> set : map.entrySet()) {
				if(verbose) System.out.println("set : " + set.getKey());
				collide(set.getKey(),set.getValue());
			}
		}
		
		int res=0;
		while(!fireballs.isEmpty()) {
			FB cur = fireballs.poll();
			res += cur.m;
		}
		
		System.out.println(res);
	}

}
