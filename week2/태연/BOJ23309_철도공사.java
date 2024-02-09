import java.io.*;
import java.util.*;

public class BOJ23309_철도공사 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb= new StringBuilder();
		int [][]nodeArr = new int[1000001][2];
		int j;
			
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int idx = Integer.parseInt(st.nextToken());
		int firstIdx = idx;
		int temp = idx;
		for(int i=1;i<n;i++) {
			idx = Integer.parseInt(st.nextToken());
			nodeArr[idx][0] = temp;
			nodeArr[temp][1] = idx;
            temp = idx;
		}
		
		nodeArr[temp][1] = firstIdx;
		nodeArr[firstIdx][0] = temp;
        String op;
        int id;
        int next, nnext, prev, pprev;
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			op = st.nextToken();
			id = Integer.parseInt(st.nextToken());
			
			// node 		0 			1			
			//				previdx		nextidx	
		
			switch(op) {
				case"BN":
					j = Integer.parseInt(st.nextToken());
					
					next = nodeArr[id][1];
					sb.append(next).append("\n");

					nodeArr[j][1]=next;
					nodeArr[j][0]=id;
					
					nodeArr[next][0]=j;
					nodeArr[id][1]=j;

					break;
				case"BP":
					j = Integer.parseInt(st.nextToken());
					
					prev = nodeArr[id][0];
					sb.append(prev).append("\n");

					nodeArr[j][1]=id;
					nodeArr[j][0]=prev;

					
					nodeArr[prev][1] = j;
					nodeArr[id][0]= j;

					break;
				case"CP":
					
					prev = nodeArr[id][0];
					sb.append(prev).append("\n");

					pprev = nodeArr[prev][0];
					nodeArr[pprev][1] = id;
					nodeArr[id][0] = pprev; 
					break;
				case"CN":
					next=nodeArr[id][1];
					sb.append(next).append("\n");
					
					nnext=nodeArr[next][1];
					nodeArr[id][1] = nnext;
					nodeArr[nnext][0] = id; 
					break;
			}
		}
		System.out.println(sb);
	}

}
