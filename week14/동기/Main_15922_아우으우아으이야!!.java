/*
  348ms
*/
import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Integer.parseInt(st.nextToken());
        long y = Integer.parseInt(st.nextToken());
        long last = y;
        long answer = y-x;

        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            if(x >= last){
                answer += y-x;
                last =y;
            }else{
                if(y> last){
                    answer += y-last;
                    last = y;
                }
            }
        }
        System.out.println(answer);
    }

}
