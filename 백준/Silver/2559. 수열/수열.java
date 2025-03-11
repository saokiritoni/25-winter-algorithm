import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n]; 

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); 
        }

        int sum = 0;
        // 초기 k개의 합 구하기
        for (int i = 0; i < k; i++) sum += arr[i];

        int max = sum;
        // 슬라이딩 윈도우 이동
        for (int i = k, j = 0; i < n; i++, j++) {
            sum += arr[i] - arr[j];  // 새 요소 추가, 윈도우 밖의 요소 제거
            if (max < sum) max = sum; // 최댓값 갱신
        }

        System.out.println(max);
    }
}
