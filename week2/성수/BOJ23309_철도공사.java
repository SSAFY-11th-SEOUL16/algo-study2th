import java.io.*;
import java.util.*;


public class BOJ23309_철도공사 {
	static int MAX_LEN = 1_000_000;
	
	public static void main(String[] args) throws IOException {
		int LEFT = 0;
		int RIGHT = 1;
		int[][] stations = new int[2][MAX_LEN + 1];
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		int N, M, i, j, k;
		StringTokenizer tokenizer = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokenizer.nextToken());
		M = Integer.parseInt(tokenizer.nextToken());
		tokenizer = new StringTokenizer(input.readLine());
		int head = Integer.parseInt(tokenizer.nextToken());
		i = head;
		j = head;
		// 2호선 초기화
		for(int n = 0; n < N - 1; n++) {
			j = Integer.parseInt(tokenizer.nextToken());
			stations[RIGHT][i]= j;
			stations[LEFT][j]= i;
			i = j;
		}
		stations[RIGHT][j]= head;
		stations[LEFT][head]= j;
		
		// M번의 공사
		for(int m = 0; m < M; m++) {
			tokenizer = new StringTokenizer(input.readLine());
			String operation = tokenizer.nextToken();
			switch(operation) {
			case "BN":
				i = Integer.parseInt(tokenizer.nextToken());
				j = Integer.parseInt(tokenizer.nextToken());
				k = stations[RIGHT][i];
				output.write(k+"\n");
				stations[RIGHT][i] = j;
				stations[LEFT][j] = i;

				stations[RIGHT][j] = k;
				stations[LEFT][k] = j;
				break;
			case "BP":
				k = Integer.parseInt(tokenizer.nextToken());
				j = Integer.parseInt(tokenizer.nextToken());
				i = stations[LEFT][k];
				output.write(i+"\n");
				stations[RIGHT][i] = j;
				stations[LEFT][j] = i;

				stations[RIGHT][j] = k;
				stations[LEFT][k] = j;
				break;
			case "CN":
				i = Integer.parseInt(tokenizer.nextToken());
				j = stations[RIGHT][i];
				k = stations[RIGHT][j];
				output.write(j+"\n");
				stations[RIGHT][i] = k;
				stations[LEFT][k] = i;
				break;
			case "CP":
				k = Integer.parseInt(tokenizer.nextToken());
				j = stations[LEFT][k];
				i = stations[LEFT][j];
				output.write(j+"\n");
				stations[RIGHT][i] = k;
				stations[LEFT][k] = i;
			}
		}
		output.close();
	}

}
