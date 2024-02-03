import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    static int N;
    static StringBuilder answer;
    static String[] operator = {" ", "+", "-"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        answer = new StringBuilder();
        StringBuilder sb;
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            sb = new StringBuilder();
            sb.append(1);
            dfs(2, sb);
            answer.append("\n");
        }

        System.out.println(answer);

    }

    static void dfs(int depth, StringBuilder sb) {
        if (depth == N + 1) {
            if (calculate(sb.toString()) == 0)
                answer.append(sb + "\n");
            return;
        }
        for (int i = 0; i < 3; i++) {
            sb.append(operator[i] + depth);
            dfs(depth + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    static int calculate(String str) {
        Deque<String> deque = new ArrayDeque<>();

        String[] split = str.split("");
        for (int i = 0; i < split.length; i++) {
            String c = split[i];
            if (c.equals("-") || c.equals("+")) {
                deque.push(c);
            } else if (!c.equals(" ")) {
                if (!deque.isEmpty()) {
                    String preC = deque.pop();
                    if (preC.equals("-") || preC.equals("+")) {
                        deque.push(preC);
                        deque.push(c);
                    } else {
                        int value = Integer.parseInt(preC) * 10 + Integer.parseInt(c);
                        deque.push(value + "");
                    }
                } else {
                    deque.push(c);
                }
            }
        }
        int sum = Integer.parseInt(deque.removeLast());
        while (!deque.isEmpty()) {
            String operator = deque.removeLast();
            int operand = Integer.parseInt(deque.removeLast());
            if (operator.equals("+"))
                sum += operand;
            else
                sum -= operand;
        }
        return sum;

    }
}