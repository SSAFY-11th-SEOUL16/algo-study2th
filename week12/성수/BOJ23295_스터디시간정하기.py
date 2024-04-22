import sys
input = sys.stdin.readline
N, T = map(int, input().split())
memo = [0] * 100_001

for _ in range(N):
    k = int(input())
    for __ in range(k):
        s, e = map(int, input().split())
        memo[s] += 1
        memo[e] -= 1

for i in range(1, 100_001):
    memo[i] += memo[i - 1]

total = 0
for i in range(T):
    total += memo[i]
answer = total
s, e = 0, T
for i in range(T, 100_001):
    total -= memo[i - T]
    total += memo[i]
    if(total > answer):
        answer = total
        s = i - T + 1
        e = i + 1

print(s,  e)