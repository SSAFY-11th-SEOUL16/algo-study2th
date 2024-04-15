import java.util.*;

/*
 * 테스트 1 〉	통과 (181.22ms, 141MB)
 * 테스트 2 〉	통과 (211.68ms, 126MB)
 * 테스트 3 〉	통과 (228.47ms, 130MB)
 * 테스트 4 〉	통과 (208.22ms, 133MB)
 * 테스트 5 〉	통과 (200.30ms, 160MB)
 * 테스트 6 〉	통과 (196.23ms, 133MB)
 * 테스트 7 〉	통과 (87.28ms, 101MB)
 * 테스트 8 〉	통과 (128.59ms, 101MB)
 * 테스트 9 〉	통과 (199.28ms, 130MB)
 * 테스트 10 〉	통과 (229.11ms, 144MB)
 */

class PG81303_표편집 {
    
    static class Node {

        int id;
        Node prev;
        Node next;
        boolean isDeleted;
        
        Node (int id, Node prev, Node next) {
            this.id = id;
            this.prev = prev;
            this.next = next;
            isDeleted = false;
        }
    
    }
    
    public String solution(int n, int k, String[] cmd) {
        StringBuilder sb = new StringBuilder();
        Stack<Node> delStack = new Stack<>();
        Node[] nodes = new Node[n];
        
        
        Node p = new Node(0, null, null);
        nodes[0] = p;
        for (int i = 1; i < n; i++) {
            Node now = new Node(i, p, null);
            nodes[i] = now;
            p.next = now;
            p = now;
        }
        Node idx = nodes[k];
        
        for (String s : cmd) {
            
            if (s.length() == 1) {
                // 현재 선택된 행 삭제, 바로 아래 행 선택, 삭제 행이 마지막인 경우 윗 행 선택
                if (s.equals("C")) {
                    delStack.push(idx);
                    delete(idx);
                    
                    if (idx.next != null) {
                        idx = idx.next;
                    }
                    else {
                        idx = idx.prev;
                    }
                }
                // 가장 최근에 삭제한 행 복구, 선택 행 변경 X
                else if (s.equals("Z")) {
                    Node reviveNode = delStack.pop();
                    revive(reviveNode, nodes);
                }
            }
            else {
                String token = s.substring(0, 1);
                int x = Integer.parseInt(s.substring(2, s.length()));
                // 현재 선택된 행에서 X칸 위에 있는 행 선택
                if (token.equals("U")){
                    Node node = idx;
                    for (int i = 0; i < x; i++) {
                        node = node.prev;
                    }
                    idx = node;
                }
                // 현재 선택된 행에서 X칸 아래 있는 행 선택
                else if (token.equals("D")) {
                    Node node = idx;
                    for (int i = 0; i < x; i++) {
                        node = node.next;
                    }
                    idx = node;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (nodes[i].isDeleted) {
                sb.append("X");
            }
            else {
                sb.append("O");
            }
        }
        
        return sb.toString();
    }
    
    public static void delete(Node delNode) {
        delNode.isDeleted = true;
        Node prevNode = delNode.prev;
        Node nextNode = delNode.next;
        if (prevNode != null)
            prevNode.next = nextNode;
        if (nextNode != null)
            nextNode.prev = prevNode;
    }
    
    public static void revive(Node reviveNode, Node[] nodes) {
        reviveNode.isDeleted = false;
        if (reviveNode.prev != null)
            reviveNode.prev.next = reviveNode;
        if (reviveNode.next != null)
        reviveNode.next.prev = reviveNode;
    }
}
