package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ2531_회전초밥 {
	static int n,d,k,c,max=0;
	static List<Integer> initialk = new ArrayList<>();
	static List<Integer> list = new ArrayList<>();
	static Set<Integer> set = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); //총접시
		d = Integer.parseInt(st.nextToken()); //가짓수
		k = Integer.parseInt(st.nextToken()); //먹을접시수
		c = Integer.parseInt(st.nextToken()); //쿠폰번호
		int sushie;
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			sushie = Integer.parseInt(st.nextToken());
			
			initialk.add(sushie);
			list.add(sushie);
			if(sushie!=c) {
				set.add(sushie);
			}
		}
		
		for(int i=k; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			sushie = Integer.parseInt(st.nextToken());
		
			selectk(sushie);
		}
		for(int i=0; i<k; i++) {
			selectk(initialk.get(i));
		}
		
		System.out.println(max+1);
	}
	static void selectk(int next) {
		if(list.contains(next)) {
			int front = list.get(0);
			list.remove(0);
			list.add(next);
			if(front!=next && !list.contains(front)) {
				set.remove(front);
			}
		}
		else {
			int front = list.get(0);
			list.remove(0);
			list.add(next);
			if(!list.contains(front))
				set.remove(front);
			if (next!=c) 
				set.add(next);
		}
		
		if(max<set.size())
			max=set.size();
	}
}
