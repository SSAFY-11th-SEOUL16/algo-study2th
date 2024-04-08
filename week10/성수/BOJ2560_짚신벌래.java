// 120 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int p = 1000;
    static int a, b, d, N;
	static int head;
	static int getIdx(int idx) {
		return (idx + head) % d;
	}

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        a = Integer.parseInt(tokens.nextToken());
        b = Integer.parseInt(tokens.nextToken());
        d = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());
        int[] deque = new int[d];
        head = 0;
        deque[0] = 1;
        int replicate = 0;
        long total = 1;
        for(int i = 1; i <= N; i++) {
        	replicate -= deque[getIdx(b - 1)];
        	replicate += deque[getIdx(a - 1)];
        	total -= deque[getIdx(d - 1)];
        	deque[getIdx(d - 1)] = 0;
        	head = (head + d - 1) % d;
        	deque[getIdx(0)] = replicate % p;
        	total += replicate;
        }
        System.out.println(total % p);
    }
}