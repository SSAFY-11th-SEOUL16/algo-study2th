import java.io.*;
import java.util.*;

public class BOJ2317_결혼식 {
	
	/*
	 *  - 96ms
	 * 
	 *  - 사자 범위 내의 사람들은 사자 사이에 정렬, 사자 범위 밖의 사람들에 대해 위치 지정
	 */
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int diffLion=0;
		
		int firstLion = Integer.parseInt(br.readLine());
		
		int maxLion=firstLion;
		int minLion=firstLion;
		int lastLion=firstLion;
		
		for(int i=1; i<k; i++) {
			
			int lion = Integer.parseInt(br.readLine());
			
			if(lion>maxLion) {
				maxLion = lion;
			}
			
			if(lion<minLion) {
				minLion = lion;
			}
			
			diffLion += Math.abs(lastLion-lion);
			
			lastLion = lion;
		}
				
		int maxPerson=-1;
		int minPerson=Integer.MAX_VALUE;
		
		for(int i=0; i<n-k; i++) {
			
			int person = Integer.parseInt(br.readLine());
			
			if(person>maxPerson) {
				maxPerson = person;
			}
			
			if(person<minPerson) {
				minPerson = person;
			}
		}
		
		if(maxPerson>maxLion) {
			if(maxLion==Math.max(firstLion, lastLion)) {
				diffLion += maxPerson-maxLion;
			} else {
				diffLion += Math.min((maxPerson-maxLion)*2,
						maxPerson-Math.max(firstLion, lastLion));
			}
		} 

		if(minPerson<minLion) {
			if(minLion==Math.min(firstLion, lastLion)) {
				diffLion += minLion-minPerson;
			} else {
				diffLion += Math.min((minLion-minPerson)*2,
						Math.min(firstLion, lastLion)-minPerson);
			}
		}
		
		System.out.println(diffLion);
	}
}
