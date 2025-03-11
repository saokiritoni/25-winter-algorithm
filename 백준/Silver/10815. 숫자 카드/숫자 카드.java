import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static boolean binarySearch(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        br.readLine(); // 필요 없으니 버려주기
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (st.hasMoreTokens()) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(arr, target) ? "1 " : "0 ");
        }

        System.out.println(sb.toString().trim()); // 마지막 공백 제거
    }
}
