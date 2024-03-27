import java.io.*;
import java.util.*;
import java.util.Map.Entry;
 
public class SW5648_원자소멸시뮬레이션 {
	/*
	 *  - 250ms
	 * 
	 * 	주어진 데이터를 x[i], y[i], d[i], k[i]의 배열로 저장
	 * 
	 *  x,y,d를 활용하여 모든 i,j에 대해 충돌지점 계산 
	 *  
	 *  유효한 모든 충돌지점 {i,j,time} 으로 저장 후 time에 대해 정렬
	 *  
	 *  같은 시간동안 발생한 모든 유효한(boolean alive[]로 관리) 충돌인자를 set으로 저장
	 *  
	 *  set에 존재하는 모든 원소 i 에 대해 res += k[i], alive[i]=false;
	 * 
     */
	static final boolean verbose = false;
     
    final static int[][] dir = {{0,1},{0,-1},{-1,0},{1,0}};
    static int n;
     
    static int collide(int x1, int y1, int d1, int x2, int y2, int d2) {
        if(d1==d2) return -1;
         
        if(d1==0) {
            if(d2==1) {
                if(x1!=x2 || y1>y2) return -1;
                else return (y2-y1)/2;
            }
            if(d2==2) {
                if(x2-x1 == y2-y1) return y2-y1; 
            }
            if(d2==3) {
                if(y2-y1 == x1-x2) return y2-y1;
            }
        }
        else if(d1==1) {
            if(d2==0) {
                if(x1!=x2 || y1<y2) return -1;
                else return (y1-y2)/2;
            }
            if(d2==2) {
                if(y1-y2 == x2-x1) return y1-y2;
            }
            if(d2==3) {
                if(y1-y2 == x1-x2) return y1-y2;
            }
        }
        else if(d1==2) {
            if(d2==3) {
                if(y1!=y2 || x1<x2) return -1;
                else return (x1-x2)/2;
            }
            if(d2==0) {
                if(x1-x2==y1-y2) return x1-x2;
            }
            if(d2==1) {
                if(x1-x2==y2-y1) return x1-x2;
            }
        }
        else if(d1==3) {
            if(d2==2) {
                if(y1!=y2 || x1>x2) return -1;
                else return (x2-x1)/2;
            }
            if(d2==0) {
                if(x2-x1 == y1-y2) return x2-x1;
            }
            if(d2==1) {
                if(x2-x1 == y2-y1) return x2-x1;
            }
        }
        return -1;
    }
 
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
         
         
        for(int t=1; t<=tc; t++) {
            int res=0;
             
            n = Integer.parseInt(br.readLine());
            int[] x = new int[n];
            int[] y = new int[n];
            int[] d = new int[n];
            int[] k = new int[n];
             
            boolean[] alive = new boolean[n];
            Arrays.fill(alive,true);
             
            for(int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                x[i] = (Integer.parseInt(st.nextToken())+1000)*2;
                y[i]= (Integer.parseInt(st.nextToken())+1000)*2;
                d[i] = Integer.parseInt(st.nextToken());
                k[i] = Integer.parseInt(st.nextToken());
            }
             
            List<int[]> allCol = new ArrayList<>();
            for(int i=0; i<n; i++) {
                for(int j=i+1; j<n; j++) {
                    int time = collide(x[i],y[i],d[i],x[j],y[j],d[j]);
                    if(time>0) allCol.add(new int[] {i,j,time});
                }
            }
            allCol.sort((int[] o1, int[] o2)-> o1[2]-o2[2]);
             
            for(int i=0; i<allCol.size(); i++) {
                int[] cur = allCol.get(i);
                if(!alive[cur[0]] || !alive[cur[1]]) continue;
                 
                Set<Integer> ids = new HashSet<>();
                ids.add(cur[0]); ids.add(cur[1]);
                i++;
                while(i<allCol.size() && allCol.get(i)[2]==cur[2]) {
                    int[] c = allCol.get(i++);
                    if(!alive[c[0]] || !alive[c[1]]) continue;
                    ids.add(c[0]); ids.add(c[1]);
                }
                i--;
                for(int id: ids) {
                    res += k[id];
                    alive[id]=false;
                    //System.out.println("#"+id+" dead : "+res);
                }
            }
             
            sb.append("#").append(t).append(" ").append(res).append("\n");
        }
         
        System.out.println(sb);
         
         
    }
 
}