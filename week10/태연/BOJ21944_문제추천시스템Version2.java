import java.io.*;
import java.util.*;

public class BOJ21944_문제추천시스템Version2 {
	
	/*
	 *  - 584ms
	 * 
	 *  - 트리셋을 활용한 Query처리
	 */

	static class Prob{
		int id;
		int dif;
		int cat;
		
		Prob(int id, int dif, int cat){
			this.id=id;
			this.dif=dif;
			this.cat=cat;
		}
	}
	
	static int executeQuery(String line) {
		StringTokenizer st = new StringTokenizer(line);
		
		int num, cat, dif;
		TreeSet<Prob> curTree;
		switch(st.nextToken()) {
			case "recommend":
				cat = nextInt(st);
				num = nextInt(st);
				curTree = clusterByCat[cat];
				if(num==1) {
					sb.append(curTree.last().id).append("\n");
				}
				else {
					sb.append(curTree.first().id).append("\n");
				}
				break;
			case "recommend2":
				num = nextInt(st);
				curTree = all;
				if(num==1) {
					sb.append(curTree.last().id).append("\n");
				}
				else {
					sb.append(curTree.first().id).append("\n");
				}
				break;
			case "recommend3":
				num = nextInt(st);
				dif = nextInt(st);
				curTree = all;
				Prob target;
				if(num==1) {
					target = curTree.ceiling(new Prob(0, dif, 0));
					if(target!=null)
						sb.append(target.id).append("\n");
					else
						sb.append(-1).append("\n");
				}
				else {
					target = curTree.lower(new Prob(0, dif, 0));
					if(target!=null)
						sb.append(target.id).append("\n");
					else
						sb.append(-1).append("\n");
				}
				break;
			case "solved":
				num = nextInt(st);
				Prob cur = probs[num];
				all.remove(cur);
				clusterByCat[cur.cat].remove(cur);
				probs[cur.id] = null;
				break;
			case "add":
				num = nextInt(st);
				dif = nextInt(st);
				cat = nextInt(st);
				Prob newProb = new Prob(num, dif, cat);
				probs[newProb.id]= newProb;
				all.add(newProb);
				clusterByCat[newProb.cat].add(newProb); 
				break;
		}
		
		
		
		return 0;
	}
	
	static int nextInt(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
	
	static Prob[] probs;
	static TreeSet<Prob> all;
	static TreeSet<Prob>[] clusterByCat;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = nextInt(st);
		probs= new Prob[100001];
		
		Comparator<Prob> byDif = new Comparator<Prob>() {
			public int compare(Prob a, Prob b) {
				if(a.dif==b.dif) return a.id-b.id; 
				return a.dif-b.dif;
			}
		};
		
		all = new TreeSet<>(byDif);
		
		clusterByCat = new TreeSet[101];
		for(int i=1; i<=100; i++) {
			clusterByCat[i] = new TreeSet<>(byDif);
		}
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			Prob newProb = new Prob(nextInt(st), nextInt(st), nextInt(st));
			probs[newProb.id]= newProb;
			all.add(newProb);
			clusterByCat[newProb.cat].add(newProb); 
		}
		
		st = new StringTokenizer(br.readLine());
		int m= nextInt(st);
		sb = new StringBuilder();
		
		for(int i=0; i<m; i++) {
			executeQuery(br.readLine());
		}
		
		System.out.println(sb);
	}

}
