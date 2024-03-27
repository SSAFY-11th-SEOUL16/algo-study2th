import java.io.*;
import java.util.*;

public class SW4008_숫자만들기 {
	
	/*
	 *  - 162ms
	 * 
	 *  - 중복조합 사용
	 */
	
	static int n,max,min;
	static int[] nums, ops;
	static StringTokenizer st;
	
	static void init() {
		max=Integer.MIN_VALUE;
		min=Integer.MAX_VALUE;
		n = Integer.parseInt(st.nextToken());
		nums = new int[n];
		ops = new int[n-1];
	}
	
	static void cal(int[] op) {
		int ptr = 1;
		int res = nums[0];
		for(int i=0; i<n-1; i++) {
			switch(op[i]) {
			case 0:
				res += nums[ptr++];
				break;
			case 1:
				res -= nums[ptr++];
				break;
			case 2:
				res *= nums[ptr++];
				break;
			case 3:
				res /= nums[ptr++];
				break;
			}
		}
		max = Math.max(max, res);
		min = Math.min(min, res);
	}
	
	static void dfs(int size, int[] curOp, int v) {
		if(size==n-1) {
			
			cal(curOp);
		}
		else {
			int before = -1;
			for(int i=0; i<n-1; i++) {
				if(ops[i]==before) continue;
				if((v&(1<<i))>0) continue;
				
				curOp[size] = ops[i];
				before = ops[i];
				dfs(size+1, curOp, (v|(1<<i)));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input4008.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=tc; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			
			init();
			
			int size=0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				int num = Integer.parseInt(st.nextToken());
				for(int iter =0; iter<num; iter++) ops[size++] = i;
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0,new int[n-1], 0);
			//System.out.println(max+" "+min);
			sb.append(max-min).append("\n");
		}
		
		System.out.println(sb);
	}

}
