import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class SW4008_숫자만들기 {
    static int n,num[],op[],order[];
    static int max,min;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
         
        for(int test=1; test<=t; test++) {
            st  = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            op = new int[4];
            order = new int[n-1];
            num = new int[n];
             
            st  = new StringTokenizer(br.readLine());
            for(int i=0; i<4; i++) {
                op[i] = Integer.parseInt(st.nextToken());
            }
             
            st  = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            max=Integer.MIN_VALUE;
            min=Integer.MAX_VALUE;
            permu(0);
             
            sb.append("#").append(test).append(" ").append(max-min);
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static void permu(int cnt) {
        if(cnt==n-1) {
//          System.out.println(Arrays.toString(order));
            calculate();
            return;
        }
        for(int i=0; i<4; i++) {
            if(op[i]==0) continue;
            op[i]--;
            order[cnt]=i;
            permu(cnt+1);
            op[i]++;
        }
    }
    static void calculate() {
        Stack<Integer> s = new Stack<>();
        s.push(num[0]);
        for(int i=1; i<n; i++) {
            int tmp = s.pop();
            if(order[i-1]==0)
                s.push(tmp+num[i]);
            else if(order[i-1]==1)
                s.push(tmp-num[i]);
            else if(order[i-1]==2)
                s.push(tmp*num[i]);
            else
                s.push(tmp/num[i]);
        }
        int result = s.pop();
        if(max<result) max=result;
        if(min>result) min=result;
    }
}