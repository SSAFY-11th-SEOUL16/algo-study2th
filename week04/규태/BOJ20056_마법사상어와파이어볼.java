import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20056_마법사상어와파이어볼 {
	static int n,m,k,totalmass=0;
	static ArrayList<Fireball>[][] map;
	static int[][] move = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	
	static class Fireball{
		int row,col,mass,dir,speed;

		public Fireball(int row, int col, int mass, int dir, int speed) {
			super();
			this.row = row;
			this.col = col;
			this.mass = mass;
			this.dir = dir;
			this.speed = speed;
		}

		@Override
		public String toString() {
			return "Fireball [row=" + row + ", col=" + col + ", mass=" + mass + ", dir=" + dir + ", speed=" + speed
					+ "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new ArrayList[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int ri = Integer.parseInt(st.nextToken());
			int ci = Integer.parseInt(st.nextToken());
			int mi = Integer.parseInt(st.nextToken());
			int si = Integer.parseInt(st.nextToken());
			int di = Integer.parseInt(st.nextToken());
			map[ri-1][ci-1].add(new Fireball(ri-1,ci-1,mi,di,si));
		}
		
		for(int i=1; i<=k; i++)
			move();
		
		System.out.println(totalmass);
	}
	static void move() {
		ArrayList<Fireball> temp = new ArrayList<Fireball>();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j].isEmpty()) continue;
				for(int idx=map[i][j].size()-1; idx>=0; idx--) {
					Fireball cur = map[i][j].get(idx);
					cur.row = (i+cur.speed*move[cur.dir][0])%n;
					cur.col = (j+cur.speed*move[cur.dir][1])%n;
					
					if(cur.row<0)
						cur.row+=n;
					if(cur.col<0)
						cur.col+=n;
					
					temp.add(cur);
					map[i][j].remove(idx);
				}
			}
		}
		
//		System.out.println(temp.toString());
		for(int i=0; i<temp.size(); i++) {
			map[temp.get(i).row][temp.get(i).col].add(temp.get(i));
		}
		
		totalmass=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int size = map[i][j].size();
				if(size==0) continue;
				else if(size==1) {
					totalmass+=map[i][j].get(0).mass;
					continue;
				}
				
				int masshap=0,speedhap=0,odd=0;
				for(int idx=size-1; idx>=0; idx--) {
					Fireball cur = map[i][j].get(idx);
					masshap+=cur.mass;
					speedhap+=cur.speed;
					if(cur.dir%2==1)
						odd++;
					map[i][j].remove(idx);
				}
				
				int newmass = masshap/5,newspeed = speedhap/size;
				if(newmass == 0) continue;
				totalmass += newmass*4;
				if(odd==0 || odd==size) {
					for(int d=0; d<8; d=d+2) {
						map[i][j].add(new Fireball(i, j, newmass, d, newspeed));
					}
				}
				else {
					for(int d=1; d<8; d=d+2) {
						map[i][j].add(new Fireball(i, j, newmass, d, newspeed));
					}
				}
			}
		}
	}
}