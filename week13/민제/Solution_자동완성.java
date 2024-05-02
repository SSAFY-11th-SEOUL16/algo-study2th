import java.util.*;

class Solution_자동완성 {

    static class Node {
        Character val;
        HashMap<Character, Node> childs =new HashMap<>();
        int cnt;
        boolean end;

        public Node(Character val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", cnt=" + cnt +
                    ", end=" + end +
                    '}';
        }
    }

    static class Trie{
        Node head = new Node(null);

        void add(String word) {
            Node cur = head;

            for(int i=0; i<word.length(); i++) {
                cur.cnt++;
                cur = cur.childs.computeIfAbsent(word.charAt(i), key -> new Node(key));
            }

            cur.cnt++;
            cur.end = true;
        }

    }

    static Trie trie;

    public static int solution(String[] words) {

        trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }
        int answer = bfs();
        return answer;
    }

    static int bfs() {

        int result = 0;

        Queue<Node> q = new ArrayDeque<>();
        q.add(trie.head);

        int nowLen = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Node poll = q.poll();

                if (poll.cnt == 1) {
                    result += nowLen;
                }
                if ( poll.cnt != 1 && poll.end) {
                    result += nowLen;
                }
                if (poll.cnt >= 2){
                    HashMap<Character, Node> childs = poll.childs;
                    Set<Character> characters = childs.keySet();

                    for (Character character : characters) {
                        q.add(childs.get(character));
                    }
                }

            }

            nowLen++;
        }

        return result;
    }

//    public static void main(String[] args) {
//        System.out.println(solution(new String[]{"go","gone","guild"}));
//        System.out.println(solution(new String[]{"abc","def","ghi","jklm"}));
//        System.out.println(solution(new String[]{"word","war","warrior","world"}));
//    }
}