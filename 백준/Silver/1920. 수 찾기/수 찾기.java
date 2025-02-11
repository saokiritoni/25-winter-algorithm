import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashSet<Integer> hs = new HashSet<Integer>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hs.add(Integer.valueOf(st.nextToken()));
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            if (hs.contains(Integer.valueOf(st.nextToken()))) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }

        }
    }
}
