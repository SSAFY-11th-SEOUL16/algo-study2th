import java.util.*;
import java.io.*;

public class BOJ6080_최대유량 {
	
	/*
	 * - 84ms
	 * 
	 * - 빡구현(코드 설명 아래에)
	 */

    static class Node{
        int id;
        Set<Integer> nb;
        
        Node(int id){
            this.id=id;
            this.nb = new HashSet<>();
        }
        
    }
    
    // 다른곳으로 연결된게 없는 리프노드 삭제
    static void removeLeaf(int i) {
        if(i==0 || i==25 || nodes[i]==null) return;
        
        for(int n: nodes[i].nb) {
            nodes[n].nb.remove(i);
            if(nodes[n].nb.size()<=1) {
                removeLeaf(n);
            }
        }
        nodes[i]=null;
    }
    
    static void executeQuery(int i) {
        if(i==0 || i==25 || nodes[i]==null) return;
        
        if(nodes[i].nb.size()==2) {				// 직렬 연결 사이에 끼어있는 중간 노드 삭제
            Iterator<Integer> iter = nodes[i].nb.iterator();
            int n1 = iter.next();
            int n2 = iter.next();
            
            nodes[n1].nb.remove(i);
            nodes[n2].nb.remove(i);
            
            int newW = Math.min(w[n1][i], w[n2][i]);
            w[n1][n2]+=newW;					// n1과 n2 연결
            w[n2][n1]+=newW;
            nodes[n1].nb.add(n2);
            nodes[n2].nb.add(n1);
            
            query.add(n1);						// 연결 상태에 변경이 있는 node들 다시 한번 query
            query.add(n2);
            
            nodes[i] = null;
        }
        else if(nodes[i].nb.size()==1) {		// 다른곳에 연결되지 않은 리프노드 삭제
            removeLeaf(i);
        }
    }
    
    static int[][] w;
    static Node[] nodes;
    static Queue<Integer> query;
    
    static int c2i(char c) {
        if(c>='a' && c<='z') {
            return c-'a'+26;
        }
        else return c-'A';
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        nodes = new Node[52];
        w = new int[52][52];					// 병렬 연결 연산을 쉽게 하기 위해서 adj matrix 사용
        
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = c2i(st.nextToken().charAt(0));
            int c2 = c2i(st.nextToken().charAt(0));
            int n1 = Integer.parseInt(st.nextToken());

            if(nodes[c1]==null) {
                nodes[c1] = new Node(c1);
            }
            if(nodes[c2]==null) {
                nodes[c2] = new Node(c2);
            }
            
            nodes[c1].nb.add(c2);
            nodes[c2].nb.add(c1);
                
            w[c1][c2] += n1;
            w[c2][c1] += n1;
            
        }
        
        for(int i=0; i<52; i++) {
            if(i==0 || i==25 || nodes[i]==null) continue;
            if(nodes[i].nb.size()<=1) {
                removeLeaf(i);
            }
        }
        
        query = new LinkedList<>();
        for(int c=0; c<52; c++) {					//리프가 아닌 모든 노드에 대해서 최소 1회 query
            if(nodes[c]!=null)
                query.add(c);
        }
        
        while(!query.isEmpty()) {
            executeQuery(query.poll());
        }
        
        System.out.println(w[0][25]);				// A-Z로 압축
    }

}