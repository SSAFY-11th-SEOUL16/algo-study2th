import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 249 ms
 */

public class Solution {
    static int p;
    static int n;
    static ArrayList<Pos> peoples;
    static int answer;
    static Pos stairs1;
    static Pos stairs2;

    static int stairs1Height;
    static int stairs2Height;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            peoples = new ArrayList<>();
            answer = Integer.MAX_VALUE;
            stairs1 = null;
            stairs2 = null;
            p = 0;

            boolean isFirstStairs = true;

            for (int i = 0; i < n; i++) {
                int[] arr = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int j = 0; j < n; j++) {
                    if (arr[j] == 1) {
                        p++;
                        peoples.add(new Pos(j, i));
                    } else if (arr[j] > 1) {
                        if (isFirstStairs) {
                            stairs1 = new Pos(j, i);
                            stairs1Height = arr[j];
                            isFirstStairs = false;
                        } else {
                            stairs2 = new Pos(j, i);
                            stairs2Height = arr[j];
                        }
                    }
                }
            }
            combi(0, new ArrayList<Integer>(), new ArrayList<Integer>());

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static int simulation(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        int result = 0; // 걸린 시간

        // 계단1,2에 각각 들어갈 사람들 리스트
        ArrayList<Pos> stairs1PeopleList = new ArrayList<>();
        for (int index : list1) stairs1PeopleList.add(peoples.get(index).clone());
        ArrayList<Pos> stairs2PeopleList = new ArrayList<>();
        for (int index : list2) stairs2PeopleList.add(peoples.get(index).clone());

        // stairsWait 변수는 계단입구에서 대기하는 사람의 수 / stairsQueue 는 계단으로 내려가고 있는 사람들
        int stairs1Wait = 0;
        Queue<Integer> stairs1Queue = new LinkedList<>();
        int stairs2Wait = 0;
        Queue<Integer> stairs2Queue = new LinkedList<>();

        // 계단 1,2 각각 같은 로직들을 돌렸습니다. 설명은 계단 1에만 적겠습니다.
        while (!stairs1PeopleList.isEmpty() || !stairs2PeopleList.isEmpty() || !stairs1Queue.isEmpty() || !stairs2Queue.isEmpty() || stairs1Wait > 0 || stairs2Wait > 0) {
            /*
             * for문 안에 i < stairs1Queue.size() 이런 식으로 하니까
             * 중간에 poll 할 경우 사이즈가 동적으로 변하면서 예상치 못한 결과가 나옵니다.
             */
            int s1qSize = stairs1Queue.size();
            // 계단으로 내려가고 있는 사람들의 시간을 모두 poll 해서 1씩 추가하고 계단높이보다 작으면 다시 add
            for (int i = 0; i < s1qSize; i++) {
                int time = stairs1Queue.poll() + 1;
                if (time < stairs1Height)
                    stairs1Queue.add(time);
            }

            // 계단 위(큐)에 3명 이하일때, 입구에서 대기하는 사람들 추가
            while (stairs1Queue.size() < 3) {
                if (stairs1Wait == 0) break;
                stairs1Queue.add(0);
                stairs1Wait--;
            }

            int s2qSize = stairs2Queue.size();
            for (int i = 0; i < s2qSize; i++) {
                int time = stairs2Queue.poll() + 1;
                if (time < stairs2Height)
                    stairs2Queue.add(time);
            }

            while (stairs2Queue.size() < 3) {
                if (stairs2Wait == 0) break;
                stairs2Queue.add(0);
                stairs2Wait--;
            }

            // 계단 입구에 도착한 사람들을 지우기 위한 리스트
            ArrayList<Pos> removeList = new ArrayList<>();

            // 사람들의 다음 위치를 구하고, 다음 위치가 계단입구일 때, 아닐 때 구분하여 처리
            for (Pos curPos : stairs1PeopleList) {
                Pos nextPos = getNextPos(curPos, stairs1);
                if (nextPos.x == stairs1.x && nextPos.y == stairs1.y) {
                    stairs1Wait++;
                    removeList.add(curPos);
                } else {
                    curPos.x = nextPos.x;
                    curPos.y = nextPos.y;
                }
            }
            // 계단 입구에 도착한 사람들 지우기
            for (Pos pos : removeList) stairs1PeopleList.remove(pos);

            removeList.clear();
            for (Pos curPos : stairs2PeopleList) {
                Pos nextPos = getNextPos(curPos, stairs2);
                if (nextPos.x == stairs2.x && nextPos.y == stairs2.y) {
                    stairs2Wait++;
                    removeList.add(curPos);
                } else {
                    curPos.x = nextPos.x;
                    curPos.y = nextPos.y;
                }
            }
            for (Pos pos : removeList) stairs2PeopleList.remove(pos);

            result++;
        }


        return result;
    }

    // 두 계단에 들어갈 사람들의 조합
    static void combi(int index, ArrayList<Integer> list1, ArrayList<Integer> list2) {
        if (index == p) {
            answer = Math.min(answer, simulation(list1, list2));
            return;
        }
        list1.add(index);
        combi(index + 1, list1, list2);
        list1.remove(list1.size() - 1);
        list2.add(index);
        combi(index + 1, list1, list2);
        list2.remove(list2.size() - 1);
    }

    static Pos getNextPos(Pos people, Pos stairs) {
        int xDiff = stairs.x - people.x;
        if (xDiff != 0) {
            return new Pos(people.x + (xDiff / Math.abs(xDiff)), people.y);
        } else {
            int yDiff = stairs.y - people.y;
            return new Pos(people.x, people.y + (yDiff / Math.abs(yDiff)));
        }
    }

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos clone() {
            return new Pos(x, y);
        }
    }
}