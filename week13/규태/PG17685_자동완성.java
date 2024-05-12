import java.util.*;
class PG17685_자동완성 {
	// 트라이 자료구조 활용
    public class Node {
        Map<Character,Node> child = new HashMap<>();
        int cnt=0;
        boolean end;
    }
    public class Trie {
        Node root = new Node();
        void insert(String word){
            Node cur = root;
            for(int i=0; i<word.length(); i++) { // 없으면 새로 노드 만들어줌
                cur = cur.child.computeIfAbsent(word.charAt(i),key -> new Node());
                cur.cnt++; // 들어온 횟수 카운팅
            }
            cur.end=true; // 종료노드 설정
        }
        int search(String word){
            Node cur = root;
            for(int i=0;i<word.length(); i++){
                cur = cur.child.get(word.charAt(i)); // 항상 있으므로 getOrDefault 메서드 사용하지 않아도 됨
                if(cur.cnt==1) return i+1; // 현재 노드 경로로 한번만 입력됐다면 즉시 리턴
            }
            return word.length(); // 끝까지 확인한 경우 = 단어 전체 입력해야 유일한 경우이므로 단어 길이 리턴  
        }
    }
    public int solution(String[] words) {
        Trie trie = new Trie();
        for(int i=0; i<words.length; i++)
            trie.insert(words[i]);
        
        int answer = 0;
        for(int i=0; i<words.length; i++)
            answer+=trie.search(words[i]);
        return answer;
    }
}
/*
테스트 1 〉	통과 (0.71ms, 75.1MB)
테스트 2 〉	통과 (0.79ms, 72.5MB)
테스트 3 〉	통과 (0.76ms, 71.5MB)
테스트 4 〉	통과 (0.83ms, 75.9MB)
테스트 5 〉	통과 (0.84ms, 75.5MB)
테스트 6 〉	통과 (188.03ms, 270MB)
테스트 7 〉	통과 (0.75ms, 74.3MB)
테스트 8 〉	통과 (150.25ms, 267MB)
테스트 9 〉	통과 (0.83ms, 79.4MB)
테스트 10 〉	통과 (0.74ms, 75.8MB)
테스트 11 〉	통과 (0.81ms, 72.5MB)
테스트 12 〉	통과 (159.09ms, 265MB)
테스트 13 〉	통과 (161.41ms, 288MB)
테스트 14 〉	통과 (156.27ms, 347MB)
테스트 15 〉	통과 (0.70ms, 74.9MB)
테스트 16 〉	통과 (159.33ms, 273MB)
테스트 17 〉	통과 (159.08ms, 278MB)
테스트 18 〉	통과 (0.75ms, 72.9MB)
테스트 19 〉	통과 (151.04ms, 349MB)
테스트 20 〉	통과 (148.95ms, 270MB)
테스트 21 〉	통과 (155.27ms, 359MB)
테스트 22 〉	통과 (283.61ms, 362MB)
 */