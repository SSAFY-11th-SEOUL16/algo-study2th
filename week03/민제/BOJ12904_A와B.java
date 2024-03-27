import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BOJ12904_A와B {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        List<Character> S = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            S.add(c);
        }

        Stack<Character> T = new Stack<>();
        input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            T.add(c);
        }

        while (T.size() != S.size()) {

            Character last = T.peek();

            if (last == 'A') {
                while (T.size() != S.size() && T.peek() == 'A') T.pop();
            } else {
                T.pop();
                Stack<Character> temp = new Stack<>();
                while (!T.isEmpty()) {
                    temp.add(T.pop());
                }
                T = temp;
            }
        }

        int result = 1;

        List<Character> temp = new ArrayList<>(T);

        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i) != S.get(i)) {
                result = 0;
                break;
            }
        }

        System.out.println(result);

    }
}
