/*
18892KB 264ms
*/
import java.io.*;
import java.util.*;

public class BOJ14725_개미굴 {
	static class Data {
		String path;
		int k;
		public Data(String path, int k) {
			this.path = path;
			this.k = k;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		int N = Integer.parseInt(input.readLine());
		Data[] data = new Data[N];
		for(int i = 0; i < N; i++) {
			String tmp = input.readLine();
			int k = Integer.parseInt(tmp.substring(0, tmp.indexOf(' ')));
			String path = tmp.substring(tmp.indexOf(' ') + 1);
			data[i] = new Data(path, k);
		}
		Arrays.sort(data, (o1, o2) -> o1.path.compareTo(o2.path));
		String[][] paths = new String[N][];
		StringTokenizer tokens;
		for(int i = 0; i < N; i++) {
			paths[i] = new String[data[i].k];
			tokens = new StringTokenizer(data[i].path);
			for(int j = 0; j < paths[i].length; j++) {
				paths[i][j] = tokens.nextToken();
			}
		}
		String[] prev = {};
		String base = "--";
		for(int i = 0; i < N; i++) {
			int j = 0;
			int M = Math.min(prev.length, paths[i].length);
			for(; j < M; j++) {
				if(!prev[j].equals(paths[i][j])) {
					break;
				}
			}
			for(; j < paths[i].length; j++) {
				for(int k = 0; k < j; k++) {
					output.append(base);
				}
				output.append(paths[i][j]).append('\n');
			}
			prev = paths[i];
		}
		System.out.print(output);
	}
}