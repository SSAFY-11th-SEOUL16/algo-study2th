import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ23295_스터디시간정하기1 {
	/*
	 * 420ms
	 * 프로그래머스 모의코테 누젝합 문제를 연상하며 해결
	 * 어디서부터 1,2,3,4,5에 1을 모두 추가해야한다면 1에 +1 6에 -1을 저장하는 방식
	 * 한번 누적합 하면 시간대별 가능한 인원 배열 생성
	 * 이 상태에서 한번 더 누적합을 하면 시간만족도의 누적합 생성
	 * 그대로 누적합하며 기존 배열을 덮어씌우는 과정에서 배열의 범위나 index에서 사용을 유의해야할 필요가 있다 
	 */
	static int len=100000;
	static int n,t;
	static int[] time = new int[len+2];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		for(int i=0; i<n; i++) {
			int k = Integer.parseInt(br.readLine());
			for(int j=0; j<k; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				time[s]++; time[e]--; //시간대별 가능한 인원 구하기 이전의 상태
			}
		}
		for(int i=1; i<len+2; i++) //누적합1회  -> 시간대별 가능한 인원
			time[i]+=time[i-1];
		for(int i=1; i<len+2; i++) //누적합2회  -> 시간만족도 합 
			time[i]+=time[i-1];
		
		int max=time[t-1]; int anstime=-1; // 그중 시간만족도 max 구간 구하기
		for(int i=0; i<len+1-t; i++) {
			int tmp = time[t+i]-time[i];
			if(max<tmp) {
				max=tmp; anstime=i;
			}
		}
		System.out.println(anstime+1+" "+(anstime+t+1));
	}
}
