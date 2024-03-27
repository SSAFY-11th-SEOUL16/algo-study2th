import java.io.*;
import java.util.*;

public class BOJ20926_얼음미로 {
	/*
	 * - 860ms
	 * 
	 * - 모든 바위주변 4면과 시작점 도착점을 Node로 만든 뒤 다익스트라 적용
	 * 
	 * - 코드 굉장히 더러움
	 */

	static int[][] map;
	static int h,w;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	static boolean inRange(int x, int y) {
		return (x>=0 && x<h && y>=0 && y<w);
	}
	
	static int pos2int(int x, int y) {
		return x*500+y;
	}
	
	static void setRock(int[] pos) {
		int x = pos[0]; int y = pos[1];
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(!inRange(nx,ny)) continue;
			
			if(map[nx][ny]>=0)
				setEdge(nx,ny, (i+2)%4);
			
		}
	}
	
	static void setEdge(int x, int y, int dir) {
		int loc = pos2int(x,y);
		Node my;
		if(nodes[loc]==null) nodes[loc] =new Node(loc);
		my = nodes[loc];
		
		next : for(int i=0; i<4; i++) {
			
			if(i==dir) continue;
			
			int destX=x; int destY=y; int w = 0;
			
			destX+=dx[i]; destY+=dy[i];
			
			while(inRange(destX, destY)) {
				if(map[destX][destY]>=0) {
					w += map[destX][destY];
				}
				else if(map[destX][destY]==-1) {
					continue next;
				}
				else if(map[destX][destY]==-2) {
					if(!inRange(destX-dx[i], destY-dy[i])) continue next;
					
					int p = pos2int(destX-dx[i], destY-dy[i]);
					if(nodes[p]==null) {
						nodes[p] = new Node(p);
					}
					nodes[loc].adj.add(new Edge(my,nodes[p],w));
					continue next;
				}
				else if(map[destX][destY]==-3) {
					nodes[loc].adj.add(new Edge(my,nodes[pos2int(destX,destY)],w));
					continue next;
				}
				destX+=dx[i]; destY+=dy[i];
			}
			
		}
	}
	
	static void setEnd(int[] pos) {
		int x = pos[0]; int y = pos[1];
		int p = pos2int(x,y);
		nodes[p] = new Node(p);
	}
	
	static class Node implements Comparable<Node>{
		int pos;
		List<Edge> adj;
		int w;
		
		Node(int x){
			pos = x;
			adj = new ArrayList<>();
			w = Integer.MAX_VALUE;
		}
		
		public int compareTo(Node e) {
			return this.w-e.w;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		Node from;
		Node dest;
		int w;
		
		Edge(Node from, Node dest, int w){
			this.from=from;
			this.dest=dest;
			this.w=w;
		}
		
		public int compareTo(Edge e) {
			return this.w-e.w;
		}
	}
	
	static Node[] nodes;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		map = new int [h][w];
		Queue<int[]> rocks = new LinkedList<>();
		v = new boolean[500*h+w];
		nodes = new Node[500*h+w];
		
		int[] tera = {0,0}; int[] end = {0,0};
		
		for(int i=0; i<h; i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j=0; j<w; j++) {
				char c = arr[j];
				switch(c) {
				case 'R':
					map[i][j] = -2;
					rocks.add(new int[] {i,j});
					break;
				case 'H':
					map[i][j] = -1;
					break;	
				case 'E':
					map[i][j] = -3;
					end = new int[] {i,j};
					break;
				case 'T':
					map[i][j] = 0;
					tera = new int[] {i,j};
					break;
				default:
					map[i][j] = c-'0';
					break;	
				}
			}
		}
		
		for(int i=0; i<h; i++) {
			Arrays.toString(map[i]);
		}
		
		setEnd(end);
		while(!rocks.isEmpty()) {
			setRock(rocks.poll());
		}
		setEdge(tera[0], tera[1], -1); 
		nodes[pos2int(tera[0],tera[1])].w= 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(nodes[pos2int(tera[0],tera[1])]);
		
		nodes[pos2int(end[0],end[1])].w = Integer.MAX_VALUE;
		int last = pos2int(end[0],end[1]);
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(v[node.pos]) continue;
			v[node.pos]= true; 
			
			for(int i=0; i<node.adj.size(); i++) {
				Edge e = node.adj.get(i);
				if(e.dest.w > e.w + e.from.w) {
					e.dest.w = e.w + e.from.w;
					pq.add(e.dest);
				}
			}
			
			if(node.pos==last) break;
		}
		
		if(nodes[pos2int(end[0],end[1])].w == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(nodes[pos2int(end[0],end[1])].w);
		}
		
	}

}
