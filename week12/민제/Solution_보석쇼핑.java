import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

class Solution_보석쇼핑 {
    public int[] solution(String[] gems) {
        int n = gems.length;
        HashMap<String, Queue<Integer>> jewels = new HashMap<>();
        int left = 0;
        int right = 0;
        int result_left = 1;
        int result_right = n;
        
        HashSet<String> jewelSet = new HashSet<>();
        for (String gem : gems) {
			jewelSet.add(gem);
		}
        int jewelCnt = jewelSet.size();
        
        
        while (right < n) {
        	String rightGem = gems[right++];
        	
        	if (jewels.containsKey(rightGem)) {
        		Queue<Integer> list = jewels.get(rightGem);
        		list.add(right);
        		jewels.put(rightGem, list);
            } else {
            	Queue<Integer> list = new ArrayDeque<Integer>();
            	list.add(right);
                jewels.put(rightGem, list);
            }
        	
        	boolean valid = false;
        	//left이동시작
        	while (jewels.keySet().size() == jewelCnt) {
        		valid = true;
        		String leftGem = gems[left++];
        		Queue<Integer> queue = jewels.get(leftGem);
        		queue.poll();
        		if (queue.isEmpty()) {
        			jewels.remove(leftGem);
        			break;
        		}
        	}
        	
        	if (valid) {
        		if (right - left < result_right - result_left) {
            		result_left = left;
            		result_right = right;
            	}
        	}
        	
        }

        int[] answer = {result_left, result_right};
        return answer;
    }
}