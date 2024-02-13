import java.io.*;
import java.util.*;

class Node25691{
	int apple=0;
	int id;
	ArrayList<Node25691> nb = new ArrayList<>();		// 원래는 양방향으로 하려했는데 바꿔서 이름이 neighbor임 사실상 child
	Node25691 p = null;									// 부모 노드 
	
	Node25691(int id){ this.id = id; }
	
	void hasApple() {
		this.apple =1;
	}
	
	void setNb(Node25691 n) {
		nb.add(n);
		n.p = this;
	}
}

public class BOJ25691_k개트리노드에서사과를최대로수확하기 {
	
	static int result=0;
	static int size;
	static int maxApple;
	static int visited;
	static int k;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Node25691 root = new Node25691(-1);					// 더미 루트 노드 
		size = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		Node25691[] nodes = new Node25691[size];
		for(int i=0; i<size; i++) nodes[i]=new Node25691(i);
		
		for(int i=0; i<size-1; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[Integer.parseInt(st.nextToken())].setNb(nodes[Integer.parseInt(st.nextToken())]);
		}
		root.setNb(nodes[0]);
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<size; i++) if(Integer.parseInt(st.nextToken())==1) {nodes[i].hasApple(); maxApple++;}
		maxApple = (maxApple<k) ? maxApple : k;				// 먹을 수 있는 최대 apple 수
															// 불필요한 leaf node를 거르고 탐색하는 방식을 사용하기때문에 
															// 모든 노드를 방문해야하는경우 탈출코드로 사용
		dfs(nodes[0],k-1,0);
		//		0		1			2
		//		node	남은방문노드	현재apple수
		
		System.out.println(result);
	}

	private static void dfs(Node25691 n, int d, int res) {
		boolean firstVisit = ((visited&(1<<n.id))==0);
		if(res==maxApple) {result = res; return;}			// 무한루프 탈출코드
		
		// k번 방문 -> return
		if(d<0) {return;}
		if(firstVisit) res+=n.apple;
		if(d==0) {result = Math.max(result,res); return;}
		
		visited = visited|(1<<(n.id));						// 현재 노드 방문체크
		
		if(n.nb.size()>0) {
			for(Node25691 c : n.nb) {
				if ((visited&(1<<c.id))==0)	{				// 이미 가본 child이면 스킵
					dfs(c,d-1,res);							// 아니면 방문
				}
			}
		}
		if(n.p.id!=-1) 										
			if (n.apple==1) dfs(n.p,d,res);					// 사과가 있으면 현재 노드만 찍고 자식노드는 탐색하지 않는 경우 생성
			else {
				dfs(n.p,d+1,res);							// 사과가 없는경우 현재 노드를 방문하지 않고 탐색하는 경우 생성
			}
		
		result = Math.max(result,res);
	}

}
