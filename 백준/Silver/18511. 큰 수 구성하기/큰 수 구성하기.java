import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Integer[] arr;
    static int N;
    static int K;
    static int result = 0; // 가장 큰 값을 저장할 변수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new Integer[K];

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 큰 숫자부터 탐색하도록 내림차순 정렬
        Arrays.sort(arr, Collections.reverseOrder());

        makeBigNumber(0);

        System.out.println(result);
    }

    static void makeBigNumber(int now) {
        // N보다 크면 탐색 종료
        if (now > N) return;

        // 현재 값이 N 이하일 때만 result 갱신
        if (now > result) result = now;

        // 자릿수를 늘려가며 탐색
        for (int i = 0; i < K; i++) {
            makeBigNumber(now * 10 + arr[i]);
        }
    }
}
