import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14225 {

	static int n;
	static int[] arr;
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < (1 << n); i++) {
			int sum = 0;
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					sum += arr[j];
				}
			}
			set.add(sum);
		}

		List<Integer> lists = new ArrayList<>(set);
		lists.sort((o1, o2) -> o1 - o2);

		int num = 1;
		int pos = 0;
		while (true) {

			if (lists.get(pos) != num) {
				System.out.println(num);
				break;
			}

			if (pos < lists.size() - 1) {
				pos++;
			}
			num++;

		}

	}

}
