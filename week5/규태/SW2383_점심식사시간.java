import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
 
public class SW2383_점심식사시간 {
    static int n,min,map[][], sinfo[][];
    static ArrayList<Integer>[] stairs = new ArrayList[2]; //계단
    static ArrayList<Person> plist; 
     
    static class Person{
        int i,j,down;
 
        public Person(int i, int j, int down) {
            super();
            this.i = i;
            this.j = j;
            this.down = down;
        }
        @Override
        public String toString() {
            return "Person [i=" + i + ", j=" + j + ", down=" + down + "]";
        }
    }
     
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
         
        for(int test=1; test<=t; test++) {
            st  = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            sinfo = new int[2][2];
            plist = new ArrayList<>();
             
            int idx=0;
            for(int i=0; i<n; i++) {
                st  = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j]==1)
                        plist.add(new Person(i,j,0));
                    else if(map[i][j]>1) {
                        sinfo[idx][0]=i;	// 계단 정보 저장
                        sinfo[idx++][1]=j;
                    }
                }
            }
            stairs[0] = new ArrayList<>();
            stairs[1] = new ArrayList<>();
            min = Integer.MAX_VALUE;
            subset(0);
             
            sb.append("#").append(test).append(" ").append(min);
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static void subset(int idx) {
        if(idx==plist.size()) {
            arrive(); // 서로소집합으로 나눈후 도착시간 계산
            return;
        }
 
        for(int i=0; i<2; i++) {
            stairs[i].add(idx);
            subset(idx+1);
            stairs[i].remove(stairs[i].size()-1);
        }
    }
    static void arrive() { //계단에 도착시간 계산
    	//두번씩 적을 필요는 없었으나 코드 작성 당시 조금 더 직관적이고 꼼꼼하게 하기 위해 두번씩 작성했었습니다.. 
        int[][] tostairs = new int[2][];
        tostairs[0] = new int[stairs[0].size()];
        tostairs[1] = new int[stairs[1].size()];
        for(int i=0; i<stairs[0].size(); i++) {
            tostairs[0][i] = Math.abs(plist.get(stairs[0].get(i)).i-sinfo[0][0])
                            +Math.abs(plist.get(stairs[0].get(i)).j-sinfo[0][1]);
        }
        for(int i=0; i<stairs[1].size(); i++) {
            tostairs[1][i] = Math.abs(plist.get(stairs[1].get(i)).i-sinfo[1][0])
                            +Math.abs(plist.get(stairs[1].get(i)).j-sinfo[1][1]);
        }
        Arrays.sort(tostairs[0]);
        Arrays.sort(tostairs[1]);
//      System.out.println(Arrays.toString(tostairs[0]));
//      System.out.println(Arrays.toString(tostairs[1]));
//      System.out.println();
         
        // sort 후 큐 예상 탈출 시간을 넣고, 3명이 넘는 경우 맨 앞의 탈출 시간과 비교해 탈출시간 보정
        // 이 역시 두번 반복해서 적을 필요는 없었지만 단순히 복붙해 작성했습니다.
        Deque<Integer> q = new ArrayDeque<>();
        int time1=0;
        for(int i=0; i<stairs[0].size(); i++) {
            if(q.size()<3) {
                q.offer(tostairs[0][i]+map[sinfo[0][0]][sinfo[0][1]]+1);
            }
            else {
                int popped = q.poll();
                if(popped<=tostairs[0][i])
                    q.offer(tostairs[0][i]+map[sinfo[0][0]][sinfo[0][1]]+1);
                else
                    q.offer(popped+map[sinfo[0][0]][sinfo[0][1]]);
            }
        }
        while(!q.isEmpty()) { // 마지막 사람의 탈출시간 = 최종 걸린 시간
            time1 = q.poll();
        }
//      System.out.println(time1);
         
        int time2=0;
        for(int i=0; i<stairs[1].size(); i++) {
            if(q.size()<3) {
                q.offer(tostairs[1][i]+map[sinfo[1][0]][sinfo[1][1]]+1);
            }
            else {
                int popped = q.poll();
                if(popped<=tostairs[1][i])
                    q.offer(tostairs[1][i]+map[sinfo[1][0]][sinfo[1][1]]+1);
                else
                    q.offer(popped+map[sinfo[1][0]][sinfo[1][1]]);
            }
        }
        while(!q.isEmpty()) {
            time2 = q.poll();
        }
//      System.out.println(time2);
        int cur = Math.max(time1, time2);
        if(min>cur) {
//          System.out.println(cur);
            min = cur;
        }
    }
}
