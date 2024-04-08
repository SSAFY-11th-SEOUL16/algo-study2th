import java.util.*;
import java.io.*;
import java.math.*;
/*
 * 244ms
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		Queue<Node> youngQ = new LinkedList();
		Queue<Node> adultQ = new LinkedList();
		Queue<Node> oldQ = new LinkedList();

		int adultCnt = 0;
		int allCnt = 1;
		youngQ.add(new Node(0, 1));

		for (int i = 1; i <= n; i++) {
			if (!youngQ.isEmpty() && (i - youngQ.peek().birthDay) == a) {// 어른으로 승급
				Node newAdult = youngQ.poll();
				adultQ.add(newAdult);
				adultCnt += newAdult.count;
				adultCnt %= 1000;
			}
			if (!adultQ.isEmpty() && (i - adultQ.peek().birthDay) == b) { // 성체 탈출
				Node newOld = adultQ.poll();
				oldQ.add(newOld);
				adultCnt = adultCnt - newOld.count;
				if (adultCnt < 0)
					adultCnt += 1000;
				adultCnt %= 1000;
			}
			if (!oldQ.isEmpty() && (i - oldQ.peek().birthDay) == d) { // 사망
				Node die = oldQ.poll();
				allCnt = allCnt - die.count;
				if (allCnt < 0)
					allCnt += 1000;
				allCnt %= 1000;
			}

			if (adultCnt != 0) { // 0이 아닐때
				youngQ.add(new Node(i, adultCnt));
				allCnt += adultCnt;
				allCnt %= 1000;
			}

		}

		System.out.println(allCnt);
	}

	static class Node {
		int birthDay;
		int count;

		Node(int birthDay, int count) {
			this.birthDay = birthDay;
			this.count = count;
		}
	}
}