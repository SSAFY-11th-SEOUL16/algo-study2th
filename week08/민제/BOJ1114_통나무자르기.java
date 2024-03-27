import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1114_통나무자르기 {

	static int L, K, C;
	static HashSet<Integer> hs = new HashSet<>();
	static List<Integer> pos;
	static List<Integer> canCut;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			hs.add(Integer.parseInt(st.nextToken()));
		}
		hs.add(0);
		hs.add(L);
		canCut = new ArrayList<>(hs);
		Collections.sort(canCut);
		
		int left = 0;
		int right = L;
		
		int divide_num = 0;
		
		while (left < right) {
			int mid = (left + right) / 2;
			divide_num = valid(mid);
			
			if (divide_num > C) {
				left = mid + 1;
			}
			else {
				right = mid;
			}
		}
		
		int firstCut = 0;
		int now_len = 0;
		
		for(int i = canCut.size()-1; i >= 1; i--) {
			int diff = canCut.get(i) - canCut.get(i-1);
			if (now_len + diff > left) {
				now_len = diff;
				firstCut = canCut.get(i);
				C--;
				continue;
			}
			now_len += diff;
		}
		
		if (C > 0 ) {
			System.out.println(left + " " + canCut.get(1));
		}
		else {
			System.out.println(left + " " + firstCut);
		}
		
	}

	private static int valid(int mid) {
		
		int now_len = 0;
		int num = 0;
		
		for(int i=0; i<canCut.size()-1; i++) {
			
			int diff = canCut.get(i+1) - canCut.get(i);
			if (diff > mid) return C+2;
			
			if (now_len + diff > mid) {
				num++;
				now_len = diff;
				continue;
			}
			
			now_len+= diff;
		}
		
		return num;
	}
	

}
