package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ7490_0만들기 {
	static char[] list = {'+','-',' '};
	static char[] op;
	static int n,nonblank;
	static List<String> zeroFormula;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int test = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=test; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			op = new char[n]; // 1~n-1까지 저장하자
			
			zeroFormula = new ArrayList<String>();
			
			formula(1);
			
			Collections.sort(zeroFormula);
			for(String str : zeroFormula)
				sb.append(str).append('\n');
			
			sb.append('\n');
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}
	static void formula(int cnt) {
		if(cnt==n) {
			if(isZero()) {
				String str="";
				for(int i=1; i<n; i++)
					str=str+i+op[i];
				str=str+n;
				zeroFormula.add(str);
			}
			return;
		}
		for(int i=0; i<3; i++) {
			op[cnt]=list[i];
			formula(cnt+1);
		}
	}
	static boolean isZero() {
		int temp=1,sum=0,nonblank=0;
		
		List<Integer> arr = new ArrayList<Integer>(); 
		for(int i=1; i<n; i++) {
			switch(op[i]) {
			case '+':
				arr.add(temp);
				temp = i+1;
				nonblank++;
				break;
			case '-':
				arr.add(temp);
				temp = -(i+1);
				nonblank++;
				break;
			case ' ':
				temp = temp>0 ? temp*10+i+1:temp*10-(i+1);
			}
		}
		if(arr.size()<=nonblank)
			arr.add(temp);
		
		for(int val: arr) {
			sum+=val;
		}
		if(sum==0) {
			return true;
		}
		return false;
	}
}
