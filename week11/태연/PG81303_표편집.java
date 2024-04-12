import java.util.*;

class Solution {

    /*
     *  - 링크드 리스트 쿼리 구현
     * 
     *  효율성  테스트
     *   테스트 1 〉	통과 (232.62ms, 127MB)
     *   테스트 2 〉	통과 (191.75ms, 122MB)
     *   테스트 3 〉	통과 (192.41ms, 122MB)
     *   테스트 4 〉	통과 (225.70ms, 128MB)
     *   테스트 5 〉	통과 (239.87ms, 127MB)
     *   테스트 6 〉	통과 (274.51ms, 133MB)
     *   테스트 7 〉	통과 (130.78ms, 97.9MB)
     *   테스트 8 〉	통과 (135.04ms, 98.8MB)
     *   테스트 9 〉	통과 (242.72ms, 138MB)
     *   테스트 10 〉	통과 (266.05ms, 133MB)
     */
    
    static class Node{
        int id;
        Node next;
        Node prev;
        
        Node(int id){
            this.id=id;
        }
        
        Node(int id, Node prev){
            this.id=id;
            this.prev=prev;
            prev.next=this;
        }
    }
    
    static class LList{
        int size;           // 표 크기
        Node ptr;           // 현재 커서 위치
        Node root;          // 리스트 시작점을 표시하기 위한 더미 노드
        Node tail;          // nullPointer 방지를 위한 더미 노드
        Deque<Node> temp;   // 삭제되는 배열을 담기 위한 Deque
        
        LList(int size, int ptr){
            this.size=size;
            this.root= new Node(-1);
            this.tail= new Node(Integer.MAX_VALUE);
            this.temp = new ArrayDeque<>();
            
            Node cur = this.root;
            for(int i=0; i<size; i++){
                Node newNode = new Node(i, cur);
                if(i==ptr) this.ptr = newNode;
                cur = newNode;
            }
            cur.next = tail;
            tail.prev = cur;
        }
        
        void up(int n){
            Node cur = this.ptr;
            for(int i=0; i<n; i++){
                cur = cur.prev;
            }
            this.ptr = cur;
        }
        
        void down(int n){
            Node cur = this.ptr;
            for(int i=0; i<n; i++){
                cur = cur.next;
            }
            this.ptr = cur;
        }
        
        void delete(){
            Node prev = this.ptr.prev;
            Node next = this.ptr.next;
            this.temp.addLast(this.ptr);
            prev.next = next;
            next.prev = prev;
            if(next.id==Integer.MAX_VALUE){
                this.ptr = prev;
            }
            else{
                this.ptr = next;
            }
        }
        
        void undo(){
            if(this.temp.isEmpty()) return;
            
            Node toRecover = this.temp.pollLast();
            
            Node prev = toRecover.prev;
            Node next = toRecover.next;
            prev.next = toRecover;
            next.prev = toRecover;
        }
        
        String getStatus(){
            StringBuilder ret = new StringBuilder();
            Node cur = this.root;
            for(int i=0; i<size; i++){
                if(cur.next.id==i){
                    ret.append("O");
                    cur = cur.next;
                }
                else{
                    ret.append("X");
                }
            }
            
            return ret.toString();
        }
    }
    
    public String solution(int n, int k, String[] cmd) {
        
        LList list = new LList(n,k);
        
        for(String op : cmd){
            StringTokenizer st = new StringTokenizer(op);
            switch(st.nextToken()){
                case "U":
                    list.up(Integer.parseInt(st.nextToken()));
                    break;
                case "D":
                    list.down(Integer.parseInt(st.nextToken()));
                    break;
                case "C":
                    list.delete();
                    break;
                case "Z":
                    list.undo();
                    break;
            }
            //System.out.println(list.getStatus() + " " +list.ptr.id);
        }
        return list.getStatus();
    }
}