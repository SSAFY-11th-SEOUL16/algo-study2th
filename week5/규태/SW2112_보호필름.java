import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class SW2112_보호필름 {
    static int n,m,k,min,film[][],changerow[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
         
        for(int test=1; test<=t; test++) {
            st  = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            film = new int[n][m];
             
            for(int i=0; i<n; i++) {
                st  = new StringTokenizer(br.readLine());
                for(int j=0; j<m; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            min=k;
            int change=0;
            while(change<k) {
                changerow = new int[change];
                combi(0,-1,change);
                 
                if(min!=k)
                    break;
                change++;
            }
             
            sb.append("#").append(test).append(" ").append(min);
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static void combi(int idx,int before, int cnt) {
        if(min!=k) return;
        if(idx==cnt) { // 바꿀 열과 기존 film 내용 복붙해 넘겨줌, 복붙 굳이 이렇게 할필요는 없음
//          System.out.println(Arrays.toString(changerow));
            int[][] tmp = new int[n][m];
            for(int i=0; i<n; i++)
                tmp[i] = Arrays.copyOf(film[i], m);
            inject(0,tmp);
            return;
        }
        for(int i=before+1; i<n; i++) {
            changerow[idx]=i;
            combi(idx+1,i,cnt);
        }
    }
    static void inject(int idx,int[][] tmp) { // 용액 집어넣기
        if(idx==changerow.length) {
//          show(tmp);
            boolean flag=true;
            for(int i=0; i<m; i++)
                if(!checkcol(i,tmp)) {
                    flag=false; break;
                }
            if(flag)
                min=changerow.length;
            return;
        }
        for(int i=0; i<m; i++)
            tmp[changerow[idx]][i]=0;
        inject(idx+1,tmp);
        for(int i=0; i<m; i++)
            tmp[changerow[idx]][i]=1;
        inject(idx+1,tmp);
    }
    static void show(int[][] tmp) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) 
                System.out.print(tmp[i][j]+" ");
            System.out.println();
        }
        System.out.println();
    }
    static boolean checkcol(int col,int[][] tmp) {
        Stack<Integer> s = new Stack<>();
        s.push(tmp[0][col]);
        boolean flag;
        for(int i=1; i<n; i++) { //다른 입력 들어오면 k개 pop하고 가능하면 비우고 push
            if(tmp[i][col]!=s.peek()) {
                flag=true;
                for(int n=1; n<=k; n++) {
                    if(s.isEmpty()) {
                        flag=false;
                        break;
                    }
                    int popped = s.pop();
                    if(popped==tmp[i][col]) {
                        flag=false;
                        break;
                    }
                }
                s.clear();
                if(flag) // 다른 문자 들어왔을 때 앞에 k개 같은 문자 뽑기 성공
                    return true;
            }
            s.push(tmp[i][col]);
        }
        if(s.size()>=k) // 끝날때 같은 문자 k개 이상
            return true;
        return false;
    }
}