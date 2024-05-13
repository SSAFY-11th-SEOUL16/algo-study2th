import java.util.*;
public class PG172927_광물캐기 {
	/*
	 * dfs 방식으로 해결
	 */
    static int[][] tired = {{1,1,1},{5,1,1},{25,5,1}};
    static int[] picks;
    static String[] minerals;
    static int maxpicks,totalpicks;
    static int min = Integer.MAX_VALUE;
    public int solution(int[] picks, String[] minerals) {
        this.picks = Arrays.copyOf(picks,picks.length);
        this.minerals = Arrays.copyOf(minerals,minerals.length);
        maxpicks = minerals.length%5==0? minerals.length/5:minerals.length/5+1;
        totalpicks = picks[0]+picks[1]+picks[2];
        
        dfs(0,0);
        return min;
    }
    static void dfs(int turn,int sum){
        if(min<sum) return;
        if(turn==maxpicks || turn==totalpicks){min=sum; return;}
        
        for(int i=0; i<3; i++){
            if(picks[i]==0) continue;
            picks[i]--;
            dfs(turn+1,sum+action(i,turn));
            picks[i]++;
        }
    }
    static int action(int pick, int turn){
        int start = 5*turn; int tmp=0;
        for(int i=start; i<start+5 && i<minerals.length; i++){
            if(minerals[i].equals("diamond")) tmp+=tired[pick][0];
            else if(minerals[i].equals("iron")) tmp+=tired[pick][1];
            else if(minerals[i].equals("stone")) tmp+=tired[pick][2];
        }
        return tmp;
    }
}