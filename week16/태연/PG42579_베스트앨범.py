def solution(genres, plays):

    '''
    예전에 풀었던 파이썬 풀이인데 좋은풀이는아님
    근데 그냥 올려봄 

    테스트 1 〉	통과 (0.06ms, 10.4MB)
    테스트 2 〉	통과 (0.01ms, 10.2MB)
    테스트 3 〉	통과 (0.01ms, 10.4MB)
    테스트 4 〉	통과 (0.01ms, 10.1MB)
    테스트 5 〉	통과 (0.15ms, 10.3MB)
    테스트 6 〉	통과 (0.07ms, 10.2MB)
    테스트 7 〉	통과 (0.08ms, 10.3MB)
    테스트 8 〉	통과 (0.05ms, 10.1MB)
    테스트 9 〉	통과 (0.01ms, 10.2MB)
    테스트 10 〉통과 (0.08ms, 10.4MB)
    테스트 11 〉통과 (0.02ms, 10.2MB)
    테스트 12 〉통과 (0.05ms, 10.1MB)
    테스트 13 〉통과 (0.07ms, 10.2MB)
    테스트 14 〉통과 (0.08ms, 10.4MB)
    테스트 15 〉통과 (0.02ms, 10.1MB)
    '''
    
    answer = []
    song = []
    for i in range(len(genres)):
        song.append([genres[i],plays[i],i]) 
    song = sorted(song, key = lambda x:x[1],reverse=True)
    
    song_num = {}
    
    for s in song:
        if s[0] in song_num:
            song_num[s[0]] += s[1]
        else:
            song_num[s[0]] = s[1]
            
    song_num = sorted(song_num,key=lambda x:song_num[x], reverse=True)
    
    for kind in song_num:
        index = 0
        for s in song:
            if s[0] == kind and index < 2:
                answer.append(s[2])
                index +=1
        
    return answer