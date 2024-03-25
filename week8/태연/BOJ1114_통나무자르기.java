import java.io.*;
import java.util.*;

public class BOJ1114_통나무자르기 {
	/*
	 *  - 220ms
	 * 
	 *  - 최대 길이를 파라미터로 두고 이분탐색
	 */

	static int isPossible(int size, TreeSet<Integer> set, int l, int maxCnt) {
		int cur = l;
		Object temp;
		int cnt = 0;
		
		while(cur-size>0) {
			if((temp = set.ceiling(cur-size))==null || cur==(int) temp) return -1;
			else {
				cur = (int) temp;
				cnt++;
				if(cnt>maxCnt) return -1;
			}
		}
		if(cnt==maxCnt) return cur;		// 횟수 다 썼으면 마지막 위치
		else return set.first();		// 횟수가 남았으면 시작점에서 가장 가까운곳에서 한번 더 자름
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int l = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		TreeSet<Integer> set = new TreeSet<>(); 
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		int minSize = (int) Math.ceil((double)l/(c+1));	// 이론상 최대길이
		
		int left = minSize; int right = l; int mid=0; int minL=l; int result=0;
		
		
		while(left<right) {
			mid = (left+right)/2;
			int temp;
			if((temp= isPossible(mid, set, l, c))>=0) { // mid길이로 되면
				minL = mid;
				right = mid;		// 더 줄여보고
				result = temp;
			}
			else {					// 안되면
				left = mid+1;		// 늘려
			}
		}
		
		System.out.println(minL + " " + result);
	}

}
