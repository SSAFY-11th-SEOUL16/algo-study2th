import java.util.*;

class Solution {

    /*
     *  - 시간 하단 첨부
     * 
     *  - TreeSet 여러개 사용
     */
    
    static class Songs implements Comparable<Songs>{
        TreeSet<Song> list;
        int plays;
        
        Songs(){
            this.list = new TreeSet<>();
            this.plays = 0;
        }
        
        void add(Song s){
            this.list.add(s);
            this.plays+=s.plays;
        }
        
        public int compareTo(Songs s){
            return s.plays-this.plays;
        }
    }
    
    static class Song implements Comparable<Song>{
        int id;
        int plays;
        
        Song(int id, int plays){
            this.id=id;
            this.plays=plays;
        }
        
        public int compareTo(Song s){
            if(this.plays==s.plays){
                return this.id-s.id;
            } else {
                return s.plays-this.plays;
            }
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        HashMap<String,Integer> genreToIndex = new HashMap<>();
        List<Songs> songList = new ArrayList<>();
        int curIdx = 0;
        
        for(int i=0; i<genres.length; i++){
            String genre = genres[i];
            int play = plays[i];
            
            if(!genreToIndex.containsKey(genre)){
                genreToIndex.put(genre, curIdx++);
                songList.add(new Songs());
            }
            
            songList.get(genreToIndex.get(genre)).add(new Song(i, play));
        }
        
        Collections.sort(songList);
        
        Queue<Integer> q = new LinkedList<>();
        
        for(Songs songs : songList){
            q.add(songs.list.pollFirst().id);
            if(!songs.list.isEmpty()){
                q.add(songs.list.pollFirst().id);
            }
        }
        
        int[] ret = new int[q.size()];
        
        int idx=0;
        while(!q.isEmpty()){
            ret[idx++] = q.poll();
        }
        
        return ret;
    }
    
}

/*
     *  정확성  테스트
     * 테스트 1 〉	통과 (1.21ms, 76.2MB)
     * 테스트 2 〉	통과 (1.44ms, 74.9MB)
     * 테스트 3 〉	통과 (1.03ms, 75.4MB)
     * 테스트 4 〉	통과 (1.02ms, 82.7MB)
     * 테스트 5 〉	통과 (2.51ms, 72.9MB)
     * 테스트 6 〉	통과 (1.19ms, 71.6MB)
     * 테스트 7 〉	통과 (1.18ms, 72.8MB)
     * 테스트 8 〉	통과 (1.89ms, 78.2MB)
     * 테스트 9 〉	통과 (1.91ms, 79MB)
     * 테스트 10 〉	통과 (1.99ms, 83.1MB)
     * 테스트 11 〉	통과 (1.12ms, 81.1MB)
     * 테스트 12 〉	통과 (1.12ms, 74.3MB)
     * 테스트 13 〉	통과 (1.81ms, 77.5MB)
     * 테스트 14 〉	통과 (1.25ms, 78.6MB)
     * 테스트 15 〉	통과 (1.01ms, 71.2MB)
     */