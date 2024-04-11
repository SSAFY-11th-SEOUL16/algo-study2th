import java.util.*;

class Solution {

    /*
     *  - 링크드 리스트 쿼리 구현 배열 ver
     * 
     * 효율성  테스트
     *  테스트 1 〉	통과 (149.03ms, 112MB)
     *  테스트 2 〉	통과 (150.99ms, 112MB)
     *  테스트 3 〉	통과 (372.70ms, 112MB)
     *  테스트 4 〉	통과 (171.25ms, 113MB)
     *  테스트 5 〉	통과 (186.28ms, 113MB)
     *  테스트 6 〉	통과 (174.67ms, 113MB)
     *  테스트 7 〉	통과 (120.80ms, 95.3MB)
     *  테스트 8 〉	통과 (241.82ms, 95.9MB)
     *  테스트 9 〉	통과 (191.99ms, 113MB)
     *  테스트 10 〉	통과 (216.01ms, 119MB)
     */
    
    static class LList{
        
        int size;
        int ptr;
        int[] next;
        int[] prev;
        boolean[] dead;
        Deque<Integer> temp;
        
        LList(int size, int ptr){
            this.size=size;
            
            // 0번 index와 size+1번 index를 dummy head, tail로 사용
            this.next = new int[size+2];
            this.prev = new int[size+2];
            this.temp = new ArrayDeque<>();
            this.dead = new boolean[size+2];
            
            for(int i=0; i<=size+1; i++){
                this.next[i] = i+1;
                this.prev[i] = i-1;
            }
            
            this.ptr=ptr+1;
        }
        
        void up(int n){
            int cur = this.ptr;
            for(int i=0; i<n; i++){
                cur = prev[cur];
            }
            this.ptr = cur;
        }
        
        void down(int n){
            int cur = this.ptr;
            for(int i=0; i<n; i++){
                cur = next[cur];
            }
            this.ptr = cur;
        }
        
        void delete(){
            int prev = this.prev[this.ptr];
            int next = this.next[this.ptr];
            
            this.temp.addLast(this.ptr);
            this.dead[this.ptr]=true;
            
            this.next[prev] = next;
            this.prev[next] = prev;
            
            if(next==this.size+1){
                this.ptr = prev;
            }
            else{
                this.ptr = next;
            }
        }
        
        void undo(){
            if(this.temp.isEmpty()) return;
            
            int toRecover = this.temp.pollLast();
            
            int prev = this.prev[toRecover];
            int next = this.next[toRecover];
            this.next[prev] = toRecover;
            this.prev[next] = toRecover;
            
            this.dead[toRecover]=false;
        }
        
        String getStatus(){
            StringBuilder ret = new StringBuilder();
            
            for(int i=1; i<=size; i++){
                if(this.dead[i])
                    ret.append("X");
                else
                    ret.append("O");
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
            
        }
        return list.getStatus();
    }
}