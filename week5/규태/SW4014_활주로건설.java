import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class SW4014_활주로건설 {
    static int n,l,ans=0,road[][];
    static int[][] dir = {{1,0},{0,1}}; // 0 - 행, 1 - 열방향 체크
    static Stack<Integer> s;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
         
        for(int test=1; test<=t; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            road = new int[n][n];
             
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    road[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans=0;
            for(int col=0; col<n; col++)
                checkline(0,col,0); // 아래방향 체크
            for(int row=0; row<n; row++)
                checkline(row,0,1); // 우측방향 체크
             
            sb.append("#").append(test).append(" ").append(ans); //소수 첫째 자리에서 반올림
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static void checkline(int srow, int scol, int d) {
        s = new Stack<>();
        int row=srow; int col=scol; int before=-1;
         
        while(inRange(row,col)) { //맘에 안들때마다 return
            if(s.isEmpty()){
                if(row==srow && col==scol) 
                    s.push(road[row][col]);
                else if(before==road[row][col]+1) { //스택 비워지고 다시 내려가는 경우
                    for(int i=1; i<=l; i++) {
                        if(!inRange(row,col) || before!=road[row][col]+1) return;
                        row+=dir[d][0]; col+=dir[d][1];
                    }
                    before = road[row-dir[d][0]][col-dir[d][1]];
                    continue;
                }
                else if(before==road[row][col]) { //스택 비워지고 동일 높이인 경우
                    s.push(road[row][col]);
                }
                else    //스택 비워지고 다시 올라가는 경우, 올라가기 위한 경사로를 만들수 없으므로 리턴  ex)3 2 3
                    return;
            }
            else {
                if(s.peek()<road[row][col]-1) return; //2이상 차이
                else if(s.peek()==road[row][col]-1) { //1일때 스택에 l개 pop 시도
                    for(int i=1; i<=l; i++) {
                        if(s.isEmpty()) return;
                        int popped = s.pop();
                        if(popped!=road[row][col]-1) return;
                    }
                }
                else if(s.peek()==road[row][col]+1) { //1차이일때 스택에 뒤에 나올 l개 확인후 스택 비우기
                    for(int i=1; i<=l; i++) {
                        if(!inRange(row,col) || s.peek()!=road[row][col]+1) return;
                        row+=dir[d][0]; col+=dir[d][1];
                    }
                    before = road[row-dir[d][0]][col-dir[d][1]];
                    s.clear(); //뒤의 l개를 확인했고, 그 이후와 스택에 있는 내용은 이제 무의미하므로 비우기
                    continue;
                }
                else if(s.peek()>road[row][col]+1) return; // 2이상 차이
                 
                s.push(road[row][col]);
            }
//          System.out.println(row+"\t"+col+"\t"+s.toString());
            row+=dir[d][0]; col+=dir[d][1]; // 다음으로 
        }
//      System.out.println(srow+" "+scol+" "+d);
        ans+=1;
    }
    static boolean inRange(int row, int col) {
        return (row>=0 && row<n && col>=0 && col<n);
    }
}