import java.util.Arrays;

class Solution {

    /*
     *  - 5개단위로 끊어서 Greedy하게 풀이
     */
    
    static class Info implements Comparable<Info>{
        int[] minerals;
        int cost;
        
        Info(){
            this.minerals = new int[5];
            cost = 0;
        }
        
        public int compareTo(Info i){
            return i.cost-this.cost;
        }
        
        public int getCost(int div){
            int ret = 0;
            for(int i=0; i<5; i++){
                if(minerals[i]==0) continue;
                
                ret += Math.max(minerals[i]/div,1);
            }
            
            return ret;
        }
    }
    
    static int getCost(String str){
        switch(str){
            case "diamond": return 25;
            case "iron": return 5;
            case "stone": return 1;
        }
        return 0;
    }
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int sumPicks = picks[0] + picks[1] + picks[2];
        
        Info[] info = new Info[sumPicks];
        for(int i=0; i<info.length; i++){
            info[i] = new Info();
        }
        
        for(int i=0; i<minerals.length && i<sumPicks*5; i++){
            int cost = getCost(minerals[i]);
            info[i/5].minerals[i%5] = cost;
            info[i/5].cost += cost;
        }
        
        Arrays.sort(info);
        
        for(int i=0; i<info.length; i++){
            Info cur = info[i];
            
            if(picks[0]>0){
                answer += cur.getCost(25);
                picks[0]--;
            } else if(picks[1]>0){
                answer += cur.getCost(5);
                picks[1]--;
            } else if(picks[2]>0){
                answer += cur.getCost(1);
                picks[2]--;
            }
        }
        
        return answer;
    }
}