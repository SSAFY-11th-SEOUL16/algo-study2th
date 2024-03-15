import java.io.*;
import java.util.*;

public class SW2383_점심식사시간 {
	
	/*
	 *  - 144ms
	 *  
	 *  - 두개의 계단을 선택하는 모든 조합에 대해 각 계단의 소화 시간을 계산
	 */
	
	static class Person{
		int x;
		int y;
		int distA;
		int distB;
		
		Person(int x, int y){
			this.x=x;
			this.y=y;
		}
		
		void calDist(Stair A, Stair B) {
			distA = Math.abs(this.x-A.x) + Math.abs(this.y-A.y);
			distB = Math.abs(this.x-B.x) + Math.abs(this.y-B.y);
		}
	}
	
	static class Stair{
		int x;
		int y;
		int l;
		Stair(int x, int y, int l){
			this.x=x;
			this.y=y;
			this.l=l;
		}
	}
	
	static int n, pSize, time;
	static Person[] persons;
	static Stair A;
	static Stair B;
	static PriorityQueue<Integer> aq = new PriorityQueue<>();
	static PriorityQueue<Integer> bq = new PriorityQueue<>();
	
	static void init() {
		time=Integer.MAX_VALUE;
		pSize=0;
		persons = new Person[10];
		A=null;
		B=null;
	}
	
	static void down(int i) {
		ArrayList<Integer> aList = new ArrayList<>();
		ArrayList<Integer> bList = new ArrayList<>();
		aq.clear();
		bq.clear();
		int aTime=0;
		int bTime=0;
		
		for(int j=0; j<pSize; j++) {
			if((i&(1<<j))>0) {
				aList.add(persons[j].distA);
			}
			else {
				bList.add(persons[j].distB);
			}
		}
		
		Collections.sort(aList); Collections.sort(bList);
		
		int idx=0;
		while(idx!=aList.size()) {
			
			if(aList.get(idx) >= aTime) {aTime++; continue;}
			
			if(aq.size()<3) {aq.add(aTime); idx++;}
			else {
				if(aq.peek() + A.l <= aTime) {
					aq.poll();
				}
				else aTime++;
			}
		}
		while(!aq.isEmpty()) {
			if(aq.peek() + A.l <= aTime) aq.poll();
			else aTime++;
		}
		idx=0;
		while(idx!=bList.size()) {
			
			if(bList.get(idx) >= bTime) {bTime++; continue;}
			
			if(bq.size()<3) {bq.add(bTime); idx++;}
			else {
				if(bq.peek() + B.l <= bTime) {
					bq.poll();
				}
				else bTime++;
			}
		}
		while(!bq.isEmpty()) {
			if(bq.peek() + B.l <= bTime) bq.poll();
			else bTime++;
		}
		
		
		time = Math.min(time, Math.max(aTime,bTime));
		
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2383.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=tc; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num==0) continue;
					
					if(num==1) persons[pSize++] = new Person(i,j);
					else {
						if(A==null) A = new Stair(i,j, num);
						else B = new Stair(i,j, num);
					}
				}
			}
			
			for(int i=0; i<pSize; i++) {
				persons[i].calDist(A,B);
			}
			
			for(int i=0; i<(1<<pSize); i++) {
				down(i);
			}
			
			
			sb.append(time).append("\n");
		}
		
		System.out.println(sb);
	}

}
