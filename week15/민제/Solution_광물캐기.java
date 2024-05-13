import java.util.*;

class Solution_광물캐기 {
    
    static int hashMineralLen;
    static int[] hashMinerals;
    static int answer;
    
    public int solution(int[] picks, String[] minerals) {
        int n = minerals.length;
        hashMineralLen = n%5 == 0 ? n/5 : n/5 + 1;
        hashMinerals = new int[hashMineralLen];
        int hashpick = 0;
        int num = 100;
        for(int i=0; i<3; i++) {
            hashpick += picks[i] * num;
            num /= 10;
        }
        for(int i=0; i<n/5 + 1; i++) {
            for(int j=0; j<5; j++) {
                int pos = i * 5 + j;
                if (pos >= n) break;
                if (minerals[pos].equals("diamond")) hashMinerals[i] += 100;
                else if(minerals[pos].equals("iron")) hashMinerals[i] += 10;
                else if(minerals[pos].equals("stone")) hashMinerals[i] += 1;
            }
        }
        System.out.println(hashpick);
        System.out.println(Arrays.toString(hashMinerals));
        
        answer = 1000000;
        dfs(0, hashpick, 0);
        
        return answer;
    }
    
    static void dfs(int turn, int hashpick, int score) {
        
        if (turn >= hashMineralLen || hashpick == 0) {
            answer = Math.min(answer, score);
            return;
        }
        //다이아 곡괭이 있을때
        int dia = hashpick/100;
        int iron = hashpick%100 / 10;
        int stone = hashpick%10;
        
        if (dia > 0) {
            dfs(turn + 1, hashpick - 100, score + getScore(100, hashMinerals[turn]));
        }
        if (iron > 0) {
            dfs(turn + 1, hashpick - 10, score + getScore(10, hashMinerals[turn]));
        }
        if (stone > 0) {
            dfs(turn + 1, hashpick - 1, score + getScore(1, hashMinerals[turn]));
        }
    }
    
    public static int getScore(int pick, int hashMineral) {
        int score = 0;
        int dia = hashMineral/100;
        int iron = hashMineral%100 / 10;
        int stone = hashMineral%10;
        //다이아 곡괭이
        if (pick == 100) {
            score = dia + iron + stone;
        } 
        //철 곡괭이
        else if (pick == 10) {
            score = 5 * dia + iron + stone;
        } 
        //돌 곡괭이
        else if (pick == 1) {
            score = 25 * dia + 5 * iron + stone;
        }
        return score;
    }
}