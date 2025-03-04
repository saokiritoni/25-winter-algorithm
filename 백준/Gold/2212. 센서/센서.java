import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 센서 개수
        int K = Integer.parseInt(br.readLine()); // 집중국 개수

        int[] sensor = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }

        // 센서 위치 정렬
        Arrays.sort(sensor);

        // 센서 간 거리 계산 - 거리 개수는 N-1개
        Integer[] dist = new Integer[N - 1];
        for (int i = 0; i < N - 1; i++) {
            dist[i] = sensor[i + 1] - sensor[i];
        }

        // 거리를 내림차순 정렬 (큰 거리부터 줄여나갈 예정)
        Arrays.sort(dist, Collections.reverseOrder());

        int answer = 0;
        // 시작점이 K-1만큼 앞당겨짐
        for (int i = 0 + K - 1; i < N - 1; i++) {
            answer += dist[i];
        }

        System.out.println(answer);
    }
}
