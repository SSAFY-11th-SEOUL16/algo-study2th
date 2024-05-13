class PG17685_자동완성 {
	
	/*
	 * - 정확성  테스트
	 * 테스트 1 〉	통과 (0.33ms, 83.1MB)
	 * 테스트 2 〉	통과 (0.48ms, 78.2MB)
	 * 테스트 3 〉	통과 (0.31ms, 68.3MB)
	 * 테스트 4 〉	통과 (0.30ms, 78.8MB)
	 * 테스트 5 〉	통과 (0.34ms, 74.8MB)
	 * 테스트 6 〉	통과 (81.82ms, 214MB)
	 * 테스트 7 〉	통과 (0.36ms, 73.9MB)
	 * 테스트 8 〉	통과 (76.89ms, 204MB)
	 * 테스트 9 〉	통과 (0.31ms, 73.6MB)
	 * 테스트 10 〉	통과 (0.31ms, 77MB)
	 * 테스트 11 〉	통과 (0.31ms, 72.1MB)
	 * 테스트 12 〉	통과 (79.90ms, 216MB)
	 * 테스트 13 〉	통과 (84.66ms, 196MB)
	 * 테스트 14 〉	통과 (80.57ms, 285MB)
	 * 테스트 15 〉	통과 (0.35ms, 71.3MB)
	 * 테스트 16 〉	통과 (90.40ms, 218MB)
	 * 테스트 17 〉	통과 (89.88ms, 211MB)
	 * 테스트 18 〉	통과 (0.46ms, 76.9MB)
	 * 테스트 19 〉	통과 (74.38ms, 260MB)
	 * 테스트 20 〉	통과 (107.16ms, 202MB)
	 * 테스트 21 〉	통과 (88.62ms, 274MB)
	 * 테스트 22 〉	통과 (149.96ms, 276MB)
	 * 
	 * - 트라이 구현
	 * 
	 */
    
    static class Node{
        Node[] next;
        int nNext;
        
        Node(){
            this.next = new Node[26];
            this.nNext = 0;
        }
    }
    
    static class Trie{
        Node root;
        
        Trie(){
            this.root = new Node();
        }
        
        void add(String word){
            Node cur = this.root;
            for(int i=0; i<word.length(); i++){
                int nextChar = char2int(word.charAt(i));
                
                if(cur.next[nextChar] == null){
                    cur.next[nextChar] = new Node();
                }
                cur = cur.next[nextChar];
                cur.nNext++;
            }
        }
        
        int find(String word){
            Node cur = this.root;
            for(int i=0; i<word.length(); i++){
                int nextChar = char2int(word.charAt(i));
                
                if(i!=0 && cur.nNext==1) {
                    return i;
                }
                cur = cur.next[nextChar];
            }
            
            return word.length();
        }
    }
    
    static int char2int(char c){
        return c-'a';
    }
    
    public int solution(String[] words) {
        
        Trie trie = new Trie();
        for(String word: words){
            trie.add(word);
        }
        
        int answer = 0;
        
        for(String word: words){
            answer += trie.find(word);
        }

        return answer;
    }
}