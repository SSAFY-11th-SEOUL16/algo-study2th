import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ21944_문제추천시스템Version2 {
	/*
	 * 924ms
	 * TreeSet 검색 후 학습하며 활용
	 * 직관적으로 카테고리, 레벨로 분류하여 가져오는 방식
	 */
	static int n,m;
	static class Problem {
		int no,level,category;
		public Problem(int no, int level, int category) {
			super();
			this.no = no;
			this.level = level;
			this.category = category;
		}
	}
	static Comparator<Problem> c = new Comparator<Problem>() {
		@Override
		public int compare(Problem o1, Problem o2) {
			if(o1.level==o2.level)	return o1.no-o2.no;
			return o1.level-o2.level;
		}
	};
	static Map<Integer,Problem> plist = new HashMap<>();
	static TreeSet<Problem> treeset = new TreeSet<>(c);
	static TreeSet<Problem>[] categoryset = new TreeSet[101];
	static TreeSet<Problem>[] levelset = new TreeSet[101];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=1; i<=100; i++) {
			categoryset[i] = new TreeSet<>(c);
			levelset[i] = new TreeSet<>(c);
		}
		
		n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			add(p,l,g);
		}
		
		m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			String action = st.nextToken();
			if(action.equals("recommend")) {
				int g = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				System.out.println(recommend(g,x));
			}
			else if(action.equals("recommend2")) {
				int x = Integer.parseInt(st.nextToken());
				System.out.println(recommend2(x));
			}
			else if(action.equals("recommend3")) {
				int x = Integer.parseInt(st.nextToken());	
				int l = Integer.parseInt(st.nextToken());
				System.out.println(recommend3(l,x));
			}
			else if(action.equals("add")) {
				int p = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				int g = Integer.parseInt(st.nextToken());
				add(p,l,g);
			}
			else {
				int p = Integer.parseInt(st.nextToken());
				remove(p);
			}
		}
	}
	static void add(int p, int l, int g) {
		Problem prblm = new Problem(p,l,g);
		plist.put(p, prblm);
		treeset.add(prblm);
		levelset[l].add(prblm);
		categoryset[g].add(prblm);
	}
	static int recommend(int g,int x) {
		if(x==1) return categoryset[g].last().no;
		else return categoryset[g].first().no;
	}
	static int recommend2(int x) {
		if(x==1) return treeset.last().no;
		else return treeset.first().no;
	}
	static int recommend3(int l,int x) {
		int ans=-1;
		if(x==1) {
			for(int lvl=l; lvl<=100; lvl++) {
				if(!levelset[lvl].isEmpty()) {
					ans = levelset[lvl].first().no;
					break;
				}
			}
		}
		else {
			for(int lvl=l-1; lvl>0; lvl--) {
				if(!levelset[lvl].isEmpty()) {
					ans = levelset[lvl].last().no;
					break;
				}
			}
		}
		return ans;
	}
	static void remove(int p) {
		Problem prblm = plist.get(p);
		plist.remove(p);
		treeset.remove(prblm);
		levelset[prblm.level].remove(prblm);
		categoryset[prblm.category].remove(prblm);
	}
}
