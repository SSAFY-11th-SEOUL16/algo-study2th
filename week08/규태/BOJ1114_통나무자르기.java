import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1114_통나무자르기 {
	/*
	 * 이분 탐색 활용
	 */
	static int l,k,c,startpoint,anslength; //길이 위치 횟수 최소시작점 길이
	static ArrayList<Integer> position = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) {
			int next = Integer.parseInt(st.nextToken());
			position.add(next);
		} // 자를수 있는 포지션 저장
		Collections.sort(position);
		anslength=l;
		cutlength();
		System.out.println(anslength+" "+startpoint);
	}
	static void cutlength() {
		int left=1,right=l;
		for(int i=0; i<30; i++) { // 이분탐색시 30번 만에  가능 1000000000 <2^30
			int tmplen = (left+right)/2;
			int startcutpoint = trycutting(tmplen);
			if(startcutpoint==0) { //안되니까 더 긴 길이로
				left=tmplen+1;
			}
			else { //더 짧은 길이로 잘라보기
				right=tmplen-1;
				anslength = tmplen;
				startpoint=startcutpoint;
			}
		}
	}
	static int trycutting(int len) {
		int lastlen=l,cnt=0,startcutpoint=0;
		for(int i=k-1; i>=0; i--) {
			if(lastlen-position.get(i)>len) {
				if(i==k-1) return 0;
				lastlen = position.get(i+1);
				if(lastlen-position.get(i)>len) return 0;//안됨
				startcutpoint = position.get(i+1);
				cnt++;
			}
			if(cnt>=c) break;//더이상 자를수 없음
		}
		if(cnt<c) startcutpoint=position.get(0);
		if(startcutpoint>len) return 0;
		return startcutpoint;
	}
}